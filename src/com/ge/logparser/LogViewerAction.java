package com.ge.logparser;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogViewerAction extends ActionSupport {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	List<LogRecord> list;

	public List<LogRecord> getList() {
		return list;
	}

	public void setList(List<LogRecord> list) {
		this.list = list;
	}
 
	public String execute() {
		LogParser.parseFile();
		this.setList(LogParser.list);
		System.out.println("in logview action");
		return Action.SUCCESS;
	}

}
