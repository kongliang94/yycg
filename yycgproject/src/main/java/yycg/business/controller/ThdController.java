package yycg.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.po.Useryy;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.PageQuery;
import yycg.base.pojo.vo.UseryyCustom;
import yycg.base.process.context.Config;
import yycg.base.process.result.DataGridResultInfo;
import yycg.base.process.result.ExceptionResultInfo;
import yycg.base.process.result.ResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.process.result.View;
import yycg.base.service.SystemConfigService;
import yycg.base.service.UserService;
import yycg.business.pojo.po.Yythd;
import yycg.business.pojo.po.Yythdmx;
import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;
import yycg.business.service.CgdService;
import yycg.business.service.ThdService;
import yycg.util.MyUtil;
@Controller
@RequestMapping("/thd")
public class ThdController {

	@Autowired
	private UserService userService;
	@Autowired
	private ThdService thdService;
	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private CgdService cgdService;
	
	/**
	 * 退货单编辑
	 */
	@RequestMapping("/yythdadd")
	public String yythdadd(ActiveUser activeUser,Model model)throws Exception{
		
		//获取医院
		String sysmc=activeUser.getSysmc();
		Yythd yythd = new Yythd();

		//准备添加采购单
		//采购单名称
		String yythdmc = sysmc+MyUtil.getDate()+"退货单";
		yythd.setMc(yythdmc);		
		model.addAttribute("yythd", yythd);
		return View.toBusiness("/thd/yythdadd"); 
	}
	
	@RequestMapping("/yythdsave")
	@ResponseBody
	public SubmitResultInfo yythdsave(ActiveUser activeUser,String year,YycgdQueryVo yycgdQueryVo)throws Exception{
		
		String useryyid=activeUser.getSysid();
		Yythd yythd =null;
		if(yycgdQueryVo!=null){
			yythd = yycgdQueryVo.getYythd();
		}
		yycgdQueryVo.setBusinessyear(year);
		if(yythd==null){
			yythd = new Yythd();
		}

		yythd.setUseryyid(activeUser.getSysid());
		if(yythd.getId()!=null){
			
		}else {
			//添加时必须选择年
			if(year == null || year.equals("")||!MyUtil.isDate(year, "yyyy")){
				ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE,909, null));
			}
			String yythdid= thdService.insertYythd(year,yythd);
			yythd.setId(yythdid);
		}
		ResultInfo resultInfo= ResultUtil.createSuccess(Config.MESSAGE, 906, null);

		//获取采购单id，将id通过ResultInfo中sysdata传到页面
		/*Map<String, Object> map=new HashMap<String, Object>();
		map.put("yythdid", yythd.getBm());*/
		resultInfo.getSysdata().put("yythdid", yythd.getId());
		resultInfo.getSysdata().put("year", year);
		return ResultUtil.createSubmitResult(resultInfo);
	}

	// 采购单修改页面方法
	@RequestMapping("/yythdedit")
	public String yythdedit(Model model, String yythdid) throws Exception {

		//退货单信息
		Yythd yythdedit = thdService.findYythdById(yythdid);
		if(yythdedit==null){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE,902, null));
		}

		//修改退货单相关信息
		if(yythdedit.getZt().equals("2")){//退货单已提交不允许修改
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE,610, null));
		}

		String year=yythdid.substring(0,4);
		model.addAttribute("year",year);
		model.addAttribute("yythd", yythdedit);
		return View.toBusiness("/thd/yythdedit");

	}
	
	
	/**
	 * 退货单维护列表
	 */
	@RequestMapping("/yythdmanager")
	public String yycgdmanager(Model model) throws Exception{
		model.addAttribute("year", MyUtil.get_YYYY(MyUtil.getDate()));
		return View.toBusiness("/thd/yythdmanager");
	}


	@RequestMapping("/yythdmanager_result")
	@ResponseBody
	public DataGridResultInfo yythdmanager_result(ActiveUser activeUser,/*@RequestParam(value = "year", required = true) */String year,
			YycgdQueryVo yycgdQueryVo, int page, int rows)throws Exception{
		String useryyid=activeUser.getSysid();
		
		if(year == null){
        	ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,909, null));
        }
        
		if(yycgdQueryVo == null){
			yycgdQueryVo = new YycgdQueryVo();
		}
		//退货单状态为未提交
        YycgdCustom yycgdCustom = yycgdQueryVo.getYycgdCustom();
        if(yycgdCustom == null){
        	yycgdCustom = new YycgdCustom();
        }
        yycgdCustom.setThdzt("1");
        yycgdQueryVo.setYycgdCustom(yycgdCustom);
        //设置医院ID
        UseryyCustom useryyCustom=yycgdQueryVo.getUseryyCustom();
        if (useryyCustom==null) {
			useryyCustom=new UseryyCustom();
		}
        useryyCustom.setId(useryyid);
        
        yycgdQueryVo.setUseryyCustom(useryyCustom);
        yycgdQueryVo.setBusinessyear(year);
		//列表的总数
		int total = thdService.findYythdCount(yycgdQueryVo);
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		yycgdQueryVo.setPageQuery(pageQuery);// 设置分页参数
		List<YycgdCustom> list= thdService.findYythdList(yycgdQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}
	
	@RequestMapping("/yythdmxadd")
	public String yythdmxadd(@RequestParam(value = "yythdid", required = true) String yythdid,Model model) throws Exception{
		//获取医院信息
		model.addAttribute("yythdid", yythdid);
		model.addAttribute("year", yythdid.substring(0, 4));
		findThdBaseData(model);
		return View.toBusiness("/thd/yythdmxadd");
	} 
	@RequestMapping("/yythdmxadd_result")
	@ResponseBody
	public DataGridResultInfo yythdmxadd_result(ActiveUser activeUser,String yythdid,
			YycgdQueryVo yycgdQueryVo, int page, int rows)throws Exception{
		String useryyid=activeUser.getSysid();
		String year=yythdid.substring(0, 4);
		if(yycgdQueryVo == null){
			yycgdQueryVo = new YycgdQueryVo();
		}

		YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
		if(yycgdmxCustom==null){
			yycgdmxCustom = new YycgdmxCustom();
		}
	
		yycgdmxCustom.setCgzt("3");
		yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);
		YycgdCustom yycgdCustom = yycgdQueryVo.getYycgdCustom();
		if(yycgdCustom==null){
			yycgdCustom = new YycgdCustom();
		}
		//添加退货单id，对本退货单以外的药品执行退货
		yycgdCustom.setYythdid(yythdid);
		//只查询已入库
		//yycgdCustom.setZt("3");
		yycgdQueryVo.setYycgdCustom(yycgdCustom);
		//获取医院信息
		UseryyCustom useryyCustom = new UseryyCustom();
		useryyCustom.setId(activeUser.getSysid());
		yycgdQueryVo.setUseryyCustom(useryyCustom);

		//获取记录总数 
		int total=0;		
		total = cgdService.findYycgdrkCount(useryyid,year,yycgdQueryVo);
		//列表的总数
		//int total = thdService.findYythdCount(yycgdQueryVo);
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		yycgdQueryVo.setPageQuery(pageQuery);// 设置分页参数
		List<YycgdmxCustom> list= cgdService.findYycgdrkList(useryyid, year, yycgdQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}
	/**
	 * 退货单修改页面，退货药品明细列表
	 */
	@RequestMapping("/yythdedit_thdmxresult")
	public @ResponseBody
	DataGridResultInfo yythdedit_thdmxresult(
			/*@RequestParam(value = "yythdid", required = true)*/ String yythdid,
			YycgdQueryVo yycgdQueryVo, int page, int rows) throws Exception {
		
		String year= yythdid.substring(0, 4);
		if(yycgdQueryVo == null){
			yycgdQueryVo = new YycgdQueryVo();
		}
		YycgdCustom yycgdCustom = yycgdQueryVo.getYycgdCustom();
		if(yycgdCustom == null){
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setYythdid(yythdid);
		yycgdQueryVo.setYycgdCustom(yycgdCustom);
		return yythdmx_result(year, yycgdQueryVo, page, rows);
		
	}
	
	/**
	 * 退货药品添加提交
	 */
	@RequestMapping("/yythdmxaddsubmit")
	public @ResponseBody
	SubmitResultInfo yythdmxaddsubmit(
			@RequestParam(value = "yythdid", required = true) String yythdid,
			int[] indexs, YycgdQueryVo yycgdQueryVo) throws Exception {
	
		//未选择数据项目
		if(indexs == null || indexs.length <=0){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		if(yycgdQueryVo == null){
			yycgdQueryVo = new YycgdQueryVo();
		}
		
		//选择添加的退货药品列表
		List<Yythdmx> yythdmxs = yycgdQueryVo.getYythdmxs();
		
		if(yythdmxs == null || yythdmxs.size() <indexs.length ){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}

		//操作总数
		int totalCount = indexs.length;
		int failed=0;
		int success = 0;
		List<ResultInfo> errorInfos = new ArrayList<ResultInfo>();
		for (int m = 0; m < totalCount; m++) {
			int index = indexs[m]; 
			Yythdmx yythdmx = yythdmxs.get(index);
			yythdmx.setYythdid(yythdid);//退货单id
			ResultInfo result=null;
			try{
				thdService.insertYythdmx(yythdid,yythdmx);
			}catch(Exception e){
				//捕获退货单抛出的异常
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
	 * 获取基础分类数据
	 * @param model
	 * @throws Exception
	 */
	private void findThdBaseData(Model model) throws Exception{
		//药品类别
		List yplbList = systemConfigService.findDicttypeinfolist("001");
		//药品状态类别
		List ypztList = systemConfigService.findDicttypeinfolist("002");
		//质量层次
		List ypzlccList = systemConfigService.findDicttypeinfolist("004");
		//交易状态
		List ypjyztList = systemConfigService.findDicttypeinfolist("003");
		
		//采购状态
		List cgztList = systemConfigService.findDicttypeinfolist("011");
		//退货状态
		List thztList = systemConfigService.findDicttypeinfolist("013");

		model.addAttribute("yplbList", yplbList);
		model.addAttribute("ypztList", ypztList);
		model.addAttribute("ypzlccList", ypzlccList);
		model.addAttribute("ypjyztList", ypjyztList);
		
		model.addAttribute("cgztList", cgztList);
		model.addAttribute("thztList", thztList);
	}
	/**
	 * 退货单采购药品明细(公用)
	 */
	private DataGridResultInfo yythdmx_result(String year,YycgdQueryVo yycgdQueryVo,int page,int rows)throws Exception{
		
		if(yycgdQueryVo == null){
			yycgdQueryVo = new YycgdQueryVo();
		}
		
		//获取记录总数 
		int count=0;
		count = thdService.findYythdmxCount(year,yycgdQueryVo);
		
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(count, rows, page);
		
		//设置分页查询参数
		yycgdQueryVo.setPageQuery(pageQuery);
		
		//查询出结果集
		List<YycgdCustom> list = thdService.findYythdmxList(year,yycgdQueryVo);
		if(list == null){
			list = new ArrayList<YycgdCustom>();
		}
		//封装成查询结果数据
		DataGridResultInfo queryResultInfo = ResultUtil.createQueryResult(ResultUtil.createSuccess(Config.MESSAGE,906, null));
		queryResultInfo.setRows(list);
		queryResultInfo.setTotal(count);
		return queryResultInfo;
	}
	
}
