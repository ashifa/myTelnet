package telnet.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class Test extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 220817334781599319L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		return Action.SUCCESS;
	}

	@Override
	protected void finalize() {
		System.out.println("bye"+this.count);
	}

}
