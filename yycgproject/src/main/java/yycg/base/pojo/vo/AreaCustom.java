package yycg.base.pojo.vo;

import yycg.base.pojo.po.BssSysArea;



public class AreaCustom extends BssSysArea {
	//是否为叶子
	private String isleaf;
	//同areaid
	private String id;
	//同areaname
	private String name;
	
	//是否为父亲
	private String isParent;

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		if(isleaf!=null){
			if(isleaf.equals("0")){
				isParent="true";
			}else{
				isParent="false";
			}
		}
		this.isleaf = isleaf;
	}



	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
