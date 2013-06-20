package telnet.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TargetInfo {

	private String hostName;
	private String iP;
	private String version;
	@Id
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getiP() {
		return iP;
	}

	public void setiP(String iP) {
		this.iP = iP;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
