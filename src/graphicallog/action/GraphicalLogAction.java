package graphicallog.action;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import graphicallog.service.*;
@SuppressWarnings("serial")
public class GraphicalLogAction extends ActionSupport {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	GraphicalLogService graphicLogService;

	public GraphicalLogService getGraphicLogService() {
		return graphicLogService;
	}

	public void setGraphicLogService(GraphicalLogService graphicLogService) {
		this.graphicLogService = graphicLogService;
	}
	List<LogRecord> list;

	public List<LogRecord> getList() {
		return list;
	}

	public void setList(List<LogRecord> list) {
		this.list = list;
	}
 
	public String execute() {
		list = graphicLogService.parseFile("gesys_GEHC.log");

		System.out.println("in logview action");
		return Action.SUCCESS;
	}

}
