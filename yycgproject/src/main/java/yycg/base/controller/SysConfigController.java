package yycg.base.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yycg.base.pojo.vo.ActiveUser;
import yycg.base.process.context.Config;
import yycg.util.ResourcesUtil;
import yycg.util.Vcom_3DES;

/**
 * 调用第三方系统的接口
 * @author KL
 *
 */
@Controller
@RequestMapping("/sysconfig")
public class SysConfigController {

	public String buildUrl(HttpSession session,String url_key)throws Exception{
		

		ActiveUser activeUser=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		
		String userid=activeUser.getUserid();
		//最多24个字节，最少？
		String key=ResourcesUtil.getValue(Config.SYSCONFIG, "deskey");
		//待加密串
		String oldstring =userid + "#" + "test" + "#" + System.currentTimeMillis();
		
		//进行3des加密，1表示加密，key：密钥
		Vcom_3DES tempDesEn = new Vcom_3DES(1, oldstring,
				key);
		//加密后的串
		String strTemp = tempDesEn.Vcom3DESChiper();
		String model_url=ResourcesUtil.getValue(Config.SYSCONFIG, url_key);
		String url=model_url+strTemp;
		return View.redirect(url);
	}
	@RequestMapping("/modulelist")
	public String modulelist(HttpSession session)throws Exception{
		
		return buildUrl(session,"sysmanagerurl_modulelist");
		
	}
	@RequestMapping("/rolelist")
	public String rolelist(HttpSession session)throws Exception{

		return buildUrl(session,"sysmanagerurl_rolelist");
	}
	/**
	 * 系统参数配置
	 */
	@RequestMapping("/basicinfo")
	public String basicinfo(HttpSession session)throws Exception{		
		return buildUrl(session,"sysconfigurl_basicinfo");
	}
	
	@RequestMapping("/arealist")
	public String arealist(HttpSession session) throws Exception{
		return buildUrl(session, "sysmanagerurl_arealist");
	}
		
}
