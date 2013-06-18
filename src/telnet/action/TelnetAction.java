package telnet.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
	private Set<String> selectedValue;

	public Set<String> getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(Set<String> selectedValue) {
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
		if (this.selectedValue == null)
			return Action.SUCCESS;
		if (!this.customizedCMD.isEmpty()) {
			String customizedCMD = this.customizedCMD.contains("|||") ? this.customizedCMD
					: this.customizedCMD + "|||.*";
			this.getCMDMap().put("customizedCMD", customizedCMD);
			this.selectedValue.add("customizedCMD");
		} else {
			this.selectedValue.remove("customizedCMD");
		}
		List<String> selectedCMD = new ArrayList<String>();

		for (String str : this.selectedValue) {
			selectedCMD.add(this.CMDMap.get(str));
		}

		List<List<String>> list = telnetService.getVersion(selectedCMD);
		this.setTblist(list);

		return Action.SUCCESS;
	}

	public static void main(String[] args) {
		TelnetAction ta = new TelnetAction();
		ta.execute();

		System.out.println(ta.getTblist());

	}
}
