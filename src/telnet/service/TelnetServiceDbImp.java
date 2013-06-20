package telnet.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import telnet.model.TargetInfo;

@Transactional
public class TelnetServiceDbImp implements TelnetService {
	private EntityManager em;
	@PersistenceContext
	public void setEntityManager(EntityManager em){
		this.em=em;
	}
	@Override
	public List<List<String>> getVersion(Collection<String> CMDlist) {
		// TODO Auto-generated method stub
		TargetInfo ti = em.find(TargetInfo.class, "bay1");
		
		return null;
	}

	@Override
	public Map<String, String> getTargetMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTargetMap(Map<String, String> targetMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> getCMDMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCMDMap(Map<String, String> cMDMap) {
		// TODO Auto-generated method stub

	}

}
