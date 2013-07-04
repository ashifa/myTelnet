package telnet.dao;

import java.util.List;

import javax.persistence.Query;

import telnet.model.Visitor;

public interface VisitorDao /*extends JpaRepository<Visitor, Integer>*/ {
/*	@Query(value="select p FROM Visitor p")*/
	public List<Visitor> findAll();
}
