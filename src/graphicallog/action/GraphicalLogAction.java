package graphicallog.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import graphicallog.service.*;

@Controller
public class GraphicalLogAction extends ActionSupport {

	private static final long serialVersionUID = 3635006449552025065L;

	@Resource
	private GraphicalLogService graphicLogService;

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
