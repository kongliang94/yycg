package yycg.business.pojo.vo;

import yycg.business.pojo.po.Ypxx;

public class YpxxCustom extends Ypxx{
	
	//交易状态名称
	private String jyztmc;

	//根据价格区间查询的接受参数
	private Float price_start;
	private Float price_end;
	public Float getPrice_start() {
		return price_start;
	}

	public void setPrice_start(Float price_start) {
		this.price_start = price_start;
	}

	public Float getPrice_end() {
		return price_end;
	}

	public void setPrice_end(Float price_end) {
		this.price_end = price_end;
	}

	public String getJyztmc() {
		return jyztmc;
	}

	public void setJyztmc(String jyztmc) {
		this.jyztmc = jyztmc;
	}
	
	
}
