package com.ge.logparser;

import java.util.Date;

class LogRecord{
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