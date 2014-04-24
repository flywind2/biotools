/**
 * 
 */
package org.flywind2.biotools.chat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author SuFeng
 * 异步调用学习
 */
@Controller
@RequestMapping(value={"/chat","/chat/*"})
public class ChatController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
//	private final List<String> messages = new java.util.concurrent.CopyOnWriteArrayList<String>();
	
	private final Map<String,DeferredResult<Message>> chatRequests = new ConcurrentHashMap<String,DeferredResult<Message>>();
	
	@RequestMapping(method=RequestMethod.GET)
	public String chat(){
		return "chat/chat";
	}
	
	//DeferredResult<JdMessage>
	@RequestMapping(value="join",method=RequestMethod.GET)
	@ResponseBody
	public String join(@RequestParam(required=true,value="user") String user,HttpSession session){
		log.info("user ["+user+"] joined in chat  room !");
		session.setAttribute("user", user);

		Message message = new Message();
		message.setUser(user);
		message.setContent("user ["+user+"] joined in chat  room !");
		message.setDate(sdf.format(new Date()));
		processMessage(message);

		return "ok";
	}
	
	@RequestMapping(value="post",method=RequestMethod.POST)
	@ResponseBody
	public String postMessage(@RequestParam(required=true,value="content") String content,HttpSession session) throws UnsupportedEncodingException{
		
		log.info("post message :"+ URLDecoder.decode(content, "utf-8"));
		
		Message message = new Message();
		message.setUser((String) session.getAttribute("user"));
		message.setContent(content);
		message.setDate(sdf.format(new Date()));
		
		processMessage(message);
		
		return "ok";
	}
	
	
	@RequestMapping(value="get",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody DeferredResult<Message> getMessages(HttpSession session){
		
		log.info("get message :");
		
		final String user = (String)session.getAttribute("user");
		
		log.info("get message :"+ user);
		
		//0 表示不超时
		DeferredResult<Message> result = new DeferredResult<>(10000L,"time is out");
		
		
		
		if(user==null){
			//do nothing
			log.info("get message  : user is null");
		}else{
			result.onCompletion(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					chatRequests.remove(user);
					log.info("get message completed : remove user ref");
				}
				
			});
			
			result.onTimeout(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					chatRequests.remove(user);
				}
				
			});
			
			chatRequests.put(user, result);
		}
		

		
		
		log.info("get message return message "+ result);
		return result;
		
	}
	
	
	/**
	 * 向所有用户分发消息
	 * @param message
	 */
	private void processMessage(Message message) {
		// TODO Auto-generated method stub
		log.info("process message");
		Set<String> users = chatRequests.keySet();
		for(String user : users){
			chatRequests.get(user).setResult(message);
		}
	}
	
	
	


	
	/**
	 * Message Entity
	 * @author SuFeng
	 *
	 */
	class Message{
		private String user;
		private String content;
		private String date;
		
		Message(){
			
		}
		
		Message(String content){
			this.content = content;
		}
		
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		
		
	}
	
}
