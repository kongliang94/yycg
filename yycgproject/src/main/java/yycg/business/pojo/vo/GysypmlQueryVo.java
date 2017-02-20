package yycg.business.pojo.vo;

import java.util.List;

import yycg.base.pojo.vo.PageQuery;
import yycg.business.pojo.po.GysypmlControl;

public class GysypmlQueryVo {
	
	private List<GysypmlControl> gysypmlControls;
	public List<GysypmlControl> getGysypmlControls() {
		return gysypmlControls;
	}

	public void setGysypmlControls(List<GysypmlControl> gysypmlControls) {
		this.gysypmlControls = gysypmlControls;
	}

	//页面批量添加，用list接收。
	private List<YpxxCustom> ypxxCustoms;
	
	public List<YpxxCustom> getYpxxCustoms() {
		return ypxxCustoms;
	}

	public void setYpxxCustoms(List<YpxxCustom> ypxxCustoms) {
		this.ypxxCustoms = ypxxCustoms;
	}

	private PageQuery pageQuery;  //分页数据
	
	private YpxxCustom ypxxCustom;  //药品信息,可以通过药品字段查询供应商药品目录。
	
	private GysypmlCustom gysypmlCustom;  //供应商药品目录

	public PageQuery getPageQuery() {
		return pageQuery;
	}

	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}

	public GysypmlCustom getGysypmlCustom() {
		return gysypmlCustom;
	}

	public void setGysypmlCustom(GysypmlCustom gysypmlCustom) {
		this.gysypmlCustom = gysypmlCustom;
	}

	public YpxxCustom getYpxxCustom() {
		return ypxxCustom;
	}

	public void setYpxxCustom(YpxxCustom ypxxCustom) {
		this.ypxxCustom = ypxxCustom;
	}
	
	
}
