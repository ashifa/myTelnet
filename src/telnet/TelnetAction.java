package telnet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TelnetAction extends ActionSupport {

	private Map<String, String> targetMap;
	private Map<String, String> CMDMap;
	private String customizedCMD;
	private List<List<String>> tblist;
	private Set<String> selectedValue;
	private int count=0;
	public Set<String> getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(Set<String> selectedValue) {
		this.selectedValue = selectedValue;
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
		System.out.println(this.count++);
		this.setTargetMap(TelnetManager.getTargetMap());
		this.setCMDMap(TelnetManager.getCMDMap());
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

		List<List<String>> list = TelnetManager.getVersion(selectedCMD);
		this.setTblist(list);

		return Action.SUCCESS;
	}

	public static void main(String[] args) {
		TelnetAction ta = new TelnetAction();
		ta.execute();

		System.out.println(ta.getTblist());

	}
}
