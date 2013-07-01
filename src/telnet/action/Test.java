package telnet.action;

import java.util.Map;

import telnet.service.AdminService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class Test extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 220817334781599319L;

	private AdminService adminService;

	private Map<String, String> CMDMap;
	private Map<String, String> targetMap;
	private String mapKey;

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public Map<String, String> getCMDMap() {
		return CMDMap;
	}

	public void setCMDMap(Map<String, String> cMDMap) {
		CMDMap = cMDMap;
	}

	public Map<String, String> getTargetMap() {
		return targetMap;
	}

	public void setTargetMap(Map<String, String> targetMap) {
		this.targetMap = targetMap;
	}

	@Override
	public String execute() {
		CMDMap = this.adminService.getCMDMap();
		targetMap = this.adminService.getTargetMap();

		return Action.SUCCESS;
	}

	public String remove() {
		System.out.println("remove" + this.mapKey);
		this.adminService.RemoveCMD(mapKey);
		return execute();
	}
	
	public String edit(){
		return execute();
	}

}
