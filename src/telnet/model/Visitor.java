package telnet.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Visitor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int visitorId;
	private Date date;
	private String ip;
	private String hostName;
	@OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Cmd> cmd;

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Cmd> getCmd() {
		return cmd;
	}

	public void setCmd(Set<Cmd> cmd) {
		this.cmd = cmd;
	}
}
