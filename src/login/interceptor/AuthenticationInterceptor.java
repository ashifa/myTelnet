package login.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Action;

public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = -7427150861491009317L;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext()
				.getSession();

		boolean isAuthenticated = session.containsKey("user");

		if (!isAuthenticated) {
			return Action.LOGIN;
		} else {
			return actionInvocation.invoke();
		}
	}

}
