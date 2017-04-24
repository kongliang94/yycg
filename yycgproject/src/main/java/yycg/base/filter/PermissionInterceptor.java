package yycg.base.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.Operation;
import yycg.base.process.context.Config;
import yycg.util.ResourcesUtil;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//校验用户访问是否是公开资源 地址
		List<String> open_urls = ResourcesUtil.gekeyList(Config.ANONYMOUS_ACTIONS);

		//用户访问的url
		String url = request.getRequestURI();
		for(String open_url:open_urls){
			if(url.indexOf(open_url)>=0){
				//如果访问的是公开 地址则放行
				return true;
			}
		}
		//校验是否是公共权限
		//获取公共权限 地址
		List<String> common_urls=ResourcesUtil.gekeyList(Config.COMMON_ACTIONS);
		for(String common_url:common_urls){
			if(url.indexOf(common_url)>=0){
				//如果访问的是公开 地址则放行
				return true;
			}
		}
		ActiveUser activeUser=(ActiveUser) request.getSession().getAttribute(Config.ACTIVEUSER_KEY);
		List<Operation> operations=activeUser.getOperationList();
		for (Operation operation : operations) {
			String op_url=operation.getActionUrl();
			if (url.indexOf(op_url)>0) {
				return true;
			}
		}
		
		//提示用户无此操作权限
		//跳转到无此操作权限操作页面
		request.getRequestDispatcher("/WEB-INF/jsp/base/refuse.jsp").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
