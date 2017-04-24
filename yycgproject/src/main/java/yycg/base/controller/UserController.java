package yycg.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.vo.PageQuery;
import yycg.base.pojo.vo.SysuserCustom;
import yycg.base.pojo.vo.SysuserQueryVo;
import yycg.base.process.context.Config;
import yycg.base.process.result.DataGridResultInfo;
import yycg.base.process.result.ResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.service.SystemConfigService;
import yycg.base.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SystemConfigService systemConfigService;
	
	@RequestMapping("/sysuserquery")
	public String queryuser(Model model) throws Exception{
		
		List<Dictinfo> groupinfos=systemConfigService.findDictinfoByType("s01");
		List<Dictinfo> statusinfos=systemConfigService.findDictinfoByType("s02");
		model.addAttribute("groupinfos", groupinfos);
		model.addAttribute("statusinfos", statusinfos);
		return "/base/user/queryuser";
	}
	
	//用户查询页面的结果集
	//最终DataGridResultInfo通过@ResponseBody将java对象转成json
	@RequestMapping("/sysuserquery_result")
	public @ResponseBody DataGridResultInfo queryuser_result(
			SysuserQueryVo sysuserQueryVo,
			int page,//页码
			int rows//每页显示个数
			)throws Exception{

		//非空校验
		sysuserQueryVo = sysuserQueryVo!=null?sysuserQueryVo:new SysuserQueryVo();

		//查询列表的总数
		int total = userService.findSysuserCount(sysuserQueryVo);

		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);

		sysuserQueryVo.setPageQuery(pageQuery);

		//分页查询，向sysuserQueryVo中传入pageQuery
		List<SysuserCustom> list = userService.findSysuserList(sysuserQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		//填充 total
		dataGridResultInfo.setTotal(total);
		//填充  rows
		dataGridResultInfo.setRows(list);
		System.out.println(dataGridResultInfo);
		return dataGridResultInfo;
	}
	@RequestMapping("/addsysuser")
	public String addsysuser(Model model) throws Exception{
		
		List<Dictinfo> groupinfos=systemConfigService.findDictinfoByType("s01");
		model.addAttribute("groupinfos", groupinfos);
		return "/base/user/addsysuser";
	}
	
	//添加用户提交
	//提交 结果转json输出到页面
	//提交表单数据统一使用包装类
	@RequestMapping("/addsysusersubmit")
	public @ResponseBody SubmitResultInfo addsysusersubmit(SysuserQueryVo sysuserQueryVo)throws Exception{
		
//		ResultInfo resultInfo=new ResultInfo();
//		resultInfo.setMessage("添加成功！");
//		resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		 //SysuserCustom sysuserCustom= sysuserQueryVo.getSysuserCustom();
		//System.out.println(sysuserQueryVo.getSysuserCustom().getSysid());
		//使用全局异常处理器后，在actoin不用进行try/catch捕获
		userService.insertSysuser(sysuserQueryVo.getSysuserCustom());
		
//		SubmitResultInfo submitResultInfo=new SubmitResultInfo(resultInfo);
		
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE,906, null));
	}
	
	@RequestMapping("/deletesysuser")
	public @ResponseBody SubmitResultInfo deletesysuser(String id)throws Exception{
		
		userService.deleteSysuser(id);
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 906, null));
	} 
	
	@RequestMapping("/editsysuser")
	public String editSysuser(String id,Model model)throws Exception{
		
		SysuserCustom sysuserCustom=userService.findSysuserById(id);
		
		model.addAttribute("sysuserCustom", sysuserCustom);
		return "/base/user/editsysuser";
	}
	@RequestMapping("/editsysusersubmit")
	public @ResponseBody SubmitResultInfo editsysusersubmit(String id,
			SysuserQueryVo sysuserQueryVo) throws Exception{
		
		userService.updateSysuser(id, sysuserQueryVo.getSysuserCustom());
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 906, null));
	}
}
