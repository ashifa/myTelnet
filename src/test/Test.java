package test;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class Test extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 220817334781599319L;


	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub

	}
	private int count=0;
	
	@Override
	public String execute(){
		System.out.println(this.count++);
		return Action.SUCCESS;
	}

}
