package yycg.business.pojo.vo;

import java.util.ArrayList;
import java.util.List;

import yycg.base.pojo.po.Usergys;
import yycg.base.pojo.po.Useryy;
import yycg.base.pojo.vo.PageQuery;
import yycg.business.pojo.po.Yyypml;

/**
 * 医院药品目录查询模型
 * @author KL
 *
 */
public class YyypmlQueryVo extends GysypmlQueryVo {
	
	
	
	private Integer iscount=1;  //分页数据
	//医院药品目录
	private List<Yyypml> yyypmls = new ArrayList<Yyypml>();
	
	//医院
	private Useryy useryy;
	//供应商
	private Usergys usergys;
	
	public Usergys getUsergys() {
		return usergys;
	}

	public void setUsergys(Usergys usergys) {
		this.usergys = usergys;
	}

	//医院药品目录
	private Yyypml yyypml;

	//供货状态
	private String control;
	
	//添加药品目标采购单，用于添加采购药品使用
	private String yycgdid;
	
    //采购单年份
	private String businessyear;

	public List<Yyypml> getYyypmls() {
		return yyypmls;
	}

	public void setYyypmls(List<Yyypml> yyypmls) {
		this.yyypmls = yyypmls;
	}

	public Useryy getUseryy() {
		return useryy;
	}

	public void setUseryy(Useryy useryy) {
		this.useryy = useryy;
	}

	public Yyypml getYyypml() {
		return yyypml;
	}

	public void setYyypml(Yyypml yyypml) {
		this.yyypml = yyypml;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getYycgdid() {
		return yycgdid;
	}

	public void setYycgdid(String yycgdid) {
		this.yycgdid = yycgdid;
	}

	public String getBusinessyear() {
		return businessyear;
	}

	public void setBusinessyear(String businessyear) {
		this.businessyear = businessyear;
	}

	
	
}
