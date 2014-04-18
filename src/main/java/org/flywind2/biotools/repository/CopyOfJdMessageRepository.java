/**
 * 
 */
package org.flywind2.biotools.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.flywind2.model.messages.JdMessage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author SuFeng
 *
 */
@Repository
public class CopyOfJdMessageRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<JdMessage> findAll(){
		Query query = entityManager.createQuery("select m from JdMessage m");
		
		return query.getResultList();
	}

	@Transactional(readOnly=false)
	public void delete(Long messageId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("delete from JdMessage m where m.messageId = :messageId");
		 
		query.setParameter("messageId", messageId).executeUpdate();
	}
	

	
}
