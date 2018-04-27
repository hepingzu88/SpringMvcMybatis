package yyd.yun.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import yyd.yun.beans.Operator;
import yyd.yun.constants.AdminConstant;


/**
 * 
 * @author yyd
 * 针对springMVC
 */

public class LoginInterceptor implements HandlerInterceptor{
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	/**
	 * 该方法在Controller处理之前进行调用
	 * 如果返回false 就不能执行到controller
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod method=(HandlerMethod) handler;;
		logger.info("method:"+method.getMethod().getName());
		HttpSession session = request.getSession();
		Operator admin = (Operator) session.getAttribute(AdminConstant.SESSION_ADMINISTRATOR);
		if(admin==null){
			if(isAjax(request)){
				response.getWriter().write(" no login ");
				return false;
			}else{
				response.sendRedirect("/admin/login");
			}
		}
		return true;
	}

	private static boolean isAjax(HttpServletRequest request){
		return  (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())) ;
	}
	/**
	 * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之后,
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	
	
	/**
	 * 	该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行完成之后才会执行
	 *	这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。 
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
