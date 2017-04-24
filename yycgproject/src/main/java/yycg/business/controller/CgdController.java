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
import yycg.business.pojo.po.Yycgd;
import yycg.business.pojo.po.Yycgdmx;
import yycg.business.pojo.vo.GysypmlQueryVo;
import yycg.business.pojo.vo.YpxxCustom;
import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;
import yycg.business.pojo.vo.YycgdrkCustom;
import yycg.business.service.CgdService;
import yycg.util.MyUtil;

@Controller
@RequestMapping("/cgd")
public class CgdController {
	@Autowired
	private CgdService cgdService;

	@Autowired
	private SystemConfigService systemConfigService; 

	//创建采购单基本信息页面
	@RequestMapping("/addcgd")
	public String addcgd(HttpSession session,Model model)throws Exception{

		//对采购单进行初始化
		ActiveUser activeUser=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		String sysmc=activeUser.getSysmc();

		String currentDate=MyUtil.getDate();
		String yycgdmc=sysmc+currentDate+"采购单";
		String year=MyUtil.get_YYYY(currentDate);
		model.addAttribute("yycgdmc", yycgdmc);
		model.addAttribute("year", year);
		return "/business/cgd/addcgd";
	}

	//创建采购单基本信息保存
	@RequestMapping("/addcgdsubmit")
	@ResponseBody
	public SubmitResultInfo addcgdsubmit(HttpSession session,String year,YycgdQueryVo yycgdQueryVo)throws Exception{
		ActiveUser activeUser= (ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		String useryyid=activeUser.getSysid();
		//String yycgdid="2017100012";//
		String yycgdid=cgdService.insertYycgd(useryyid, year, yycgdQueryVo.getYycgdCustom());

		ResultInfo resultInfo= ResultUtil.createSuccess(Config.MESSAGE, 906, null);

		//获取采购单id，将id通过ResultInfo中sysdata传到页面
		resultInfo.getSysdata().put("yycgdid", yycgdid);
		return ResultUtil.createSubmitResult(resultInfo);
	}

	// 采购单修改页面方法
	@RequestMapping("/editcgd")
	public String editcgd(Model model, String id) throws Exception {

		// 采购状态
		List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		List<Dictinfo> jyztlist = systemConfigService.findDictinfoByType("003");
		model.addAttribute("cgztlist", cgztlist);
		model.addAttribute("jyztlist", jyztlist);

		//调用service获取采购单信息

		YycgdCustom yycgdCustom = cgdService.findYycgdById(id);
		model.addAttribute("yycgd", yycgdCustom);

		return "/business/cgd/editcgd";

	}
	//采购单的修改
	@RequestMapping("/editcgdsubmit")
	public @ResponseBody
	SubmitResultInfo editcgdsubmit(String id, YycgdQueryVo yycgdQueryVo)
			throws Exception {
		// 执行修改操作
		cgdService.updateYycgd(id, yycgdQueryVo.getYycgdCustom());

		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 906, null));
	}
	/**
	 * 采购单删除
	 */
	@RequestMapping("/yycgddelete")
	public @ResponseBody SubmitResultInfo yycgddelete(ActiveUser activeUser,String cgddeleteid)throws Exception{

		//获取医院id
		String useryyid = activeUser.getSysid();
		cgdService.deleteYycgd(cgddeleteid, useryyid);
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 906, null));

	}
	@RequestMapping("/viewcgd")
	public String viewcgd(Model model, String id)throws Exception{

		// 采购状态
		List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		List<Dictinfo> jyztlist = systemConfigService.findDictinfoByType("003");
		model.addAttribute("cgztlist", cgztlist);
		model.addAttribute("jyztlist", jyztlist);

		//调用service获取采购单信息

		YycgdCustom yycgdCustom = cgdService.findYycgdById(id);
		model.addAttribute("yycgd", yycgdCustom);
		return "/business/cgd/viewcgd";
	}

	/**
	 * 采购单提交
	 */
	@RequestMapping("/yycgdsubmit")
	public @ResponseBody SubmitResultInfo yycgdsubmit(ActiveUser activeUser,String id)throws Exception{

		//获取医院id
		String useryyid = activeUser.getSysid();
		//提交采购单
		cgdService.saveYycgdSubmit(id, activeUser.getSysid());
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 906, null));
	}

	// 采购单药品明细查询结果集
	@RequestMapping("/queryYycgdmx_result")
	public @ResponseBody
	DataGridResultInfo queryYycgdmx_result(String id,// 采购单id
			YycgdQueryVo yycgdQueryVo, int page, int rows) throws Exception {

		// 查询数据总数
		int total = cgdService.findYycgdmxCountByYycgdid(id, yycgdQueryVo);

		// 分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		// 设置分页参数
		yycgdQueryVo.setPageQuery(pageQuery);

		// 分页查询
		List<YycgdmxCustom> list = cgdService.findYycgdmxListByYycgdid(id,
				yycgdQueryVo);
		//List<YycgdmxCustom> footer=cgdService.findYycgdmxListSum(id, yycgdQueryVo);
		
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		if(total>0){
			//调用service进行总计
			List<YycgdmxCustom> sumlist = cgdService.findYycgdmxListSum(id, yycgdQueryVo);
			dataGridResultInfo.setFooter(sumlist);
			
		}

		return dataGridResultInfo;
	}

	//采购药品添加
	@RequestMapping("/queryaddyycgdmx")
	public String queryaddyycgdmx(Model model,String yycgdid)throws Exception{

		// 药品类别
		List<Dictinfo> yplblist = systemConfigService.findDictinfoByType("001");
		model.addAttribute("yplblist", yplblist);

		List<Dictinfo> jyztlist=systemConfigService.findDictinfoByType("003");
		model.addAttribute("jyztlist", jyztlist);
		List<Dictinfo> ypzlcclist=systemConfigService.findDictinfoByType("004");
		model.addAttribute("zlcclist", ypzlcclist);
		//将采购单id
		model.addAttribute("yycgdid", yycgdid);

		return "/business/cgd/queryaddyycgdmx";
	}
	//采购药品的结果
	@RequestMapping("/queryaddyycgdmx_result")
	@ResponseBody
	public DataGridResultInfo queryaddyycgdmx_result(HttpSession session,
			String yycgdid,
			YycgdQueryVo yycgdQueryVo,// 查询条件
			int page, int rows)throws Exception{

		ActiveUser activeUser=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		String useryyid=activeUser.getSysid();
		//列表的总数
		int total = cgdService.findAddYycgdmxCount(useryyid, yycgdid, yycgdQueryVo);
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		yycgdQueryVo.setPageQuery(pageQuery);// 设置分页参数
		List<YycgdmxCustom> list= cgdService.findAddYycgdmxList(useryyid, yycgdid, yycgdQueryVo);
		
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		//dataGridResultInfo.setFooter(footer);
		return dataGridResultInfo;

	}
	@RequestMapping("/addyycgdmxsubmit")
	@ResponseBody
	public SubmitResultInfo addyycgdmxsubmit(String yycgdid,
			int[] indexs,YycgdQueryVo yycgdQueryVo)throws Exception{
		//未选择
		if(indexs == null || indexs.length <=0){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}

		int count=indexs.length;
		//处理成功的数量
		int count_success = 0;
		//处理失败的数量
		int count_error = 0;

		//处理失败的原因
		List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();
		//获取页面传过来的list
		List<YycgdmxCustom> yycgdmxCustoms=yycgdQueryVo.getYycgdmxCustoms();

		for (int i = 0; i < count; i++) {
			//系统提示信息封装类
			ResultInfo resultInfo = null;
			YycgdmxCustom yycgdmxCustom=yycgdmxCustoms.get(indexs[i]);
			try {
				cgdService.insertYycgdmx(yycgdid, yycgdmxCustom.getYpxxid(), yycgdmxCustom.getUsergysid());;
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

	@RequestMapping("/yycgdmxdelsubmit")
	@ResponseBody
	public SubmitResultInfo yycgdmxdelsubmit(int[] indexs,
			String yycgdid,YycgdQueryVo yycgdQueryVo)throws Exception{
		if(indexs == null || indexs.length <=0){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}

		int count=indexs.length;
		//处理成功的数量
		int count_success = 0;
		//处理失败的数量
		int count_error = 0;

		//处理失败的原因
		List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();
		//获取页面传过来的list
		List<YycgdmxCustom> yycgdmxCustoms=yycgdQueryVo.getYycgdmxCustoms();

		for (int i = 0; i < count; i++) {
			//系统提示信息封装类
			ResultInfo resultInfo = null;
			YycgdmxCustom yycgdmxCustom=yycgdmxCustoms.get(indexs[i]);
			try {
				cgdService.deleteYycgdmx(yycgdid,yycgdmxCustom);
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

	// 采购单药品保存
	@RequestMapping("/savecgl")
	public @ResponseBody
	SubmitResultInfo savecgl(String id,// 采购单id
			YycgdQueryVo yycgdQueryVo, int[] indexs // 页面选择序号
			) throws Exception {

		// 页面提交的业务数据（多个），要处理的业务数据，页面中传入的参数
		List<YycgdmxCustom> list = yycgdQueryVo.getYycgdmxCustoms();

		// 处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_error = 0;

		// 处理失败的原因
		List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();

		for (int i = 0; i < count; i++) {

			ResultInfo resultInfo = null;

			// 根据选中行的序号获取要处理的业务数据(单个)
			YycgdmxCustom yycgdmxCustom = list.get(indexs[i]);
			String ypxxid = yycgdmxCustom.getYpxxid();// 药品信息id
			Integer cgl = yycgdmxCustom.getCgl();// 采购量

			try {
				cgdService.updateYycgdmx(id, ypxxid, cgl);
			} catch (Exception e) {
				e.printStackTrace();

				// 进行异常解析
				if (e instanceof ExceptionResultInfo) {
					resultInfo = ((ExceptionResultInfo) e).getResultInfo();
				} else {
					// 构造未知错误异常
					resultInfo = ResultUtil.createFail(Config.MESSAGE, 900,
							null);
				}

			}
			if (resultInfo == null) {
				// 说明成功
				count_success++;
			} else {
				count_error++;
				// 记录失败原因
				msgs_error.add(resultInfo);
			}

		}

		// 提示用户成功数量、失败数量、失败原因
		// 改成返回详细信息
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess(Config.MESSAGE, 907, new Object[] {
						count_success, count_error }), msgs_error);
	}


	//采购药品添加
	@RequestMapping("/yycgdlist")
	public String yycgdlist(Model model)throws Exception{

		// 药品类别
		List<Dictinfo> cgdztlist = systemConfigService.findDictinfoByType("010");
		model.addAttribute("cgdztlist", cgdztlist);
		//这里需要一个默认的年份
		model.addAttribute("year", MyUtil.get_YYYY(MyUtil.getDate()));
		//将采购单id
		//model.addAttribute("yycgdid", yycgdid);

		return "/business/cgd/queryYycgd";
	}
	//采购药品的结果
	@RequestMapping("/yycgdlist_result")
	@ResponseBody
	public DataGridResultInfo yycgdlist_result(ActiveUser activeUser,
			String year,
			YycgdQueryVo yycgdQueryVo,// 查询条件
			int page, int rows)throws Exception{

		//这里用springmvc的参数解析器来把ActiveUser封装成参数
		/*ActiveUser activeUser=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);*/
		String useryyid=activeUser.getSysid();
		//列表的总数
		int total = cgdService.findYycgdCount(useryyid, year, yycgdQueryVo);
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		yycgdQueryVo.setPageQuery(pageQuery);// 设置分页参数
		List<YycgdCustom> list= cgdService.findYycgdList(useryyid, year, yycgdQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}
	//采购药品审核
	@RequestMapping("/yycgdreview")
	public String yycgdreview(Model model)throws Exception{

		//采购单状态
		List<Dictinfo> cgdztlist = systemConfigService.findDictinfoByType("010");
		model.addAttribute("cgdztlist", cgdztlist);
		//这里需要一个默认的年份
		model.addAttribute("year", MyUtil.get_YYYY(MyUtil.getDate()));
		//将采购单id
		//model.addAttribute("yycgdid", yycgdid);

		return "/business/cgd/checkYycgd";
	}
	//采购药品的结果
	@RequestMapping("/yycgdreview_result")
	@ResponseBody
	public DataGridResultInfo yycgdreview_result(ActiveUser activeUser,
			String year,
			YycgdQueryVo yycgdQueryVo,// 查询条件
			int page, int rows)throws Exception{

		//这里用springmvc的参数解析器来把ActiveUser封装成参数
		/*ActiveUser activeUser=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);*/
		String userjdid=activeUser.getSysid();
		//列表的总数
		int total = cgdService.findCheckYycgdCount(userjdid, year, yycgdQueryVo);
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		yycgdQueryVo.setPageQuery(pageQuery);// 设置分页参数
		List<YycgdCustom> list= cgdService.findCheckYycgdList(userjdid, year, yycgdQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}


	//采购药品审核提交
	@RequestMapping("/yycgdreviewsubmit")
	public @ResponseBody
	SubmitResultInfo yycgdreviewsubmit(YycgdQueryVo yycgdQueryVo, int[] indexs // 页面选择序号
			) throws Exception {

		// 页面提交的业务数据（多个），要处理的业务数据，页面中传入的参数
		List<YycgdCustom> list = yycgdQueryVo.getYycgdCustoms();

		// 处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_error = 0;

		// 处理失败的原因
		List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();

		for (int i = 0; i < count; i++) {

			ResultInfo resultInfo = null;

			// 根据选中行的序号获取要处理的业务数据(单个)
			YycgdCustom yycgdCustom = list.get(indexs[i]);

			String yycgdid = yycgdCustom.getId();
			try {
				cgdService.saveYycgdCheckStatus(yycgdid, yycgdCustom);
			} catch (Exception e) {
				e.printStackTrace();

				// 进行异常解析
				if (e instanceof ExceptionResultInfo) {
					resultInfo = ((ExceptionResultInfo) e).getResultInfo();
				} else {
					// 构造未知错误异常
					resultInfo = ResultUtil.createFail(Config.MESSAGE, 900,
							null);
				}

			}
			if (resultInfo == null) {
				// 说明成功
				count_success++;
			} else {
				count_error++;
				// 记录失败原因
				msgs_error.add(resultInfo);
			}

		}

		// 提示用户成功数量、失败数量、失败原因
		// 改成返回详细信息
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess(Config.MESSAGE, 907, new Object[] {
						count_success, count_error }), msgs_error);
	}

	//采购药品受理
	@RequestMapping("/yycgddispose")
	public String yycgddispose(Model model)throws Exception{

		List<Dictinfo> cgdztlist = systemConfigService.findDictinfoByType("010");
		model.addAttribute("cgdztlist", cgdztlist);
		//这里需要一个默认的年份
		model.addAttribute("year", MyUtil.get_YYYY(MyUtil.getDate()));

		return "/business/cgd/disposeyycgd";
	}
	//采购药品的结果
	@RequestMapping("/yycgddispose_result")
	@ResponseBody
	public DataGridResultInfo yycgddispose_result(ActiveUser activeUser,
			String year,
			YycgdQueryVo yycgdQueryVo,// 查询条件
			int page, int rows)throws Exception{

		//这里用springmvc的参数解析器来把ActiveUser封装成参数
		/*ActiveUser activeUser=(ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);*/
		String usergysid=activeUser.getSysid();
		//列表的总数
		int total = cgdService.findDisposeYycgdCount(usergysid, year, yycgdQueryVo);
		//分页参数
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		yycgdQueryVo.setPageQuery(pageQuery);// 设置分页参数
		List<YycgdmxCustom> list= cgdService.findDisposeYycgdList(usergysid, year, yycgdQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;

	}
	
	// 采购单受理提交
	@RequestMapping("/yycgddispose_submit")
	public @ResponseBody
	SubmitResultInfo disposeyycgdsubmit(YycgdQueryVo yycgdQueryVo, int[] indexs // 页面选择序号
	) throws Exception {

		// 页面提交的业务数据（多个），要处理的业务数据，页面中传入的参数
		List<YycgdmxCustom> list = yycgdQueryVo.getYycgdmxCustoms();

		// 处理数据的总数
		int count = indexs.length;
		// 处理成功的数量
		int count_success = 0;
		// 处理失败的数量
		int count_error = 0;

		// 处理失败的原因
		List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();

		for (int i = 0; i < count; i++) {

			ResultInfo resultInfo = null;

			// 根据选中行的序号获取要处理的业务数据(单个)
			YycgdmxCustom yycgdmxCustom = list.get(indexs[i]);
			// 采购单id
			String yycgdid = yycgdmxCustom.getYycgdid();
			// 药品id
			String ypxxid = yycgdmxCustom.getYpxxid();

			try {
				cgdService.saveSendStatus(yycgdid, ypxxid);
			} catch (Exception e) {
				e.printStackTrace();

				// 进行异常解析
				if (e instanceof ExceptionResultInfo) {
					resultInfo = ((ExceptionResultInfo) e).getResultInfo();
				} else {
					// 构造未知错误异常
					resultInfo = ResultUtil.createFail(Config.MESSAGE, 900,
							null);
				}

			}
			if (resultInfo == null) {
				// 说明成功
				count_success++;
			} else {
				count_error++;
				// 记录失败原因
				msgs_error.add(resultInfo);
			}

		}

		// 提示用户成功数量、失败数量、失败原因
		// 改成返回详细信息
		return ResultUtil.createSubmitResult(
				ResultUtil.createSuccess(Config.MESSAGE, 907, new Object[] {
						count_success, count_error }), msgs_error);
	}
	
	
	//采购药品入库
		@RequestMapping("/yycgdrkquery")
		public String yycgdrkquery(Model model)throws Exception{

			//这里需要一个默认的年份
			model.addAttribute("year", MyUtil.get_YYYY(MyUtil.getDate()));

			return "/business/cgd/receiveyycgd";
		}
		//采购药品的结果
		@RequestMapping("/yycgdrkquery_result")
		@ResponseBody
		public DataGridResultInfo yycgdrkquery_result(ActiveUser activeUser,
				String year,
				YycgdQueryVo yycgdQueryVo,// 查询条件
				int page, int rows)throws Exception{

			//这里用springmvc的参数解析器来把ActiveUser封装成参数
			String useryyid=activeUser.getSysid();
			//列表的总数
			int total = cgdService.findYycgdReceivCount(useryyid, year, yycgdQueryVo);
			//分页参数
			PageQuery pageQuery = new PageQuery();
			pageQuery.setPageParams(total, rows, page);
			yycgdQueryVo.setPageQuery(pageQuery);// 设置分页参数
			List<YycgdmxCustom> list= cgdService.findYycgdReceiveList(useryyid, year, yycgdQueryVo);
			DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
			dataGridResultInfo.setTotal(total);
			dataGridResultInfo.setRows(list);
			return dataGridResultInfo;

		}
		
		@RequestMapping("/yycgdrk_submit")
		public @ResponseBody
		SubmitResultInfo yycgdrksubmit(YycgdQueryVo yycgdQueryVo, int[] indexs // 页面选择序号
		) throws Exception {

			// 页面提交的业务数据（多个），要处理的业务数据，页面中传入的参数
			List<YycgdrkCustom> list = yycgdQueryVo.getYycgdrkCustoms();

			// 处理数据的总数
			int count = indexs.length;
			// 处理成功的数量
			int count_success = 0;
			// 处理失败的数量
			int count_error = 0;

			// 处理失败的原因
			List<ResultInfo> msgs_error = new ArrayList<ResultInfo>();

			for (int i = 0; i < count; i++) {

				ResultInfo resultInfo = null;

				// 根据选中行的序号获取要处理的业务数据(单个)
				YycgdrkCustom yycgdrkCustom = list.get(indexs[i]);
				// 采购单id
				String yycgdid = yycgdrkCustom.getYycgdid();
				// 药品id
				String ypxxid = yycgdrkCustom.getYpxxid();

				try {
					cgdService.saveYycgdrk(yycgdid, ypxxid, yycgdrkCustom);
				} catch (Exception e) {
					e.printStackTrace();

					// 进行异常解析
					if (e instanceof ExceptionResultInfo) {
						resultInfo = ((ExceptionResultInfo) e).getResultInfo();
					} else {
						// 构造未知错误异常
						resultInfo = ResultUtil.createFail(Config.MESSAGE, 900,
								null);
					}

				}
				if (resultInfo == null) {
					// 说明成功
					count_success++;
				} else {
					count_error++;
					// 记录失败原因
					msgs_error.add(resultInfo);
				}

			}

			// 提示用户成功数量、失败数量、失败原因
			// 改成返回详细信息
			return ResultUtil.createSubmitResult(
					ResultUtil.createSuccess(Config.MESSAGE, 907, new Object[] {
							count_success, count_error }), msgs_error);
		}
}
