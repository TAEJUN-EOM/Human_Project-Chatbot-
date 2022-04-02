package lab.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TimeCheckInterceptor implements HandlerInterceptor{
    private long start, end;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("=== preHandle()호출 ==="); 
		start = System.currentTimeMillis();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("=== postHandle()호출 ==="); 
		end = System.currentTimeMillis();
		String handlerClass = handler.getClass().getName(); 
		System.out.println(handlerClass+" 실행에 걸린 시간 : " + (end-start)+"(ms)초"); 
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("=== afterCompletion()호출 ==="); 
	}

}
