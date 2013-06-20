package telnet.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import telnet.dao.TelnetDAO;
import telnet.model.TargetInfo;

@Transactional
public class TelnetServiceDbImp implements TelnetService {
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private TelnetDAO telnetDAO = new TelnetDAO();

	@Override
	public Map<String, String> getTargetMap() {

		return this.telnetDAO.getTargetMap();
	}

	@Override
	public void setTargetMap(Map<String, String> targetMap) {

		this.telnetDAO.setTargetMap(targetMap);
	}

	@Override
	public Map<String, String> getCMDMap() {

		return this.telnetDAO.getCMDMap();
	}

	@Override
	public void setCMDMap(Map<String, String> cMDMap) {
		this.telnetDAO.setCMDMap(cMDMap);

	}

	private void saveVersion(List<List<String>> listArg) {
		for (List<String> strList : listArg) {
			if (strList.get(2).equals("On")) {
				TargetInfo targInfo = new TargetInfo();
				targInfo.setHostName(strList.get(0));
				targInfo.setiP(strList.get(1));
				targInfo.setVersion(strList.get(3));// need the third one to be
													// version info
				em.merge(targInfo);
			}
		}
	}

	private List<Future<List<String>>> connectTarget(Collection<String> CMDlist) {
		ExecutorService es = Executors.newFixedThreadPool(this.telnetDAO
				.getTargetMap().size());
		List<Future<List<String>>> futureList = new ArrayList<Future<List<String>>>();
		for (String hostname : this.telnetDAO.getTargetMap().keySet()) {
			Future<List<String>> rs = es.submit(new TelnetThread(hostname,
					this.telnetDAO.getTargetMap().get(hostname), this.telnetDAO
							.getConfig().getProperty("userName"),
					this.telnetDAO.getConfig().getProperty("passWord"),
					this.telnetDAO.getConfig().getProperty("prompt"), CMDlist));
			futureList.add(rs);
		}
		es.shutdown();

		try {
			es.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			Logger.getLogger(TelnetServiceImp.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		es.shutdownNow();
		return futureList;
	}

	private List<List<String>> buildRaw(List<Future<List<String>>> futureList) {
		List<List<String>> retList = new ArrayList<List<String>>();
		for (Future<List<String>> fl : futureList) {
			try {
				retList.add(fl.get());
			} catch (InterruptedException ex) {
				Logger.getLogger(TelnetServiceImp.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (ExecutionException ex) {
				Logger.getLogger(TelnetServiceImp.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
		this.saveVersion(retList);
		return retList;
	}

	public List<List<String>> getNewVersion(Collection<String> CMDlist) {
		// start up thread based on these host name
		List<Future<List<String>>> futureList = connectTarget(CMDlist);
		return buildRaw(futureList);

	}

	@SuppressWarnings("unchecked")
	public List<List<String>> getOldVersion() {
		Query query = this.em.createQuery("select p FROM TargetInfo p");
		List<TargetInfo> list = query.getResultList();
		List<List<String>> listRet = new ArrayList<List<String>>();
		for (TargetInfo targInfo : list) {
			List<String> tmp = new ArrayList<String>();
			tmp.add(targInfo.getHostName());
			tmp.add(targInfo.getiP());
			tmp.add(targInfo.getVersion());
			listRet.add(tmp);
		}
		return listRet;

	}
}
