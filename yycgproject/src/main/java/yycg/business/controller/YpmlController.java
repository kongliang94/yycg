package yycg.business.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.bouncycastle.jce.provider.JDKDSASigner.noneDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.PageQuery;
import yycg.base.process.context.Config;
import yycg.base.process.result.DataGridResultInfo;
import yycg.base.process.result.ExceptionResultInfo;
import yycg.base.process.result.ResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.service.SystemConfigService;
import yycg.business.pojo.po.GysypmlControl;
import yycg.business.pojo.vo.GysypmlCustom;
import yycg.business.pojo.vo.GysypmlQueryVo;
import yycg.business.pojo.vo.YpxxCustom;
import yycg.business.service.YpmlService;

/**
 * 
 * @author KL
 *
 */
@Controller
@RequestMapping("/ypml")
public class YpmlController {

	@Autowired
	private SystemConfigService systemConfigService;

	@Autowired
	private YpmlService ypmlService;

	// 查询页面
	@RequestMapping("/gysypmlquery")
	public String querygysypml(Model model) throws Exception {
		// 药品类别
		List<Dictinfo> yplblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("yplblist", yplblist);
		
		List<Dictinfo> jyztlist=systemConfigService.findDictinfoByType("003");
		model.addAttribute("jyztlist", jyztlist);
		List<Dictinfo> ypzlcclist=systemConfigService.findDictinfoByType("004");
		model.addAttribute("zlcclist", ypzlcclist);
		
		List<Dictinfo> controllist = systemConfigService.findDictinfoByType("008");
		model.addAttribute("controllist", controllist);//控制标记，在页面进行判断

		return "/business/ypml/querygysypml";
	}

	// 查询列表结果集,json
	@RequestMapping("/querygysypml_result")
	public @ResponseBody
	DataGridResultInfo querygysypml_result(HttpSession session,
			GysypmlQueryVo gysypmlQueryVo,// 查询条件
			int page, int rows) throws Exception {

		// 当前用户
		ActiveUser activeUser = (ActiveUser) session
				.getAttribute(Config.ACTIVEUSER_KEY);
		// 用户所属的单位
		String usergysid = activeUser.getSysid();// 单位id

		// 列表的总数
		int total = ypmlService.findGysypmlCount(usergysid, gysypmlQueryVo);

		// 分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		gysypmlQueryVo.setPageQuery(pageQuery);// 设置分页参数

		// 分页查询列表
		List<GysypmlCustom> list = ypmlService.findGysypmlList(usergysid,
				gysypmlQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);

		return dataGridResultInfo;
	}

	// 查询页面
	@RequestMapping("/queryaddgysypml")
	public String queryaddgysypml(Model model) throws Exception {
		// 药品类别
		List<Dictinfo> yplblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("yplblist", yplblist);

		List<Dictinfo> jyztlist=systemConfigService.findDictinfoByType("003");
		model.addAttribute("jyztlist", jyztlist);
		List<Dictinfo> ypzlcclist=systemConfigService.findDictinfoByType("004");
		model.addAttribute("zlcclist", ypzlcclist);
		return "/business/ypml/querygysypmladd";
	}

	// 查询列表结果集,json
	@RequestMapping("/querygysypmladd_result")
	public @ResponseBody
	DataGridResultInfo queryaddgysypml_result(HttpSession session,
			GysypmlQueryVo gysypmlQueryVo,// 查询条件
			int page, int rows) throws Exception {

		// 当前用户
		ActiveUser activeUser = (ActiveUser) session
				.getAttribute(Config.ACTIVEUSER_KEY);
		// 用户所属的单位
		String usergysid = activeUser.getSysid();// 单位id

		// 列表的总数
		int total = ypmlService.findAddGysypmlCount(usergysid, gysypmlQueryVo);

		// 分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		gysypmlQueryVo.setPageQuery(pageQuery);// 设置分页参数

		// 分页查询列表
		List<GysypmlCustom> list = ypmlService.findAddGysypmlList(usergysid,
				gysypmlQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);

		return dataGridResultInfo;
	}
	@RequestMapping("/addgysypmlsubmit")
	@ResponseBody
	public SubmitResultInfo addgysypmlsubmit(
			int[] indexs,GysypmlQueryVo gysypmlQueryVo,
			HttpSession session) throws Exception{
		//未选择
		if(indexs == null || indexs.length <=0){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		
		ActiveUser user=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		int count=indexs.length;
		

		//处理成功的数量
		int count_success = 0;
		//处理失败的数量
		int count_error = 0;

		//处理失败的原因
		List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();
		List<YpxxCustom> list=gysypmlQueryVo.getYpxxCustoms();
		
		for (int i = 0; i < count; i++) {
			//系统提示信息封装类
			ResultInfo resultInfo = null;
			YpxxCustom ypxxCustom=list.get(indexs[i]);
			try {
				ypmlService.insertGysypml(user.getSysid(), ypxxCustom.getId());
			} catch (Exception e) {		
				//进行异常解析
				e.printStackTrace();
				
				if (e instanceof ExceptionResultInfo) {
					resultInfo=((ExceptionResultInfo) e).getResultInfo();
				}else {
					//构造未知错误异常
					resultInfo=ResultUtil.createFail(Config.MESSAGE, 900, null);
				}
			}
			if (resultInfo==null) {
				//说明添加成功
				count_success++;
			}else {
				count_error++;
				msgs_error.add(resultInfo);//将失败原因记录
			}
			
		}
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 907, 
				new Object[]{count_success,count_error}),msgs_error);
	}
	@RequestMapping("/deletegysypmlsubmit")
	@ResponseBody
	public SubmitResultInfo deletegysypmlsubmit(
			int[] indexs,GysypmlQueryVo gysypmlQueryVo,
			HttpSession session) throws Exception{
	
		if(indexs == null || indexs.length <=0){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		ActiveUser user=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		int count=indexs.length;
		

		//处理成功的数量
		int count_success = 0;
		//处理失败的数量
		int count_error = 0;

		//处理失败的原因
		List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();
		List<YpxxCustom> list=gysypmlQueryVo.getYpxxCustoms();
		
		
		for (int i = 0; i < count; i++) {
			//系统提示信息封装类
			ResultInfo resultInfo = null;
			YpxxCustom ypxxCustom=list.get(indexs[i]);
			try {
				ypmlService.deleteGysypmlByUsergysidAndYpxxid(user.getSysid(), ypxxCustom.getId());
			} catch (Exception e) {		
				//进行异常解析
				e.printStackTrace();
				
				if (e instanceof ExceptionResultInfo) {
					resultInfo=((ExceptionResultInfo) e).getResultInfo();
				}else {
					//构造未知错误异常
					resultInfo=ResultUtil.createFail(Config.MESSAGE, 900, null);
				}
			}
			if (resultInfo==null) {
				//说明添加成功
				count_success++;
			}else {
				count_error++;
				msgs_error.add(resultInfo);//将失败原因记录
			}
			
		}
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 907, 
				new Object[]{count_success,count_error}),msgs_error);
	}
	/**
	 * 供应商药品目录控制
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/querygysypmlcontrol")
	public String querygysypmlcontrol(Model model)throws Exception{
		List<Dictinfo> yplblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("yplblist", yplblist);
		
		List<Dictinfo> jyztlist=systemConfigService.findDictinfoByType("003");
		model.addAttribute("jyztlist", jyztlist);
		List<Dictinfo> controllist = systemConfigService.findDictinfoByType("008");
		model.addAttribute("controllist", controllist);//控制标记，在页面进行判断
		return "/business/ypml/querygysypmlcontrol";
		
	}
	@RequestMapping("/querygysypmlcontrol_result")
	@ResponseBody
	public DataGridResultInfo querygysypmlcontrol_result(HttpSession session,
			GysypmlQueryVo gysypmlQueryVo,// 查询条件
			int page, int rows)throws Exception{
		int count=ypmlService.findGysypmlControlCount(gysypmlQueryVo);
		

		// 分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(count, rows, page);
		gysypmlQueryVo.setPageQuery(pageQuery);// 设置分页参数

		// 分页查询列表
		List<GysypmlCustom> list = ypmlService.findGysypmlControlList(gysypmlQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(count);
		dataGridResultInfo.setRows(list);

		return dataGridResultInfo;

	}
	
	@RequestMapping("/gysypmlcontrolsubmit")
	@ResponseBody
	public SubmitResultInfo gysypmlcontrolsubmit(
			int[] indexs,GysypmlQueryVo gysypmlQueryVo,
			HttpSession session) throws Exception{
	
		if(indexs == null || indexs.length <=0){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		ActiveUser user=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		int count=indexs.length;
		

		//处理成功的数量
		int count_success = 0;
		//处理失败的数量
		int count_error = 0;

		//处理失败的原因
		List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();
		List<GysypmlControl> gysypmls=gysypmlQueryVo.getGysypmlControls();
		//List<YpxxCustom> gysypmls=gysypmlQueryVo.getYpxxCustoms();
		
		for (int i = 0; i < count; i++) {
			//系统提示信息封装类
			ResultInfo resultInfo = null;
			GysypmlControl gysypmlControl=gysypmls.get(indexs[i]);
			String ypxxid=gysypmlControl.getYpxxid();
			String usergysid=gysypmlControl.getUsergysid();
			ypmlService.updateGysypmlControl(ypxxid,usergysid,gysypmlControl.getControl(),gysypmlControl.getAdvice());
			try {
			} catch (Exception e) {		
				//进行异常解析
				e.printStackTrace();
				
				if (e instanceof ExceptionResultInfo) {
					resultInfo=((ExceptionResultInfo) e).getResultInfo();
				}else {
					//构造未知错误异常
					resultInfo=ResultUtil.createFail(Config.MESSAGE, 900, null);
				}
			}
			if (resultInfo==null) {
				//说明添加成功
				count_success++;
			}else {
				count_error++;
				msgs_error.add(resultInfo);//将失败原因记录
			}
			
		}
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 907, 
				new Object[]{count_success,count_error}),msgs_error);
	}
}
