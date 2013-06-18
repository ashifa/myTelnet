/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telnet.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * 
 * @author 305020571
 */
public class TelnetServiceImp implements TelnetService {

	private static Properties config = new Properties();
	private static Map<String, String> targetMap = new TreeMap<String, String>();
	private static Map<String, String> CMDMap = new TreeMap<String, String>();

	static {
		if (false == readConfig()) {
			System.exit(1);
		}
		System.out.println("in Telnet static");
		parseConfig();
	}

	public static void main(String[] args) {

		System.out.println(TelnetServiceImp.getVersion());

	}

	public Map<String, String> getTargetMap() {
		return targetMap;
	}

	public void setTargetMap(Map<String, String> targetMap) {
		TelnetServiceImp.targetMap = targetMap;
	}

	public Map<String, String> getCMDMap() {
		return CMDMap;
	}

	public void setCMDMap(Map<String, String> cMDMap) {
		CMDMap = cMDMap;
	}

	private static boolean readConfig() {
		try {
			URL targetInfo = Thread.currentThread().getContextClassLoader()
					.getResource("config.xml");
			if (targetInfo != null) {
				TelnetServiceImp.config.loadFromXML(new FileInputStream(targetInfo
						.getFile()));
			} else {
				TelnetServiceImp.config.loadFromXML(new FileInputStream(
						"config.xml"));
			}

		} catch (IOException ex) {
			Logger.getLogger(TelnetServiceImp.class.getName()).log(Level.SEVERE,
					null, ex);
			return false;
		}
		return true;

	}

	private static void parseConfig() {
		// extract the CMD and HOST
		for (String str : config.stringPropertyNames()) {
			if (str.startsWith("CMD")) {
				TelnetServiceImp.CMDMap.put(str.substring(4),
						config.getProperty(str));
			} else if (str.startsWith("HOST_")) {
				TelnetServiceImp.targetMap.put(str.substring(5),
						config.getProperty(str));
			}
		}
	}

	private static List<Future<List<String>>> connectTarget(
			Collection<String> CMDlist) {
		ExecutorService es = Executors.newFixedThreadPool(targetMap.size());
		List<Future<List<String>>> futureList = new ArrayList<Future<List<String>>>();
		for (String hostname : targetMap.keySet()) {
			Future<List<String>> rs = es.submit(new TelnetThread(hostname,
					targetMap.get(hostname), config.getProperty("userName"),
					config.getProperty("passWord"), config
							.getProperty("prompt"), CMDlist));
			futureList.add(rs);
		}
		es.shutdown();

		try {
			es.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			Logger.getLogger(TelnetServiceImp.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		es.shutdownNow();
		return futureList;
	}

	static String buildHtml(List<Future<List<String>>> futureList) {
		// take out the results and build an html output
		StringBuilder sb = new StringBuilder();
		sb.append("<table border=\"1\"> \n");
		sb.append("<tr><th>targetName</th><th>IP</th><th>On/Off</th>");
		for (String str : TelnetServiceImp.CMDMap.keySet()) {
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
				for (int i = rs.get().size() - 2; i < TelnetServiceImp.CMDMap
						.keySet().size(); i++) {// in some cases, not all column
												// info was collectd, fill with
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

	public static String getVersion() {

		// start up thread based on these host name
		List<Future<List<String>>> futureList = connectTarget(TelnetServiceImp.CMDMap
				.values());
		// Build the html table
		return buildHtml(futureList);

	}

	public List<List<String>> getVersion(Collection<String> CMDlist) {

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

}
