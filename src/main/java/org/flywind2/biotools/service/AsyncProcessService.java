/**
 * 
 */
package org.flywind2.biotools.service;

import java.util.List;
import java.util.concurrent.Future;

import org.flywind2.biotools.model.messages.JdMessage;

/**
 * @author SuFeng
 *
 */
public interface AsyncProcessService {
	public Future<Void> processNotifications(List<JdMessage> list);
	
	public void gc();
}
