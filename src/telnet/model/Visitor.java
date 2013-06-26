package telnet.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Visitor {

private String ip;
private String hostName;
private Date date;
@Id
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

}
