/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telnet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import telnet.dao.TelnetDAO;

/**
 * 
 * @author 305020571
 */
public class TelnetServiceImp implements TelnetService {

	private TelnetDAO telnetDAO = new TelnetDAO();

	public static void main(String[] args) {

		System.out.println(new TelnetServiceImp().getVersion());

	}

	public Map<String, String> getTargetMap() {
		return this.telnetDAO.getTargetMap();
	}

	public void setTargetMap(Map<String, String> targetMap) {
		this.telnetDAO.setTargetMap(targetMap);
	}

	public Map<String, String> getCMDMap() {
		return this.telnetDAO.getCMDMap();
	}

	public void setCMDMap(Map<String, String> cMDMap) {
		this.telnetDAO.setCMDMap(cMDMap);
	}

	private List<Future<List<String>>> connectTarget(Collection<String> CMDlist) {
		ExecutorService es = Executors.newFixedThreadPool(this.telnetDAO
				.getTargetMap().size());
		List<Future<List<String>>> futureList = new ArrayList<Future<List<String>>>();
		for (String hostname : this.telnetDAO.getTargetMap().keySet()) {
			Future<List<String>> rs = es.submit(new TelnetThread(hostname,
					this.telnetDAO.getTargetMap().get(hostname), 
					this.telnetDAO.getConfig().getProperty("userName"),
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

	String buildHtml(List<Future<List<String>>> futureList) {
		// take out the results and build an html output
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=\"1\"> \n");
		sb.append("<tr><th>targetName</th><th>IP</th><th>On/Off</th>");
		for (String str : this.telnetDAO.getCMDMap().keySet()) {
			sb.append("<th>");
			sb.append(str);
			sb.append("</th>");
		}
		sb.append("</tr>\n");
		List<String> list = new ArrayList<String>();
		boolean flag = true;
		for (Future<List<String>> rs : futureList) {
			try {
				StringBuilder sbuilder = new StringBuilder();
				if (flag) {
					sbuilder.append("<tr>");
				} else {
					sbuilder.append("<tr class=\"even\">");
				}
				flag = !flag;
				for (String str : rs.get()) {
					sbuilder.append("<td>");
					sbuilder.append(str);
					sbuilder.append("</td>");
				}
				for (int i = rs.get().size() - 2; i < this.telnetDAO
						.getCMDMap().keySet().size(); i++) {// in some cases,
															// not all column
															// info was
															// collectd, fill
															// with
															// empty block.
					sbuilder.append("<td></td>");
				}
				sbuilder.append("</tr>\n");
				list.add(sbuilder.toString());

			} catch (InterruptedException ex) {
				Logger.getLogger(TelnetServiceImp.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (ExecutionException ex) {
				Logger.getLogger(TelnetServiceImp.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
		// sort the result
		// Collections.sort(list);
		for (String str : list) {
			sb.append(str);
		}
		sb.append("</table>");
		return sb.toString();
	}

	static List<List<String>> buildRaw(List<Future<List<String>>> futureList) {
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
		return retList;
	}

	public String getVersion() {

		// start up thread based on these host name
		List<Future<List<String>>> futureList = connectTarget(this.telnetDAO
				.getCMDMap().values());
		// Build the html table
		return buildHtml(futureList);

	}

	@Override
	public List<List<String>> getNewVersion(Collection<String> CMDlist) {

		// start up thread based on these host name
		List<Future<List<String>>> futureList = connectTarget(CMDlist);
		// extract the list
		return buildRaw(futureList);
	}

	public static List<List<String>> testGetVersion() {
		List<List<String>> li = new ArrayList<List<String>>();
		li.add(Arrays.asList("aa1", "bb1", "cc1"));
		li.add(Arrays.asList("aa", "bb", "cc"));
		li.add(Arrays.asList("aa2", "bb2", "cc2"));
		li.add(Arrays.asList("aa3", "bb3", "cc3"));
		li.add(Arrays.asList("aa3", "bb3", "cc3"));
		return li;

	}


	@Override
	public List<List<String>> getOldVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getSelectedTargetRegion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectedTargetRegion(Set<String> selectedTargetRegion) {
		// TODO Auto-generated method stub
		
	}

}
