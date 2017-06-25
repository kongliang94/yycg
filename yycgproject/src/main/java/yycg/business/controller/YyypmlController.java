package yycg.business.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.po.Usergysarea;
import yycg.base.pojo.po.Useryy;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.PageQuery;
import yycg.base.process.context.Config;
import yycg.base.process.result.DataGridResultInfo;
import yycg.base.process.result.ExceptionResultInfo;
import yycg.base.process.result.ResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.process.result.View;
import yycg.base.service.UserService;
import yycg.business.pojo.po.Yyypml;
import yycg.business.pojo.vo.YyypmlCustom;
import yycg.business.pojo.vo.YyypmlQueryVo;
import yycg.business.service.YyypmlService;

/**
 * 医院药品目录action
 * @author 
 *
 */

@Controller
@RequestMapping("/ypml")
public class YyypmlController {
	

	@Autowired
	private UserService userService;
	@Autowired
	private YyypmlService yyypmlService;
	
	/**
	 * 医院药品目录查询列表
	 */
	private String yyypmlqueryshow(Model model,String view) throws Exception{
		/*//药品类别
		List<Dictinfo> yplbList = baseServiceFacade.getSystemConfigService().findDicttypeinfolist("001");
		//药品状态类别
		List<Dictinfo> ypztList = baseServiceFacade.getSystemConfigService().findDicttypeinfolist("002");
		//质量层次
		List<Dictinfo> ypzlccList = baseServiceFacade.getSystemConfigService().findDicttypeinfolist("004");
		//交易状态
		List<Dictinfo> ypjyztList = baseServiceFacade.getSystemConfigService().findDicttypeinfolist("003");
		//供货状态
		List<Dictinfo> ypghztList = baseServiceFacade.getSystemConfigService().findDicttypeinfolist("008");
				*/
		
		
		return View.toBusiness(view);
	}
	
	/**
	 * 医院药品目录维护列表
	 */
	@RequestMapping("/yyypmlquery")
	public String yyypmlquery(Model model) throws Exception{
		return yyypmlqueryshow(model,"/ypml/yyypmlquery");
	}
	
	/**
	 * 医院药品目录查询结果集
	 */
	@RequestMapping("/yyypmlquery_result")
	public @ResponseBody DataGridResultInfo yyypmlquery_result(ActiveUser activeUser,YyypmlQueryVo yyypmlQueryVo,int page,int rows) throws Exception{
		
		if(yyypmlQueryVo == null){
			yyypmlQueryVo = new YyypmlQueryVo();
		}
		//获取医院信息
		Useryy useryy = userService.getUseryyById(activeUser.getSysid());
		yyypmlQueryVo.setUseryy(useryy);
		
		//获取记录总数 
		int count=0;
		count = yyypmlService.findYyypmlCount(yyypmlQueryVo);
		
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(count, rows, page);
		
		//设置分页查询标记
		//yyypmlQueryVo.setIscount(1);
		//设置分页查询参数
		yyypmlQueryVo.setPageQuery(pageQuery);
		
		//查询出结果集
		List<YyypmlCustom> list = yyypmlService.findYyypmlList(yyypmlQueryVo);
		if(list == null){
			list = new ArrayList<YyypmlCustom>();
		}
		//封装成查询结果数据
		DataGridResultInfo queryResultInfo = ResultUtil.createQueryResult(ResultUtil.createSuccess(Config.MESSAGE,906, null));
		queryResultInfo.setRows(list);
		queryResultInfo.setTotal(count);
		return queryResultInfo;
	}
	
	/**
	 * 医院药品目录添加列表
	 */
	@RequestMapping("/yyypmladdquery")
	public String yyypmladdquery(Model model) throws Exception{
		return yyypmlqueryshow(model,"/ypml/yyypmladdquery");
	}
	
	
	/**
	 * 医院药品目录添加查询结果集
	 */
	@RequestMapping("/yyypmladdquery_result")
	public @ResponseBody DataGridResultInfo yyypmladdquery_result(ActiveUser activeUser,YyypmlQueryVo yyypmlQueryVo,int page,int rows) throws Exception{
		
		if(yyypmlQueryVo == null){
			yyypmlQueryVo = new YyypmlQueryVo();
		}
		//获取医院信息
		Useryy useryy = userService.getUseryyById(activeUser.getSysid());
		//医院药品目录参数
		Yyypml yyypml = yyypmlQueryVo.getYyypml();
		if(yyypml==null){
			yyypml = new Yyypml();
		}
		yyypml.setUseryyid(useryy.getId());
		yyypmlQueryVo.setYyypml(yyypml);
		
		//供货区域
		Usergysarea usergysarea = new Usergysarea();
		usergysarea.setAreaid(useryy.getDq());
		//yyypmlQueryVo.setUsergysarea(usergysarea);
		
		//获取记录总数 
		int count=0;
		count = yyypmlService.findYyypmlAddCount(yyypmlQueryVo);
		
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(count, rows, page);
		
		//设置分页查询标记
		//yyypmlQueryVo.setIscount(1);
		//设置分页查询参数
		yyypmlQueryVo.setPageQuery(pageQuery);
		
		//查询出结果集
		List<YyypmlCustom> list = yyypmlService.findYyypmlAddList(yyypmlQueryVo);
		if(list == null){
			list = new ArrayList<YyypmlCustom>();
		}
		//封装成查询结果数据
		DataGridResultInfo queryResultInfo = ResultUtil.createQueryResult(ResultUtil.createSuccess(Config.MESSAGE,906, null));
		queryResultInfo.setRows(list);
		queryResultInfo.setTotal(count);
		return queryResultInfo;
	}
	
	/**
	 * 医院药品目录添加提交
	 */
	@RequestMapping("/yyypmladd")
	public @ResponseBody SubmitResultInfo yyypmladdSubmit(ActiveUser activeUser,int[] indexs,YyypmlQueryVo yyypmlQueryVo) throws Exception{
		//未选择数据项目
		if(indexs == null || indexs.length <=0){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		if(yyypmlQueryVo == null){
			yyypmlQueryVo = new YyypmlQueryVo();
		}
		//医院单位id
		String useryyid = activeUser.getSysid();
		
		//选择添加的药品和供货商
		List<Yyypml> yyypmllist = yyypmlQueryVo.getYyypmls();
		
		if(yyypmllist == null || yyypmllist.size() <indexs.length ){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		//添加药品至供货目录 
		//操作总数
		int totalCount = indexs.length;
		int failed=0;
		int success = 0;
		List<ResultInfo> errorInfos = new ArrayList<ResultInfo>();
		for (int m = 0; m < totalCount; m++) {
			int index = indexs[m]; 
			Yyypml yyypml = yyypmllist.get(index);
			
			ResultInfo result=null;
			try{
				yyypmlService.insertYyypml(useryyid,yyypml.getYpxxid(),yyypml.getUsergysid());
			}catch(Exception e){
				//捕获审核采购单抛出的异常
				if(e instanceof ExceptionResultInfo){
					e = (ExceptionResultInfo)e;
					result = ((ExceptionResultInfo) e).getResultInfo();
				}else{
					e.printStackTrace();
					result = ResultUtil.createFail(Config.MESSAGE, 900, null);
				}
			}
			if(result == null){
				success++;
			}else{
				result.setIndex(index+1);
				errorInfos.add(result);
				failed++;
			}
		}
		
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE,907, new Object[]{success+"",failed+""}),errorInfos);
		
	}
	
	/**
	 * 供货商供货目录删除药品
	 */
	@RequestMapping("/yyypmldelete")
	public @ResponseBody SubmitResultInfo yyypmldelete(ActiveUser activeUser,int[] indexs,YyypmlQueryVo yyypmlQueryVo)throws Exception{
		
		//未选择数据项目
		if(indexs == null || indexs.length <=0){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		if(yyypmlQueryVo == null){
			yyypmlQueryVo = new YyypmlQueryVo();
		}
		//供货商单位ids
		String useryyid = activeUser.getSysid();

		//选择删除的药品和供货商
		List<Yyypml> yyypmllist = yyypmlQueryVo.getYyypmls();
		
		//添加药品至供货目录 
		//操作总数
		int totalCount = indexs.length;
		int failed=0;
		int success = 0;
		List<ResultInfo> errorInfos = new ArrayList<ResultInfo>();
		for (int m = 0; m < totalCount; m++) {
			int index = indexs[m]; 
			Yyypml yyypml = yyypmllist.get(index);
			
			ResultInfo result=null;
			try{
				yyypmlService.deleteYyypml(useryyid,yyypml.getYpxxid(), yyypml.getUsergysid());
			}catch(Exception e){
				//捕获审核采购单抛出的异常
				if(e instanceof ExceptionResultInfo){
					e = (ExceptionResultInfo)e;
					result = ((ExceptionResultInfo) e).getResultInfo();
				}else{
					e.printStackTrace();
					result = ResultUtil.createFail(Config.MESSAGE, 900, null);
				}
			}
			if(result == null){
				success++;
			}else{
				result.setIndex(index+1);
				errorInfos.add(result);
				failed++;
			}
		}
		
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE,907, new Object[]{success+"",failed+""}),errorInfos);
		
	}
	
	
	
}
