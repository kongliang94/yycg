package yycg.base.pojo.vo;

import yycg.base.pojo.po.Useryy;

public class UseryyQueryVo {

	//是否分页
	private int iscount;

	//分页数据
	private PageQuery pageQuery;

	//医院po
	private Useryy useryy;
	//医院自定义vo
	private UseryyCustom useryyCustom;
	public int getIscount() {
		return iscount;
	}
	public void setIscount(int iscount) {
		this.iscount = iscount;
	}
	public PageQuery getPageQuery() {
		return pageQuery;
	}
	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	public Useryy getUseryy() {
		return useryy;
	}
	public void setUseryy(Useryy useryy) {
		this.useryy = useryy;
	}
	public UseryyCustom getUseryyCustom() {
		return useryyCustom;
	}
	public void setUseryyCustom(UseryyCustom useryyCustom) {
		this.useryyCustom = useryyCustom;
	}
}
