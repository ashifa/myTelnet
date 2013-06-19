package graphicallog.service;

import java.util.Date;

public class LogRecord{
	Date date;
	String content;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LogRecord(Date date,String content){
		this.date=date;
		this.content=content;
	}
}