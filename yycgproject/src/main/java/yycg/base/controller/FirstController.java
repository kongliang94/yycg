package yycg.base.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.po.Sysuser;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.Menu;
import yycg.base.process.context.Config;
import yycg.base.service.UserService;

@Controller
public class FirstController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/first")
	public String first(Model model) throws Exception{
		
		
		return "/base/first";
	}
	//欢迎页面
		@RequestMapping("/welcome")
		public String welcome()throws Exception{
			
			return "/base/welcome";
		}
		@RequestMapping("/usermenu")
		@ResponseBody
		public Menu usermenu(HttpSession session)throws Exception{
			ActiveUser activeUser=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
			return activeUser.getMenu();
		}
 }
