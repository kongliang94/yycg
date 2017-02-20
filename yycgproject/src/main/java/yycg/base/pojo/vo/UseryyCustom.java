package yycg.base.pojo.vo;

import yycg.base.pojo.po.Useryy;

public class UseryyCustom extends Useryy{

	// 医院级别名称
	private String jbmc;

	// 医院类别名称
	private String lbmc;

	// 地区名称
	private String dqmc;

	//所属管理地区
	public String gldq;

	public String getDqmc() {
		return dqmc;
	}

	public void setDqmc(String dqmc) {
		this.dqmc = dqmc;
	}

	public String getJbmc() {
		return jbmc;
	}

	public void setJbmc(String jbmc) {
		this.jbmc = jbmc;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	public String getGldq() {
		return gldq;
	}

	public void setGldq(String gldq) {
		this.gldq = gldq;
	}
}
