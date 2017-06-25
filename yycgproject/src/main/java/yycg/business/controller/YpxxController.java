package yycg.business.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.vo.PageQuery;
import yycg.base.pojo.vo.SysuserCustom;
import yycg.base.pojo.vo.SysuserQueryVo;
import yycg.base.process.context.Config;
import yycg.base.process.result.DataGridResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.process.result.View;
import yycg.base.service.SystemConfigService;
import yycg.business.pojo.po.Ypxx;
import yycg.business.pojo.vo.YpxxCustom;
import yycg.business.pojo.vo.YpxxQueryVo;
import yycg.business.service.YpxxService;
import yycg.util.ExcelExportSXXSSF;
import yycg.util.HxlsOptRowsInterface;
import yycg.util.HxlsRead;
import yycg.util.MyUtil;
import yycg.util.UUIDBuild;

@Controller
@RequestMapping("/ypml")
public class YpxxController {

	@Autowired
	private YpxxService ypxxService;
	
	@Autowired
	private SystemConfigService systemConfigService;
	
	@Autowired
	private HxlsOptRowsInterface ypxxImportService;
	
	
	
	/**
	 * 药品信息查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ypxxquery")
	public String ypxxquery(Model model) throws Exception{
		
		return View.toBusiness("/ypml/ypxxquery");
	}
	@RequestMapping("/ypxxmanager")
	public String ypxxmanager(Model model) throws Exception{
		model.addAttribute("ismanager", "1");//此标记作为页面上管理按钮是否显示的依据 1：表示需要管理功能
		return ypxxquery(model);

	}
	/**
	 * 药品信息查询结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ypxxquery_result")
	public @ResponseBody DataGridResultInfo ypxxquery_result(YpxxQueryVo ypxxQueryVo,int page,int rows) throws Exception{
		
		//非空校验
		ypxxQueryVo = ypxxQueryVo!=null?ypxxQueryVo:new YpxxQueryVo();

				//查询列表的总数
				int total = ypxxService.findYpxxCount(ypxxQueryVo);

				PageQuery pageQuery = new PageQuery();
				pageQuery.setPageParams(total, rows, page);

				ypxxQueryVo.setPageQuery(pageQuery);

				//分页查询，向ypxxQueryVo中传入pageQuery
				List<YpxxCustom> list = ypxxService.findYpxxList(ypxxQueryVo);

				DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
				//填充 total
				dataGridResultInfo.setTotal(total);
				//填充  rows
				dataGridResultInfo.setRows(list);
				System.out.println(dataGridResultInfo);
				return dataGridResultInfo;
	}
	@RequestMapping("/exportypxx")
	public String exportypxx(Model model) throws Exception{

		//药品类别
		List<Dictinfo> yplblist = systemConfigService.findDictinfoByType("001");

		//交易状态
		List<Dictinfo> jyztlist = systemConfigService.findDictinfoByType("003");

		model.addAttribute("yplblist", yplblist);
		model.addAttribute("jyztlist", jyztlist);
		return "/business/ypml/exportYpxx";
	}
	@RequestMapping("/queryexportYpxx_result")
	@ResponseBody
	public DataGridResultInfo queryexportYpxx_result(YpxxQueryVo ypxxQueryVo,
			int page, int rows)throws Exception{
		//非空校验
		ypxxQueryVo = ypxxQueryVo!=null?ypxxQueryVo:new YpxxQueryVo();

				//查询列表的总数
				int total = ypxxService.findYpxxCount(ypxxQueryVo);

				PageQuery pageQuery = new PageQuery();
				pageQuery.setPageParams(total, rows, page);

				ypxxQueryVo.setPageQuery(pageQuery);

				//分页查询，向ypxxQueryVo中传入pageQuery
				List<YpxxCustom> list = ypxxService.findYpxxList(ypxxQueryVo);

				DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
				//填充 total
				dataGridResultInfo.setTotal(total);
				//填充  rows
				dataGridResultInfo.setRows(list);
				System.out.println(dataGridResultInfo);
				return dataGridResultInfo;
	}
	
	@RequestMapping("/exportYpxxSubmit")
	@ResponseBody
	public SubmitResultInfo exportypxxsubmit(YpxxQueryVo ypxxQueryVo) throws Exception{

		// 调用封装类执行导出

		// 导出文件存放的路径，并且是虚拟目录指向的路径
		//String filePath = "d:/upload/linshi/";
		//改为从系统参数配置表获取参数值 
		String filePath = systemConfigService.findBasicinfoById("00301").getValue();
		// 导出文件的前缀
		String filePrefix = "ypxx";
		// -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
		int flushRows = 100;

		// 定义导出数据的title
		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add("流水号");
		fieldNames.add("通用名");
		fieldNames.add("剂型");
		fieldNames.add("规格");
		fieldNames.add("转换系数");
		fieldNames.add("生产企业");
		fieldNames.add("商品名称");
		fieldNames.add("中标价格");
		fieldNames.add("交易状态");

		// 告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
		List<String> fieldCodes = new ArrayList<String>();
		fieldCodes.add("bm");// 药品流水号
		fieldCodes.add("mc");// 通用名
		fieldCodes.add("jx");
		fieldCodes.add("gg");
		fieldCodes.add("zhxs");
		fieldCodes.add("scqymc");
		fieldCodes.add("spmc");
		fieldCodes.add("zbjg");
		fieldCodes.add("jyztmc");

		// 上边的代码可以优化为，将title和title对应的 pojo的属性，使用map存储
		// ....

		// 注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应

		// 开始导出，执行一些workbook及sheet等对象的初始创建
		ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath,
				"/upload/", filePrefix, fieldNames, fieldCodes, flushRows);

		// 导出的数据通过service取出
		List<YpxxCustom> list = ypxxService.findYpxxList(ypxxQueryVo);

		// 执行导出
		excelExportSXXSSF.writeDatasByObject(list);
		// 输出文件，返回下载文件的http地址，已经包括虚拟目录
		String webpath = excelExportSXXSSF.exportFile();

		System.out.println(webpath);

		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(
				Config.MESSAGE, 313, new Object[] {
						list.size(),
						webpath//下载地址
				}));
	}

	@RequestMapping("/importypxx")
	public String importypxx(Model model)throws Exception{

		return "/business/ypml/importypxx";
	}

	@RequestMapping("/importypxxsubmit")
	@ResponseBody
	public SubmitResultInfo importypxxsubmit(//写上传的文件
			MultipartFile ypxximportfile,HttpServletRequest request
			)throws Exception{
		//将上传的文件写到磁盘
		String originalFilename=ypxximportfile.getOriginalFilename();
				//String originalFilename  = ypxximportfile.getOriginalFilename();
		//写入磁盘的文件
		File file=new File("D:/upload/linshi/"+UUIDBuild.getUUID()+originalFilename.substring(originalFilename.lastIndexOf(".")));
		//File file = new File("D:/upload/linshi/"+UUIDBuild.getUUID()+originalFilename.substring(originalFilename.lastIndexOf(".")));
//				if(!file.exists()){
//					//如果文件目录 不存在则创建
//					file.mkdirs();
//				}
		if (!file.exists()) {
			//如果文件目录不存在则创建
			file.mkdirs();
		}
				
		//将内存中的文件写磁盘
		ypxximportfile.transferTo(file);
		//ypxximportfile.transferTo(file);
		//上传文件磁盘上路径 
		String absolutePath = file.getAbsolutePath();
						
		//调用工具类进行药品目录 导入
		HxlsRead xls2csv = null;
		try {
			//第一个参数就是导入的文件
			//第二个参数就是导入文件中哪个sheet
			//第三个参数导入接口的实现类对象
			xls2csv = new HxlsRead(absolutePath,1,ypxxImportService);
			xls2csv.process();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//提示导入成功条数
		long success_num = xls2csv.getOptRows_success();
		//导入失败数量
		long failure_num = xls2csv.getOptRows_failure();
		
		//对导入失败记录处理
		//获取导入失败记录
		//xls2csv.getFailrows()
		//获取导入记录的title
		//xls2csv.getRowtitle();
		//获取导入失败原因
		//xls2csv.getFailmsgs()	
		//将上边获取到的失败记录、title、失败原因，导出成一个 excel
		//使用工具类进行导出，得到导出文件下载路径
	
		if (failure_num>0) {
			String filePath = systemConfigService.findBasicinfoById("00301").getValue();
			// 导出文件的前缀
			String filePrefix = "ypxx";
			List<String> fieldNames=xls2csv.getRowtitle();
			fieldNames.add("错误信息");
			ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath,
					"/upload/", filePrefix, fieldNames, null, 300);
			List<List<String>> failrows = xls2csv.getFailrows();
			List<String> failmsgs=xls2csv.getFailmsgs();
			for (int k = 0; k < failrows.size(); k++) {
				List<String> failrow = failrows.get(k);
				if (failrow!=null) {
					String failmsg=failmsgs.get(k);
					failrow.add(failmsg == null?"":failmsg);
					excelExportSXXSSF.writeDatasByString(failrow);;
				}
				
			}
			String file_webpath = excelExportSXXSSF.exportFile();
//			return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE,315, new Object[]{xls2csv.getOptRows_success(),xls2csv.getOptRows_failure(),request.getContextPath()+file_webpath}));
			
			return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE,315, new Object[]{xls2csv.getOptRows_success(),xls2csv.getOptRows_failure(),file_webpath}));	
		}
		else {
			return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 314, new Object[]{
					success_num,failure_num}));
		}
		
	}
	
	/**
	 * 修改药品信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ypxxedit")
	public String ypxxedit(String editid,Model model) throws Exception{
		if(editid != null){
			Ypxx ypxx = ypxxService.getYpxxObject(editid);
			if(ypxx!=null){
				model.addAttribute("ypxx", ypxx);
			}else{
				ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE,902, null));
			}
		}

		/*//药品类别
		List yplbList = systemConfigService.findDicttypeinfolist("001");
		//药品状态类别
		List ypztList = systemConfigService.findDicttypeinfolist("002");
		//质量层次
		List ypzlccList = systemConfigService.findDicttypeinfolist("004");
		//交易状态
		List ypjyztList = systemConfigService.findDicttypeinfolist("003");

		model.addAttribute("yplbList", yplbList);
		model.addAttribute("ypztList", ypztList);
		model.addAttribute("ypzlccList", ypzlccList);
		model.addAttribute("ypjyztList", ypjyztList);*/
		return View.toBusiness("/ypml/ypxxedit");
	}

	@RequestMapping("/ypxxsave")
	@ResponseBody
	public SubmitResultInfo ypxxsave(YpxxQueryVo ypxxQueryVo) throws Exception{
		
		
		Ypxx ypxx=ypxxQueryVo.getYpxx();
		YpxxCustom ypxxCustom=ypxxQueryVo.getYpxxCustom();
		if(ypxxQueryVo == null || ypxx == null || ypxx.getMc() == null){
			return ResultUtil.createSubmitResult(ResultUtil.createWarning(Config.MESSAGE,901, null));
		}

		if(MyUtil.isNotNullAndEmpty(ypxx.getId())){
			ypxxService.updateYpxx(ypxx);
		}else{
			ypxxService.insertYpxx(ypxx);
		}
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE,906,null));
	}
	
	@RequestMapping("/ypxxview")
	public String ypxxview(String id,Model model) throws Exception{
		if(id != null){
			Ypxx ypxx = ypxxService.getYpxxObject(id);
			if(ypxx!=null){
				model.addAttribute("ypxx", ypxx);
			}else{
				ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE,902, null));
			}
		}
		
		return View.toBusiness("/ypml/ypxxview");
	}
	
	/**
	 * 删除药品信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ypxxdel")
	public @ResponseBody SubmitResultInfo ypxxdel(String ypxxdelid) throws Exception{
		if(ypxxdelid == null || ypxxdelid.equals("")){
			return ResultUtil.createSubmitResult(ResultUtil.createWarning(Config.MESSAGE,901, null));
		}
		
		String[] idAtt=ypxxdelid.split(",");
		int del_success=0;
		int del_fail=0;
		for(int i=0;i<idAtt.length;i++){
			try{
				ypxxService.deleteYpxx(idAtt[i]);
				del_success++;
			}catch(Exception e){
				del_fail++;
				e.printStackTrace();
			}
		}
		
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE,907, new Object[]{del_success,del_fail}));
		
	}
	
}
