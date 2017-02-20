package yycg.base.process.exception;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import yycg.base.process.result.ExceptionResultInfo;
import yycg.base.process.result.ResultInfo;

/**
 * 全局异常处理器,全局需要在web.xml中配置，关闭spring默认的异常处理器。
 * @author KL
 *
 */
public class ExceptionResolverCustom implements HandlerExceptionResolver{

	
	// json转换器
	// 将异常信息转json
	private HttpMessageConverter<ExceptionResultInfo> jsonMessageConverter;
	
	public HttpMessageConverter<ExceptionResultInfo> getJsonMessageConverter() {
		return jsonMessageConverter;
	}
	//依赖注入三种方式之一setter注入
	public void setJsonMessageConverter(
			HttpMessageConverter<ExceptionResultInfo> jsonMessageConverter) {
		this.jsonMessageConverter = jsonMessageConverter;
	}

	// 前端控制器调用此方法执行异常处理
	// handler，执行的action类就包装了一个方法（对应url的方法）
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// 输出 异常信息
		ex.printStackTrace();
		// 转成springmvc底层对象（就是对action方法的封装对象，只有一个方法(因为springMvc面向方法开发)）
		HandlerMethod handlerMethod=(HandlerMethod) handler;		
		// 取出方法
		Method method = handlerMethod.getMethod();
		
		// 判断方法是否返回json
		// 只要方法上有responsebody注解表示返回json，
		// 没有responsebody注解是返回jsp页面，两者都会异常处理方式不同，具体如下
		// 查询method是否有responsebody注解
		
		ResponseBody responseBody=AnnotationUtils.findAnnotation(method, 
				ResponseBody.class);
		if (responseBody!=null) {
			// 将异常信息转json输出
			return this.resolveJsonException(request, response, handlerMethod,
								ex);
		}
	
		// 这里说明action返回的是jsp页面
		// 解析异常
		ExceptionResultInfo exceptionResultInfo = resolveExceptionCustom(ex);
		
		
		String view = "/base/error";
		//异常代码
		int messageCode = exceptionResultInfo.getResultInfo().getMessageCode();
		//如果是106则跳转到登陆
		if(messageCode==106){
			//跳转到登陆
			view = "/base/login";
		}
		// 将异常信息在异常页面显示
		request.setAttribute("exceptionResultInfo", 
				exceptionResultInfo.getResultInfo());
		// 转向错误页面
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("exceptionResultInfo", 
				exceptionResultInfo.getResultInfo());
		modelAndView.setViewName(view);//逻辑视图名，error.jsp
		return modelAndView;
	}
	
	// 异常信息解析方法(一种是系统异常，一种是Runtime异常)
	private ExceptionResultInfo resolveExceptionCustom(Exception ex) {
		ResultInfo resultInfo=null;
		
		if (ex instanceof ExceptionResultInfo) {
			//抛出系统自定义异常
			resultInfo=((ExceptionResultInfo) ex).getResultInfo();
		}else {
			// 重新构造“未知错误”异常,比如runtimeException
			resultInfo = new ResultInfo();
			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("未知错误！");
		}
		return new ExceptionResultInfo(resultInfo);
	}
	
	// 将异常信息转json输出
	private ModelAndView resolveJsonException(HttpServletRequest request,
			HttpServletResponse response, HandlerMethod handlerMethod,
			Exception ex) {
		// 异常信息解析
		ExceptionResultInfo exceptionResultInfo = resolveExceptionCustom(ex);

		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		
		try {
			//将exceptionResultInfo对象转成json输出,这里使用spring异常处理的工具
			jsonMessageConverter.write(exceptionResultInfo, MediaType.APPLICATION_JSON, outputMessage);
		} catch (HttpMessageNotWritableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView();
	}

}
