package telnet.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = -1143688120095248745L;

	private String user;
	private String password;
	private Map<String, Object> session;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() {
		System.out.println("in login Action");
		if (user != null && !user.isEmpty() && user.equals(password)) {
			this.session.put("user", user);
			return Action.SUCCESS;
		} else
			return Action.INPUT;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;

	}

}
