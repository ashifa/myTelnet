package telnet.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import telnet.service.AdminService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class TelnetAdminAction extends ActionSupport {

	private static final long serialVersionUID = 220817334781599319L;
	@Resource 
	private AdminService adminService;

	private Map<String, String> CMDMap;
	private Map<String, String> targetMap;
	private String editKey;
	private String editValue;
	private boolean editFlag;

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public String getEditKey() {
		return editKey;
	}

	public void setEditKey(String editKey) {
		this.editKey = editKey;
	}

	public String getEditValue() {
		return editValue;
	}

	public void setEditValue(String editValue) {
		this.editValue = editValue;
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

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	@Override
	public String execute() {
		CMDMap = this.adminService.getCMDMap();
		targetMap = this.adminService.getTargetMap();

		return Action.SUCCESS;
	}

	public String remove() {
		System.out.println("remove" + this.editKey);
		this.adminService.RemoveCMD(editKey);
		this.editKey = "";
		return execute();
	}

	public String edit() {
		if (this.editFlag == true) {
			this.editFlag = false;
		} else {
			this.adminService.RemoveCMD(editKey);
			this.adminService.AddCMD(editKey, editKey);
			this.editKey = "";
			this.editValue = "";
		}
		return execute();
	}

}
