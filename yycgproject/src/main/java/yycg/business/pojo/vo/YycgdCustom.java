package yycg.business.pojo.vo;

import java.util.Date;

import yycg.business.pojo.po.Yycgd;

public class YycgdCustom extends Yycgd{

	private String useryymc;
	public String getUseryymc() {
		return useryymc;
	}

	public void setUseryymc(String useryymc) {
		this.useryymc = useryymc;
	}

	//采购单状态名称
	private String yycgdztmc;
	
	//根据日期区间
	private Date cjtime_start;
	private Date cjtime_end;

	public Date getCjtime_start() {
		return cjtime_start;
	}

	public void setCjtime_start(Date cjtime_start) {
		this.cjtime_start = cjtime_start;
	}

	public Date getCjtime_end() {
		return cjtime_end;
	}

	public void setCjtime_end(Date cjtime_end) {
		this.cjtime_end = cjtime_end;
	}

	public String getYycgdztmc() {
		return yycgdztmc;
	}

	public void setYycgdztmc(String yycgdztmc) {
		this.yycgdztmc = yycgdztmc;
	}

	//退货单
	private String yythdid;
	public String getYythdid() {
		return yythdid;
	}

	public void setYythdid(String yythdid) {
		this.yythdid = yythdid;
	}

	public String getAddyythdid() {
		return addyythdid;
	}

	public void setAddyythdid(String addyythdid) {
		this.addyythdid = addyythdid;
	}

	public String getYythdbm() {
		return yythdbm;
	}

	public void setYythdbm(String yythdbm) {
		this.yythdbm = yythdbm;
	}

	public String getYythdmc() {
		return yythdmc;
	}

	public void setYythdmc(String yythdmc) {
		this.yythdmc = yythdmc;
	}

	public Integer getThl() {
		return thl;
	}

	public void setThl(Integer thl) {
		this.thl = thl;
	}

	public Float getThje() {
		return thje;
	}

	public void setThje(Float thje) {
		this.thje = thje;
	}

	public String getThyy() {
		return thyy;
	}

	public void setThyy(String thyy) {
		this.thyy = thyy;
	}

	public String getThdzt() {
		return thdzt;
	}

	public void setThdzt(String thdzt) {
		this.thdzt = thdzt;
	}

	public String getThdztmc() {
		return thdztmc;
	}

	public void setThdztmc(String thdztmc) {
		this.thdztmc = thdztmc;
	}

	public String getThzt() {
		return thzt;
	}

	public void setThzt(String thzt) {
		this.thzt = thzt;
	}

	public String getThztmc() {
		return thztmc;
	}

	public void setThztmc(String thztmc) {
		this.thztmc = thztmc;
	}

	public Date getKsthdate() {
		return ksthdate;
	}

	public void setKsthdate(Date ksthdate) {
		this.ksthdate = ksthdate;
	}

	public Date getJsthdate() {
		return jsthdate;
	}

	public void setJsthdate(Date jsthdate) {
		this.jsthdate = jsthdate;
	}

	public String getIssetupThl() {
		return issetupThl;
	}

	public void setIssetupThl(String issetupThl) {
		this.issetupThl = issetupThl;
	}

	//采购单信息
		private String yycgdid;
		private String yycgdbm;
		private String yycgdmc;

		
		public String getYycgdid() {
			return yycgdid;
		}

		public void setYycgdid(String yycgdid) {
			this.yycgdid = yycgdid;
		}

		public String getYycgdbm() {
			return yycgdbm;
		}

		public void setYycgdbm(String yycgdbm) {
			this.yycgdbm = yycgdbm;
		}

		public String getYycgdmc() {
			return yycgdmc;
		}

		public void setYycgdmc(String yycgdmc) {
			this.yycgdmc = yycgdmc;
		}

		public Date getKscgdate() {
			return kscgdate;
		}

		public void setKscgdate(Date kscgdate) {
			this.kscgdate = kscgdate;
		}

		public Date getJscgdate() {
			return jscgdate;
		}

		public void setJscgdate(Date jscgdate) {
			this.jscgdate = jscgdate;
		}

		public String getYycgdmxid() {
			return yycgdmxid;
		}

		public void setYycgdmxid(String yycgdmxid) {
			this.yycgdmxid = yycgdmxid;
		}

		public String getControl() {
			return control;
		}

		public void setControl(String control) {
			this.control = control;
		}

		public Float getJyjg() {
			return jyjg;
		}

		public void setJyjg(Float jyjg) {
			this.jyjg = jyjg;
		}

		public Integer getCgl() {
			return cgl;
		}

		public void setCgl(Integer cgl) {
			this.cgl = cgl;
		}

		public Float getCgje() {
			return cgje;
		}

		public void setCgje(Float cgje) {
			this.cgje = cgje;
		}

		public String getCgzt() {
			return cgzt;
		}

		public void setCgzt(String cgzt) {
			this.cgzt = cgzt;
		}

		public String getCgztmc() {
			return cgztmc;
		}

		public void setCgztmc(String cgztmc) {
			this.cgztmc = cgztmc;
		}

		public String getIssetupCgl() {
			return issetupCgl;
		}

		public void setIssetupCgl(String issetupCgl) {
			this.issetupCgl = issetupCgl;
		}

		public String getZlccmc() {
			return zlccmc;
		}

		public void setZlccmc(String zlccmc) {
			this.zlccmc = zlccmc;
		}

		public String getJyztmc() {
			return jyztmc;
		}

		public void setJyztmc(String jyztmc) {
			this.jyztmc = jyztmc;
		}

		public Float getZbjgupper() {
			return zbjgupper;
		}

		public void setZbjgupper(Float zbjgupper) {
			this.zbjgupper = zbjgupper;
		}

		public Float getZbjglower() {
			return zbjglower;
		}

		public void setZbjglower(Float zbjglower) {
			this.zbjglower = zbjglower;
		}

		public String getYpxxid() {
			return ypxxid;
		}

		public void setYpxxid(String ypxxid) {
			this.ypxxid = ypxxid;
		}

		public String getYpxxbm() {
			return ypxxbm;
		}

		public void setYpxxbm(String ypxxbm) {
			this.ypxxbm = ypxxbm;
		}

		public String getYpxxmc() {
			return ypxxmc;
		}

		public void setYpxxmc(String ypxxmc) {
			this.ypxxmc = ypxxmc;
		}

		public String getScqymc() {
			return scqymc;
		}

		public void setScqymc(String scqymc) {
			this.scqymc = scqymc;
		}

		public String getSpmc() {
			return spmc;
		}

		public void setSpmc(String spmc) {
			this.spmc = spmc;
		}

		public Double getZbjg() {
			return zbjg;
		}

		public void setZbjg(Double zbjg) {
			this.zbjg = zbjg;
		}

		public String getZpdz() {
			return zpdz;
		}

		public void setZpdz(String zpdz) {
			this.zpdz = zpdz;
		}

		public String getPzwh() {
			return pzwh;
		}

		public void setPzwh(String pzwh) {
			this.pzwh = pzwh;
		}

		public Date getPzwhyxq() {
			return pzwhyxq;
		}

		public void setPzwhyxq(Date pzwhyxq) {
			this.pzwhyxq = pzwhyxq;
		}

		public String getJky() {
			return jky;
		}

		public void setJky(String jky) {
			this.jky = jky;
		}

		public String getBzcz() {
			return bzcz;
		}

		public void setBzcz(String bzcz) {
			this.bzcz = bzcz;
		}

		public String getBzdw() {
			return bzdw;
		}

		public void setBzdw(String bzdw) {
			this.bzdw = bzdw;
		}

		public Double getLsjg() {
			return lsjg;
		}

		public void setLsjg(Double lsjg) {
			this.lsjg = lsjg;
		}

		public String getLsjgcc() {
			return lsjgcc;
		}

		public void setLsjgcc(String lsjgcc) {
			this.lsjgcc = lsjgcc;
		}

		public String getYpjybg() {
			return ypjybg;
		}

		public void setYpjybg(String ypjybg) {
			this.ypjybg = ypjybg;
		}

		public String getYpjybgbm() {
			return ypjybgbm;
		}

		public void setYpjybgbm(String ypjybgbm) {
			this.ypjybgbm = ypjybgbm;
		}

		public Date getYpjybgyxq() {
			return ypjybgyxq;
		}

		public void setYpjybgyxq(Date ypjybgyxq) {
			this.ypjybgyxq = ypjybgyxq;
		}

		public String getJyzt() {
			return jyzt;
		}

		public void setJyzt(String jyzt) {
			this.jyzt = jyzt;
		}

		public String getDw() {
			return dw;
		}

		public void setDw(String dw) {
			this.dw = dw;
		}

		public String getJx() {
			return jx;
		}

		public void setJx(String jx) {
			this.jx = jx;
		}

		public String getGg() {
			return gg;
		}

		public void setGg(String gg) {
			this.gg = gg;
		}

		public String getZhxs() {
			return zhxs;
		}

		public void setZhxs(String zhxs) {
			this.zhxs = zhxs;
		}

		public String getLb() {
			return lb;
		}

		public void setLb(String lb) {
			this.lb = lb;
		}

		public String getCpsm() {
			return cpsm;
		}

		public void setCpsm(String cpsm) {
			this.cpsm = cpsm;
		}

		public Integer getRkl() {
			return rkl;
		}

		public void setRkl(Integer rkl) {
			this.rkl = rkl;
		}

		public Float getRkje() {
			return rkje;
		}

		public void setRkje(Float rkje) {
			this.rkje = rkje;
		}

		public String getRkdh() {
			return rkdh;
		}

		public void setRkdh(String rkdh) {
			this.rkdh = rkdh;
		}

		public String getYpph() {
			return ypph;
		}

		public void setYpph(String ypph) {
			this.ypph = ypph;
		}

		public Float getYpyxq() {
			return ypyxq;
		}

		public void setYpyxq(Float ypyxq) {
			this.ypyxq = ypyxq;
		}

		public Date getRktime() {
			return rktime;
		}

		public void setRktime(Date rktime) {
			this.rktime = rktime;
		}

		public Date getStartrktime() {
			return startrktime;
		}

		public void setStartrktime(Date startrktime) {
			this.startrktime = startrktime;
		}

		public Date getEndrktime() {
			return endrktime;
		}

		public void setEndrktime(Date endrktime) {
			this.endrktime = endrktime;
		}

		private Date kscgdate;
		private Date jscgdate;
		
		//采购单药品明细
		//采购单明细id
		private String yycgdmxid;
		
		//供货状态
		private String control;
		//交易价格
		private Float jyjg;
		//采购量
		private Integer cgl;
		//采购金额
		private Float cgje;
		//采购状态
		private String cgzt;
		private String cgztmc;
		//是否设置采购量，交易价等0:未设置 ，1：已设置
		private String issetupCgl; 
		
		//药品信息
		private String zlccmc;
		private String jyztmc;
		//价格上限
		private Float zbjgupper;
		//价格下限
		private Float zbjglower;
		private String ypxxid;
		private String ypxxbm;
		private String ypxxmc;

	    private String scqymc;

	    private String spmc;

	    private Double zbjg;

	    private String zpdz;

	    private String pzwh;

	    private Date pzwhyxq;

	    private String jky;

	    private String bzcz;

	    private String bzdw;

	    private Double lsjg;

	    private String lsjgcc;

	    private String ypjybg;

	    private String ypjybgbm;

	    private Date ypjybgyxq;

	    private String jyzt;

	    private String dw;

	    private String jx;

	    private String gg;

	    private String zhxs;

	    private String lb;

	    private String cpsm;
		
		//入库
		private Integer rkl;
		private Float rkje;
		private String rkdh;
		private String ypph;
		private Float ypyxq;
		private Date rktime;
		//开始入库时间
		private Date startrktime;
		//结束入库时间
		private Date endrktime;
	private String addyythdid;//标识添加退货药品所在的退货单id
	private String yythdbm;
	private String yythdmc;
	private Integer thl;
	private Float thje;
	private String thyy;//退货原因
	private String thdzt;//退货单状态
	private String thdztmc;//退货单状态
	private String thzt;//退货状态
	private String thztmc;
	private Date ksthdate;
	private Date jsthdate;
	//是否设置退货量，交易价等0:未设置 ，1：已设置
	private String issetupThl; 
	
}
