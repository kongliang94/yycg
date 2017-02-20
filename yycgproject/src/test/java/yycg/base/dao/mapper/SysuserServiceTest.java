package yycg.base.dao.mapper;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import yycg.base.pojo.po.Sysuser;
import yycg.base.pojo.po.Usergys;
import yycg.base.service.UserService;

public class SysuserServiceTest extends TestCase{

private ApplicationContext applicationContext;

	
	private UserService userService;

	protected void setUp() throws Exception {
		// 获取spring容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"spring/applicationContext.xml", "spring/applicationContext-base-dao.xml",
				"spring/applicationContext-base-service.xml"

		});
		userService = (UserService) applicationContext.getBean("userService");
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSelectByPrimaryKey() {
		//Sysuser sysuser=null;
		Usergys usergys=null;
		try {
			usergys = userService.findUsergysByMc("河南海恩药业有限公司");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(usergys.getLxr());
	}
}
