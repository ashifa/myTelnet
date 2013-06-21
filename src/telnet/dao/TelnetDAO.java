package telnet.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TelnetDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	private Properties config = new Properties();
	private Map<String, String> targetMap = new TreeMap<String, String>();
	private Map<String, String> CMDMap = new TreeMap<String, String>();
	private Set<String> selectedTargetRegion = new HashSet<String>();

	public Set<String> getSelectedTargetRegion() {
		return selectedTargetRegion;
	}

	public void setSelectedTargetRegion(Set<String> selectedTargetRegion) {
		this.selectedTargetRegion = selectedTargetRegion;
	}

	public Properties getConfig() {
		return config;
	}

	public void setConfig(Properties config) {
		this.config = config;
	}

	public Map<String, String> getTargetMap() {
		return targetMap;
	}

	public void setTargetMap(Map<String, String> targetMap) {
		this.targetMap = targetMap;
	}

	public Map<String, String> getCMDMap() {
		return CMDMap;
	}

	public void setCMDMap(Map<String, String> cMDMap) {
		CMDMap = cMDMap;
	}

	public TelnetDAO() {
		System.out.println("in constructor of "+ this.getClass());
		if (false == readConfig()) {
			System.exit(1);
		}
		parseConfig();
	}

	private boolean readConfig() {
		try {
			URL targetInfo = Thread.currentThread().getContextClassLoader()
					.getResource("config.xml");
			if (targetInfo != null) {
				this.config.loadFromXML(new FileInputStream(targetInfo
						.getFile()));
			} else {
				this.config.loadFromXML(new FileInputStream("config.xml"));
			}

		} catch (IOException ex) {
			Logger.getLogger(TelnetDAO.class.getName()).log(
					Level.SEVERE, null, ex);
			return false;
		}
		return true;

	}

	private void parseConfig() {
		// extract the CMD and HOST
		for (String str : config.stringPropertyNames()) {
			if (str.startsWith("CMD")) {
				this.CMDMap.put(str.substring(4), config.getProperty(str));
			} else if (str.startsWith("HOST_")) {
				this.targetMap.put(str.substring(5), config.getProperty(str));
			}
		}
		String[]tmp=config.getProperty("selectedTargetRegion").split(",");
		Collections.addAll(this.selectedTargetRegion, tmp);

	}
}
