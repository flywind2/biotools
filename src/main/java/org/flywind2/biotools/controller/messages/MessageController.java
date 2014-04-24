/**
 * 
 */
package org.flywind2.biotools.controller.messages;

import java.util.List;

import org.flywind2.biotools.model.messages.JdMessage;
import org.flywind2.biotools.repository.JdMessageRepository;
import org.flywind2.biotools.repository.JdResponseRepository;
import org.flywind2.biotools.service.AsyncProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * @author SuFeng
 *
 */
@Controller
@RequestMapping(value={"/message/*","/message"})
public class MessageController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JdMessageRepository jdMessageRepository;
	
	@Autowired
	private JdResponseRepository jdResponseRepository;
	
	@Autowired
    AsyncProcessService asyncService;

	/**
	 * ��ѯ��ʾ���е�message
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"**"})
	public String list(Model model){
		//log.info("find All 1");
		List<JdMessage> list = jdMessageRepository.findAll();
		model.addAttribute("messages", list);
		//asyncService.processNotifications(list);
		//log.info("process finished !");
		return "message/message";
	}
	
	
	
	/**
	 * ��������ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="add")
	public String add(Model model){
		//@PathVariable(value="messageId") Long messageId
		log.info("goto add new message page");
		JdMessage message = new JdMessage();
		model.addAttribute("jdMessage",message);
		return "message/add";
	}
	
	
	/**
	 * �?�ύ��post����
	 * @param jdMessage
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="add")
	public String add(@ModelAttribute JdMessage jdMessage){
		log.info("do new message");
		jdMessageRepository.save(jdMessage);
		
		log.info("added message's id is "+ jdMessage.getMessageId());
		
		return "redirect:/message/";
	}
	
	
	/**
	 * show/{messageId}
	 * @param messageId
	 * @return 
	 */
	@RequestMapping(value="show/{messageId:[\\d]+}",method=RequestMethod.GET)
	public String detail(Model model,@PathVariable(value="messageId") Long messageId ){
		log.info("show message " + messageId);
		JdMessage message = jdMessageRepository.findOne(messageId);
		model.addAttribute("message", message);
		return "message/detail";
	}
	
	/**
	 * ���IDɾ��message
	 * @param messageId
	 * @return 
	 */
	@RequestMapping(value="delete/{messageId:[\\d]+}",method=RequestMethod.GET)
	public String delete(@PathVariable(value="messageId") Long messageId ){
		
		jdMessageRepository.delete(messageId);
		return "redirect:/message/";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="update/{messageId:[\\d]+}")
	public String update(Model model,@PathVariable(value="messageId") Long messageId){
		//@PathVariable(value="messageId") Long messageId
		JdMessage message =jdMessageRepository.findOne(messageId);
		
		model.addAttribute("jdMessage",message);
		return "message/update";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="update/{messageId:[\\d]+}")
	public String update(@ModelAttribute JdMessage jdMessage,@PathVariable(value="messageId") Long messageId){
		//@PathVariable(value="messageId") Long messageId
		jdMessageRepository.saveAndFlush(jdMessage);
		return "redirect:/message/";
	}
	
}
