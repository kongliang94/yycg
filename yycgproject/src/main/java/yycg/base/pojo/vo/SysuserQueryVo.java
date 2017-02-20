package yycg.base.pojo.vo;


/**
 * 封装的用户信息（携带部分条件）
 * @author KL
 *
 */
public class SysuserQueryVo {
	
	//分页参数
	private PageQuery pageQuery;
	//用户的查询条件
	private SysuserCustom sysuserCustom;
	//可以扩展多个属性
	//....

	public SysuserCustom getSysuserCustom() {
		return sysuserCustom;
	}

	public void setSysuserCustom(SysuserCustom sysuserCustom) {
		this.sysuserCustom = sysuserCustom;
	}

	public PageQuery getPageQuery() {
		return pageQuery;
	}

	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	
	

}
