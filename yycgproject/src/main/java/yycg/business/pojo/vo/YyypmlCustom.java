package yycg.business.pojo.vo;


/**
 * 医院采购药品目录信息自定义vo
 * @author miaoruntu
 *
 */
public class YyypmlCustom extends YpxxCustom{
	//药品信息id
	private String ypxxid;
	
	//医院id
	private String useryyid;
	
	//医院名称
	private String useryymc;
	
	//供应商id
	private String usergysid;
	
	//供应商名称
	private String usergysmc;
	
	//供货状态
	private String control;
	
	//供货状态名称
	private String controlmc;
	
	//监督机构意见
	private String advice;
	
	//本区域供应商id
	private String area_usergysid;

	public String getYpxxid() {
		return ypxxid;
	}

	public void setYpxxid(String ypxxid) {
		this.ypxxid = ypxxid;
	}

	public String getUsergysid() {
		return usergysid;
	}

	public void setUsergysid(String usergysid) {
		this.usergysid = usergysid;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getUsergysmc() {
		return usergysmc;
	}

	public void setUsergysmc(String usergysmc) {
		this.usergysmc = usergysmc;
	}

	public String getControlmc() {
		return controlmc;
	}

	public void setControlmc(String controlmc) {
		this.controlmc = controlmc;
	}

	public String getUseryyid() {
		return useryyid;
	}

	public void setUseryyid(String useryyid) {
		this.useryyid = useryyid;
	}

	public String getUseryymc() {
		return useryymc;
	}

	public void setUseryymc(String useryymc) {
		this.useryymc = useryymc;
	}

	public String getArea_usergysid() {
		return area_usergysid;
	}

	public void setArea_usergysid(String area_usergysid) {
		this.area_usergysid = area_usergysid;
	}
	
	
}
