package telnet.action;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import telnet.model.Cmd;
import telnet.model.Visitor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@Transactional
public class Test extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 220817334781599319L;

	private static EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		System.out.println("setting em" + em);
		Test.em = em;
	}

	private int count = 0;
	private static int sc = 0;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String execute() {
		System.out.println(this.count++);
		System.out.println(Test.sc++);
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemoteHost());
		System.out.println(request.getRemotePort());
		System.out.println(request.getRemoteUser());

		Visitor vis = new Visitor();
		vis.setIp(request.getRemoteAddr());
		vis.setHostName(request.getRemoteHost());
		vis.setDate(new Date());
		Cmd a = new Cmd();
		a.setName("11");
		a.setValue("value");
		a.setVisitor(vis);
		Cmd b = new Cmd();
		b.setName("22");
		b.setValue("value");
		b.setVisitor(vis);
		HashSet<Cmd> set = new HashSet<Cmd>();
		set.add(a);
		set.add(b);
		vis.setCmd(set);

		em.merge(vis);
		Query query = Test.em.createQuery("select p FROM Visitor p");
		@SuppressWarnings("unchecked")
		List<Visitor> list = query.getResultList();
		for (Visitor itr : list) {
			System.out.println(itr.getHostName());
			System.out.println(itr.getIp());
			System.out.println(itr.getDate());
			System.out.println(itr.getCmd());
		}

		return Action.SUCCESS;
	}

	@Override
	protected void finalize() {
		System.out.println("bye" + this.count);
	}

}
