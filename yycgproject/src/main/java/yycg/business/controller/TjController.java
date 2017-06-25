package yycg.business.controller;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.dao.mapper.SysuserMapperCustom;
import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.po.Sysuser;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.PageQuery;
import yycg.base.process.result.DataGridResultInfo;
import yycg.base.service.SystemConfigService;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;
import yycg.business.service.YybusinessService;
import yycg.util.DateUtil;
import yycg.util.MyUtil;

@Controller
@RequestMapping("/statistics")
public class TjController {

	@Autowired
	private YybusinessService yybusinessService;
	@Autowired
	private SystemConfigService systemConfigService;

	@RequestMapping("/businesslist")
	public String businesslist(Model model) throws Exception{

		String currentDate=MyUtil.getDate();
		String year=MyUtil.get_YYYY(currentDate);
		model.addAttribute("year", year);
		return "/business/tj/businesslist";
	}

	@RequestMapping("/businesslist_result")
	@ResponseBody
	public DataGridResultInfo businesslist_result(ActiveUser activeUser,
			String year,
			YycgdQueryVo yycgdQueryVo,
			int page, int rows) throws Exception{

		String sysid=activeUser.getSysid();
		String groupid=activeUser.getGroupid();
		int count=yybusinessService.findYybusinessCount(year, groupid, sysid, yycgdQueryVo);
		//分页参数
		PageQuery pageQuery=new PageQuery();
		pageQuery.setPageParams(count, rows, page);
		//把分页参数封装到查询条件
		yycgdQueryVo.setPageQuery(pageQuery);
		// 分页查询
		List<YycgdmxCustom> list = yybusinessService.findYybusinessList(year, 
				groupid, sysid, yycgdQueryVo);
		//List<YycgdmxCustom> footer=cgdService.findYycgdmxListSum(id, yycgdQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(count);
		dataGridResultInfo.setRows(list);
		if(count>0){
			//调用service进行总计
			List<YycgdmxCustom> sumlist = yybusinessService.findYybusinessSum(year, 
					groupid, sysid, yycgdQueryVo);
			dataGridResultInfo.setFooter(sumlist);

		}

		return dataGridResultInfo;
	}

	// 按药品统计
	@RequestMapping("/groupbyypxx")
	public String groupbyypxx(Model model) throws Exception {

		// 采购状态字典
//		List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
//		model.addAttribute("cgztlist", cgztlist);

		// 默认当前年份
		model.addAttribute("year", MyUtil.get_YYYY(MyUtil.getDate()));

		return "/business/tj/groupbyypxx";
	}

	@RequestMapping("/groupbyypxx_result")
	public @ResponseBody
	DataGridResultInfo groupbyypxx_result(ActiveUser activeUser, String year,// 查询年份
			YycgdQueryVo yycgdQueryVo, int page, int rows

			) throws Exception {
		// 单位id
		String sysid = activeUser.getSysid();
		// 用户类型
		String groupid = activeUser.getGroupid();

		// 列表总数
		int total = yybusinessService.findYybusinessGroupbyYpxxCount(year, sysid, groupid, yycgdQueryVo);

		// 分页参数
		PageQuery pageQuery = new PageQuery();

		pageQuery.setPageParams(total, rows, page);
		// 设置分页参数
		yycgdQueryVo.setPageQuery(pageQuery);

		List<YycgdmxCustom> list = yybusinessService.findYybusinessGroupbyYpxxList(year, sysid, groupid, yycgdQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}
	// 按医院统计
	@RequestMapping("/groupbyuseryy")
	public String groupbyuseryy(Model model) throws Exception {

		// 采购状态字典
		//List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		//model.addAttribute("cgztlist", cgztlist);

		// 默认当前年份
		model.addAttribute("year", MyUtil.get_YYYY(MyUtil.getDate()));

		return "/business/tj/groupbyuseryy";
	}

	@RequestMapping("/groupbyuseryy_result")
	public @ResponseBody
	DataGridResultInfo groupbyuseryy_result(ActiveUser activeUser, String year,// 查询年份
			YycgdQueryVo yycgdQueryVo, int page, int rows

			) throws Exception {
		// 单位id
		String sysid = activeUser.getSysid();
		// 用户类型
		String groupid = activeUser.getGroupid();

		// 列表总数
		int total = yybusinessService.findYybusinessGroupbyUseryyCount(year, sysid, groupid, yycgdQueryVo);

		// 分页参数
		PageQuery pageQuery = new PageQuery();

		pageQuery.setPageParams(total, rows, page);
		// 设置分页参数
		yycgdQueryVo.setPageQuery(pageQuery);

		List<YycgdmxCustom> list = yybusinessService.findYybusinessGroupbyUseryyList(year, sysid, groupid, yycgdQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}
	// 按供货商统计
	@RequestMapping("/groupbyusergys")
	public String groupbyusergys(Model model) throws Exception {

		// 采购状态字典
		//List<Dictinfo> cgztlist = systemConfigService.findDictinfoByType("011");
		//model.addAttribute("cgztlist", cgztlist);

		// 默认当前年份
		model.addAttribute("year", MyUtil.get_YYYY(MyUtil.getDate()));

		return "/business/tj/groupbyusergys";
	}

	@RequestMapping("/groupbyusergys_result")
	public @ResponseBody
	DataGridResultInfo groupbyusergys_result(ActiveUser activeUser, String year,// 查询年份
			YycgdQueryVo yycgdQueryVo, int page, int rows

			) throws Exception {
		// 单位id
		String sysid = activeUser.getSysid();
		// 用户类型
		String groupid = activeUser.getGroupid();

		// 列表总数
		int total = yybusinessService.findYybusinessGroupbyUsergysCount(year, sysid, groupid, yycgdQueryVo);

		// 分页参数
		PageQuery pageQuery = new PageQuery();

		pageQuery.setPageParams(total, rows, page);
		// 设置分页参数
		yycgdQueryVo.setPageQuery(pageQuery);

		List<YycgdmxCustom> list = yybusinessService.findYybusinessGroupbyUsergysList(year, sysid, groupid, yycgdQueryVo);

		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setTotal(total);
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}
	
	// 按区域统计
	@RequestMapping("/groupbyarea")
	public String groupbyarea(Model model,HttpSession session, ActiveUser activeUser, String year,// 查询年份
			YycgdQueryVo yycgdQueryVo

			) throws Exception {

		//如果年份为空默认为当前年份
		if(year == null){
			year= MyUtil.get_YYYY(MyUtil.getDate());
		}

		// 单位id
		String sysid = activeUser.getSysid();
		// 用户类型
		String groupid = activeUser.getGroupid();
		// 调用service查询数据
		List<YycgdmxCustom> list = yybusinessService
				.findYybusinessGroupbyAreaList(year, sysid, groupid,
						yycgdQueryVo);

		//jfreechart定义

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 第一个参数：统计数值，第二个参数：统计指标名称，第三个参数：统计分类
		/*dataset.addValue(1310, "药品采购金额", "崔庙镇");
			dataset.addValue(720.68, "药品采购金额", "汜水镇");
			dataset.addValue(675.3, "药品采购金额", "高山镇");
			dataset.addValue(560, "药品采购金额", "城关乡");
			dataset.addValue(680.88, "药品采购金额", "刘河镇");
			dataset.addValue(780, "药品采购金额", "环翠峪");*/

		// 实际开发时，数据从数据库查询出来，
		// 在for循环中向dataset中添加数据
		for(YycgdmxCustom yycgdmxCustom:list){
			dataset.addValue(yycgdmxCustom.getCgje(), "药品采购金额", yycgdmxCustom.getAreaname());
		}

		JFreeChart chart = ChartFactory.createBarChart3D("药品采购金额汇总",// 图形名称
				"",// 分类名称，为横坐标名称
				"采购金额(元)",// 值名称，为纵坐标名称
				dataset,// 数据集合
				PlotOrientation.VERTICAL,// 垂直显示
				false,// 是否显示图例
				false,// 是否使用工具提示
				false);// 是否使用url

		// 在柱上显示数值
		CategoryPlot plot = chart.getCategoryPlot();

		BarRenderer3D renderer = new BarRenderer3D();

		// 设置柱的颜色
		// renderer.setSeriesPaint(0, Color.decode("#ff0000"));

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		// 默认的数字显示在柱子中，通过如下两句可调整数字的显示
		// 注意：此句很关键，若无此句，数字的显示会被覆盖
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		// 设置每个地区所包含的平行柱的之间距离
		// renderer.setItemMargin(0.3);
		plot.setRenderer(renderer);
		// 设置字体
		// 配置字体
		Font xfont = new Font("宋体", Font.PLAIN, 12);// X轴
		Font yfont = new Font("宋体", Font.PLAIN, 12);// Y轴
		Font kfont = new Font("宋体", Font.PLAIN, 12);// 底部
		Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题
		// 图形的绘制结构对象,对于饼图不适用

		// 图片标题
		chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));

		// 底部
		LegendTitle legendTitle = chart.getLegend();
		if (legendTitle != null) {
			legendTitle.setItemFont(kfont);
		}

		// X 轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(xfont);// 轴标题
		domainAxis.setTickLabelFont(xfont);// 轴数值
		domainAxis.setTickLabelPaint(Color.BLUE); // 字体颜色
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		// Y 轴
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(yfont);
		rangeAxis.setLabelPaint(Color.BLUE); // 字体颜色
		rangeAxis.setTickLabelFont(yfont);

		//将图形放在session，得到filename
		String jfreechart_filename = ServletUtilities.saveChartAsPNG(chart,
				900, 500, null, session);

		model.addAttribute("jfreechart_filename", jfreechart_filename);

		return "/business/tj/groupbyarea";
	}

}
