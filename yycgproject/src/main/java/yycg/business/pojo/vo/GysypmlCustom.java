package yycg.business.pojo.vo;


public class GysypmlCustom extends YpxxCustom {
	
	private String gysypmlid;  //供应商药品目录
	private String ypxxid;     //药品信息id
	private String usergysid;  //供应商id
	private String usergysmc;  //供应商名称
	//控制状态名称 
	private String controlmc;
	
	private String control;

	public String getControlmc() {
		return controlmc;
	}

	public void setControlmc(String controlmc) {
		this.controlmc = controlmc;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

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

	public String getUsergysmc() {
		return usergysmc;
	}

	public void setUsergysmc(String usergysmc) {
		this.usergysmc = usergysmc;
	}

	public String getGysypmlid() {
		return gysypmlid;
	}

	public void setGysypmlid(String gysypmlid) {
		this.gysypmlid = gysypmlid;
	}
	
	

}
