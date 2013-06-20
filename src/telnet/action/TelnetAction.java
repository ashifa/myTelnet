package telnet.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import telnet.service.TelnetService;;

public class TelnetAction extends ActionSupport {
	private static final long serialVersionUID = 5704419762979642412L;
	/**
	 * 
	 */
	private TelnetService telnetService;
	private Map<String, String> targetMap;
	private Map<String, String> CMDMap;
	private String customizedCMD;
	private List<List<String>> tblist;
	private List<String> selectedValue;

	public List<String> getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(List<String> selectedValue) {
		this.selectedValue = selectedValue;
	}

	public TelnetService getTelnetService() {
		return telnetService;
	}

	public void setTelnetService(TelnetService telnetService) {
		this.telnetService = telnetService;
	}

	public String getCustomizedCMD() {
		return customizedCMD;
	}

	public void setCustomizedCMD(String customizedCMD) {
		this.customizedCMD = customizedCMD;
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

	public void setTblist(List<List<String>> tblist) {
		this.tblist = tblist;
	}

	public List<List<String>> getTblist() {
		return this.tblist;
	}

	@Override
	public String execute() {
		this.setTargetMap(telnetService.getTargetMap());
		this.setCMDMap(telnetService.getCMDMap());
		System.out.println(this.selectedValue);
		if (this.selectedValue == null){
			List<List<String>> list = telnetService.getOldVersion();
			this.setTblist(list);
			return"DEFAULT";
		}
		
		
		if (!this.customizedCMD.isEmpty()) {
			String customizedCMD = this.customizedCMD.contains("|||") ? this.customizedCMD
					: this.customizedCMD + "|||.*";
			this.getCMDMap().put("customizedCMD", customizedCMD);
			this.selectedValue.add("customizedCMD");
		} else {
			this.selectedValue.remove("customizedCMD");
		}
		if(!this.selectedValue.contains(" Software Version")){
			this.selectedValue.add(0," Software Version");
		}
		
		
		List<String> selectedCMD = new ArrayList<String>();

		for (String str : this.selectedValue) {
			selectedCMD.add(this.CMDMap.get(str));
		}

		List<List<String>> list = telnetService.getNewVersion(selectedCMD);
		this.setTblist(list);

		return Action.SUCCESS;
	}

	public static void main(String[] args) {
		TelnetAction ta = new TelnetAction();
		ta.execute();

		System.out.println(ta.getTblist());

	}
}
