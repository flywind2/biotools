/**
 * 
 */
package org.flywind2.biotools.service;

import java.util.List;
import java.util.concurrent.Future;

import org.flywind2.biotools.model.messages.JdMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author SuFeng
 * 
 */
@Component
@EnableAsync
public class AsyncProcessServiceImpl implements AsyncProcessService {
	@Override
	@Async
	public Future<Void> processNotifications(List<JdMessage> list) {
		for(JdMessage message : list){
			System.out.println("messageId:"+message.getMessageId());
		}
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("finished");
		return new AsyncResult(null);
	}

	@Override
	@Async
	//@Scheduled(fixedRate=10000)
	public void gc() {
		// TODO Auto-generated method stub
		System.out.println("do gc");
		System.gc();
	}
}
