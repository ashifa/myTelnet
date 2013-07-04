package telnet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import telnet.model.Visitor;
@Repository
@Transactional
public class ListVisitorDao {
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Visitor> query(){
		Query query = this.em.createQuery("select p FROM Visitor p");
		List<Visitor> list = query.getResultList();
		return list;
	}

	
}
