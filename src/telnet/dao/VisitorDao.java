package telnet.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import telnet.model.Visitor;

public interface VisitorDao extends JpaRepository<Visitor, Integer> {

	public List<Visitor> findAll();
}
