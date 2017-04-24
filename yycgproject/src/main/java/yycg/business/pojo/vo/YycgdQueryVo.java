package yycg.business.pojo.vo;

import java.util.List;

import yycg.base.pojo.po.Usergys;
import yycg.base.pojo.po.Userjd;
import yycg.base.pojo.po.Useryy;
import yycg.base.pojo.vo.PageQuery;

public class YycgdQueryVo {

	
	private PageQuery pageQuery;  //分页数据
	public PageQuery getPageQuery() {
		return pageQuery;
	}
	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	public List<YycgdmxCustom> getYycgdmxCustoms() {
		return yycgdmxCustoms;
	}
	public void setYycgdmxCustoms(List<YycgdmxCustom> yycgdmxCustoms) {
		this.yycgdmxCustoms = yycgdmxCustoms;
	}
	//采购单药品批量添加，用list接受
	private List<YycgdmxCustom> yycgdmxCustoms;
	//采购单批量添加，用list接受
	private List<YycgdCustom> yycgdCustoms;
	//采购单批量添加，用list接受
	private List<YycgdrkCustom> yycgdrkCustoms;
	public List<YycgdrkCustom> getYycgdrkCustoms() {
		return yycgdrkCustoms;
	}
	public void setYycgdrkCustoms(List<YycgdrkCustom> yycgdrkCustoms) {
		this.yycgdrkCustoms = yycgdrkCustoms;
	}
	public List<YycgdCustom> getYycgdCustoms() {
		return yycgdCustoms;
	}
	public void setYycgdCustoms(List<YycgdCustom> yycgdCustoms) {
		this.yycgdCustoms = yycgdCustoms;
	}
	private YycgdCustom yycgdCustom;
	public YycgdCustom getYycgdCustom() {
		return yycgdCustom;
	}
	public void setYycgdCustom(YycgdCustom yycgdCustom) {
		this.yycgdCustom = yycgdCustom;
	}
	private YycgdmxCustom yycgdmxCustom;//采购单明细
	private YpxxCustom ypxxCustom;//药品信息

	private GysypmlCustom gysypmlCustom;//供货商药品目录
	public YycgdmxCustom getYycgdmxCustom() {
		return yycgdmxCustom;
	}
	public void setYycgdmxCustom(YycgdmxCustom yycgdmxCustom) {
		this.yycgdmxCustom = yycgdmxCustom;
	}
	public YpxxCustom getYpxxCustom() {
		return ypxxCustom;
	}
	public void setYpxxCustom(YpxxCustom ypxxCustom) {
		this.ypxxCustom = ypxxCustom;
	}
	public GysypmlCustom getGysypmlCustom() {
		return gysypmlCustom;
	}
	public void setGysypmlCustom(GysypmlCustom gysypmlCustom) {
		this.gysypmlCustom = gysypmlCustom;
	}
	private String businessyear;
	public String getBusinessyear() {
		return businessyear;
	}
	public void setBusinessyear(String businessyear) {
		this.businessyear = businessyear;
	}
	private Useryy useryy; //医院信息
	public Useryy getUseryy() {
		return useryy;
	}
	public void setUseryy(Useryy useryy) {
		this.useryy = useryy;
	}
	private Userjd userjd; //监督单位
	public Userjd getUserjd() {
		return userjd;
	}
	public void setUserjd(Userjd userjd) {
		this.userjd = userjd;
	}
	private Usergys usergys;
	public Usergys getUsergys() {
		return usergys;
	}
	public void setUsergys(Usergys usergys) {
		this.usergys = usergys;
	}
	
	
}
