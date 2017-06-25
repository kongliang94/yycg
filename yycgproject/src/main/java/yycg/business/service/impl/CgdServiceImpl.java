package yycg.business.service.impl;

import java.awt.print.Printable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yycg.base.dao.mapper.UserjdMapper;
import yycg.base.dao.mapper.UseryyMapper;
import yycg.base.pojo.po.Userjd;
import yycg.base.pojo.po.Useryy;
import yycg.base.process.context.Config;
import yycg.base.process.result.ResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.service.SystemConfigService;
import yycg.business.dao.mapper.YpxxMapper;
import yycg.business.dao.mapper.YybusinessMapper;
import yycg.business.dao.mapper.YycgdMapper;
import yycg.business.dao.mapper.YycgdMapperCustom;
import yycg.business.dao.mapper.YycgdmxMapper;
import yycg.business.dao.mapper.YycgdrkMapper;
import yycg.business.pojo.po.Gysypml;
import yycg.business.pojo.po.GysypmlControl;
import yycg.business.pojo.po.Ypxx;
import yycg.business.pojo.po.Yybusiness;
import yycg.business.pojo.po.Yycgd;
import yycg.business.pojo.po.YycgdExample;
import yycg.business.pojo.po.Yycgdmx;
import yycg.business.pojo.po.YycgdmxExample;
import yycg.business.pojo.po.Yycgdrk;
import yycg.business.pojo.po.YycgdrkExample;
import yycg.business.pojo.vo.GysypmlCustom;
import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;
import yycg.business.pojo.vo.YycgdrkCustom;
import yycg.business.service.CgdService;
import yycg.util.MyUtil;
import yycg.util.UUIDBuild;
/**
 * 
 * @author KL
 *
 */
@Service
public class CgdServiceImpl implements CgdService{

	@Autowired
	private YycgdMapper yycgdMapper;
	@Autowired
	private YycgdMapperCustom yycgdMapperCustom;
	@Autowired
	private YycgdmxMapper yycgdmxMapper;
	@Autowired
	private SystemConfigService systemConfigService;
	@Autowired
	private YpxxMapper ypxxMapper;
	@Autowired
	private UseryyMapper useryyMapper;
	@Autowired
	private UserjdMapper userjdMapper;
	@Autowired
	private YycgdrkMapper yycgdrkMapper;
	@Autowired
	private YybusinessMapper yybusinessMapper;
	@Override
	public String insertYycgd(String useryyid, String year,
			YycgdCustom yycgdCustom) throws Exception {
		String bm=yycgdMapperCustom.getYycgdBm(year);

		//采购单id主键和bm一致，目的是为了方便操作采购单,这样通过Id可以获取其年份
		yycgdCustom.setId(bm);
		yycgdCustom.setBm(bm); //设置采购单编码

		yycgdCustom.setUseryyid(useryyid);
		yycgdCustom.setCjtime(new Date());

		yycgdCustom.setZt("1");//采购单状态

		//设置年份，为了操作动态表
		yycgdCustom.setBusinessyear(year);
		yycgdMapper.insert(yycgdCustom);
		return bm;
	}



	@Override
	public YycgdCustom findYycgdById(String id) throws Exception {

		String year=id.substring(0, 4);//这里获取年份是为了获取动态表

		YycgdExample yycgdExample=new YycgdExample();
		//把id当做参数传给mapper
		YycgdExample.Criteria criteria=yycgdExample.createCriteria();
		criteria.andIdEqualTo(id);
		//将年份当做参数传递
		yycgdExample.setBusinessyear(year);
		Yycgd yycgd=null;
		YycgdCustom yycgdCustom=new YycgdCustom();
		List<Yycgd> list=yycgdMapper.selectByExample(yycgdExample);
		if (list!=null||list.size()==1) {
			yycgd=list.get(0);
			BeanUtils.copyProperties(yycgd, yycgdCustom);
		}else {
			// 抛出异常
			// 系统中找到该采购单。。。
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 501,
					null));
		}
		//返回一个YycgdCustom对象
		String yycgdztmc=systemConfigService.findDictinfoByDictcode("010",yycgd.getZt()).getInfo();
		yycgdCustom.setYycgdztmc(yycgdztmc);

		return yycgdCustom;
	}



	@Override
	public void updateYycgd(String id, YycgdCustom yycgdCustom) throws Exception{
		String year=id.substring(0, 4);//这里获取年份是为了获取动态表
		//获取
		YycgdCustom yycgdCustom_old=findYycgdById(id);

		yycgdCustom_old.setLxr(yycgdCustom.getLxr());
		yycgdCustom_old.setLxdh(yycgdCustom.getLxdh());
		yycgdCustom_old.setMc(yycgdCustom.getMc());
		yycgdCustom_old.setBz(yycgdCustom.getBz());

		yycgdCustom_old.setBusinessyear(year);
		yycgdMapper.updateByPrimaryKey(yycgdCustom_old);
	}


	@Override
	public List<YycgdmxCustom> findYycgdmxListByYycgdid(String yycgdid,
			YycgdQueryVo yycgdQueryVo) throws Exception {

		// 非空判断
		yycgdQueryVo = yycgdQueryVo != null ? yycgdQueryVo : new YycgdQueryVo();

		// 通过采购单id得到年份
		String businessyear = yycgdid.substring(0, 4);

		// 在service设置固定业务参数
		YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
		if (yycgdmxCustom == null) {
			yycgdmxCustom = new YycgdmxCustom();
		}
		yycgdmxCustom.setYycgdid(yycgdid);
		yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);

		// 设置年份
		yycgdQueryVo.setBusinessyear(businessyear);

		return yycgdMapperCustom.findYycgdmxList(yycgdQueryVo);
	}

	@Override
	public int findYycgdmxCountByYycgdid(String yycgdid,
			YycgdQueryVo yycgdQueryVo) throws Exception {
		// 非空判断
		yycgdQueryVo = yycgdQueryVo != null ? yycgdQueryVo : new YycgdQueryVo();
		// 通过采购单id得到年份
		String businessyear = yycgdid.substring(0, 4);
		// 在service设置固定业务参数
		YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
		if (yycgdmxCustom == null) {
			yycgdmxCustom = new YycgdmxCustom();
		}
		yycgdmxCustom.setYycgdid(yycgdid);
		yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);
		// 设置年份
		yycgdQueryVo.setBusinessyear(businessyear);
		return yycgdMapperCustom.findYycgdmxCount(yycgdQueryVo);
	}

	@Override
	public List<YycgdmxCustom> findAddYycgdmxList(String useryyid,
			String yycgdid, YycgdQueryVo yycgdQueryVo) throws Exception {

		String year=yycgdid.substring(0,4);

		//非空判断
		yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();

		Useryy useryy=yycgdQueryVo.getUseryy();
		//根据医院的id得到医院的对象
		Useryy useryy2=useryyMapper.selectByPrimaryKey(useryyid);
		//根据医院的对象得到医院的区域id
		String dq=useryy2.getDq();
		if (useryy==null) {
			useryy=new Useryy();
		}
		useryy.setDq(dq);
		yycgdQueryVo.setUseryy(useryy);
		YycgdCustom yycgdCustom=yycgdQueryVo.getYycgdCustom();
		if (yycgdCustom == null) {
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setId(yycgdid);
		//yycgdCustom.setBusinessyear(year);
		yycgdQueryVo.setBusinessyear(year);
		yycgdQueryVo.setYycgdCustom(yycgdCustom);
		return yycgdMapperCustom.findAddYycgdmxList(yycgdQueryVo);
	}



	@Override
	public int findAddYycgdmxCount(String useryyid, String yycgdid,
			YycgdQueryVo yycgdQueryVo) throws Exception {

		//根据医院id得到医院区域id
		Useryy useryy = useryyMapper.selectByPrimaryKey(useryyid);

		//一开始忘记设计医院区域，运行时报错Java.sql.SQLException: 无效的列类型
		String dq = useryy.getDq();//医院区域id

		//向yycgdQueryVo设置业务参数
		//医院地区
		Useryy useryy_l = yycgdQueryVo.getUseryy();
		if(useryy_l == null){
			useryy_l = new Useryy();
		}
		useryy_l.setDq(dq);
		yycgdQueryVo.setUseryy(useryy_l);

		//采购单id
		YycgdCustom yycgdCustom = yycgdQueryVo.getYycgdCustom();
		if(yycgdCustom == null){
			yycgdCustom = new YycgdCustom();
		}
		yycgdCustom.setId(yycgdid);
		yycgdQueryVo.setYycgdCustom(yycgdCustom);

		//设置年份
		String businessyear = yycgdid.substring(0, 4);
		yycgdQueryVo.setBusinessyear(businessyear);


		return yycgdMapperCustom.findAddYycgdmxCount(yycgdQueryVo);
	}



	@Override
	public void insertYycgdmx(String yycgdid, String ypxxid, String usergysid)
			throws Exception {
		//只允许添加采购单药品目录中没有的药品
		//校验方法：先根据采购单id和药品id查询药品目录记录，如果查询到说明已存在
		Yycgdmx yycgdmx=findYycgdmxByYycgdidandYpxxid(yycgdid, ypxxid);

		if (yycgdmx!=null) {
			// 说明已存在
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 401, null));
		}
		// 药品的交易状态为暂停不允许添加
		// 根据药品id查询药品信息

		Ypxx ypxx=ypxxMapper.selectByPrimaryKey(ypxxid);

		if (ypxx==null) {
			// 系统中找不到系统信息
			// .....
		}
		// 药品交易状态
		String jyzt = ypxx.getJyzt();
		if (jyzt.equals("2")) {
			//药品状态为暂停不允许添加
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 403, 
					new Object[]{ypxx.getBm(),ypxx.getMc()}));
		}

		String year=yycgdid.substring(0,4);
		// 向插入一条记录
		Yycgdmx yycgdmx_insert=new Yycgdmx();
		yycgdmx_insert.setBusinessyear(year);
		yycgdmx_insert.setId(UUIDBuild.getUUID());
		yycgdmx_insert.setUsergysid(usergysid);
		yycgdmx_insert.setYycgdid(yycgdid);
		yycgdmx_insert.setYpxxid(ypxxid);
		yycgdmx_insert.setZbjg(ypxx.getZbjg());//中标价格
		yycgdmx_insert.setJyjg(ypxx.getZbjg());//本系统交易价格和中标价格相等
		yycgdmx_insert.setCgzt("1");//默认1、未确认送货

		yycgdmxMapper.insert(yycgdmx_insert);



	}



	@Override
	public Yycgdmx findYycgdmxByYycgdidandYpxxid(String yycgdid, String ypxxid)
			throws Exception {
		YycgdmxExample yycgdmxExample=new YycgdmxExample();
		YycgdmxExample.Criteria criteria=yycgdmxExample.createCriteria();
		criteria.andYycgdidEqualTo(yycgdid);
		criteria.andYpxxidEqualTo(ypxxid);

		// 设置年份
		String year = yycgdid.substring(0, 4);
		yycgdmxExample.setBusinessyear(year);

		List<Yycgdmx> list= yycgdmxMapper.selectByExample(yycgdmxExample);
		if (list!=null&&list.size()==1) {
			return list.get(0);
		}
		return null;
	}



	@Override
	public void updateYycgdmx(String yycgdid, String ypxxid, Integer cgl) throws Exception{

		String year=yycgdid.substring(0,4);

		//判断该药品是否存在
		Yycgdmx yycgdmx=findYycgdmxByYycgdidandYpxxid(yycgdid, ypxxid);
		if (yycgdmx==null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,509, null));
		}
		//判断采购单数量大于0
		if (cgl<=0) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,500, null));
		}
		//更新采购单数量和采购单价格
		Float jyjg=yycgdmx.getJyjg();
		Float cgjg=jyjg*cgl;
		Yycgdmx yycgdmx_update=new Yycgdmx();
		yycgdmx_update.setId(yycgdmx.getId());
		yycgdmx_update.setBusinessyear(year);
		yycgdmx_update.setCgl(cgl);
		yycgdmx_update.setCgje(cgjg);

		yycgdmxMapper.updateByPrimaryKeySelective(yycgdmx_update);
	}



	@Override
	public void deleteYycgdmx(String yycgdid, YycgdmxCustom yycgdmxCustom)
			throws Exception {

		if(yycgdmxCustom == null || yycgdmxCustom.getYpxxid() == null 
				|| yycgdid == null){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}

		Yycgd yycgd = findYycgdById(yycgdid);
		//找不到采购单
		if(yycgd == null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,501, null));
		}
		//采购单已提交不允许删除药品
		if (yycgd.getZt().equals("1")) {


			Yycgdmx yycgdmx= findYycgdmxByYycgdidandYpxxid(yycgdid,yycgdmxCustom.getYpxxid());
			if (yycgdmx==null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 509, null));
			}
			YycgdmxExample yycgdmxExample=new YycgdmxExample();
			YycgdmxExample.Criteria criteria= yycgdmxExample.createCriteria();
			criteria.andYycgdidEqualTo(yycgdid);
			criteria.andYpxxidEqualTo(yycgdmxCustom.getYpxxid());
			// 设置年份
			String year = yycgdid.substring(0, 4);
			yycgdmxExample.setBusinessyear(year);
			yycgdmxMapper.deleteByExample(yycgdmxExample);
		}else {
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 506, null));
		}
	}



	@Override
	public List<YycgdCustom> findYycgdList(String useryyid,String year,
			YycgdQueryVo yycgdQueryVo) throws Exception {
		//非空判断
		yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();
		yycgdQueryVo.setBusinessyear(year);

		//设置医院id
		Useryy useryy=yycgdQueryVo.getUseryy();
		if (useryy==null) {
			useryy=new Useryy();
		}
		if (yycgdQueryVo.getUseryyCustom()==null) {
			useryy.setId(useryyid);
			yycgdQueryVo.setUseryy(useryy);
		}

		return yycgdMapperCustom.findYycgdList(yycgdQueryVo);
	}



	@Override
	public int findYycgdCount(String useryyid,String year, YycgdQueryVo yycgdQueryVo)
			throws Exception {
		yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();
		yycgdQueryVo.setBusinessyear(year);

		//设置医院id
		Useryy useryy=yycgdQueryVo.getUseryy();
		if (useryy==null) {
			useryy=new Useryy();
		}
		if (yycgdQueryVo.getUseryyCustom()==null) {
			
				useryy.setId(useryyid);
				yycgdQueryVo.setUseryy(useryy);
			
		}
		
			
		return yycgdMapperCustom.findYycgdCount(yycgdQueryVo);
	}

	/**
	 * 删除采购单
	 */
	@Override
	public void deleteYycgd(String id, String useryyid) throws Exception {

		//找不到采购单
		//采购单已提交不允许删除
		//只允许删除自己创建的采购单
		//删除采购单主表
		//删除采购单明细
		//找不到采购单
		if(id == null || id.equals("")){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		String year = id.substring(0, 4);
		Yycgd yycgd_del = findYycgdById(id);
				if(yycgd_del == null){
					ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,501, null));
				}
				//采购单已提交不允许删除
				if(!yycgd_del.getZt().equals("1")){
					ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,506, null));
				}
				//只允许删除自己创建的采购单
				if(!yycgd_del.getUseryyid().equals(useryyid)){
					ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,507, null));
				}
				
				//删除采购单主表
				YycgdExample yycgdExample1=new  YycgdExample();
				yycgdExample1.setBusinessyear(year);//指定年份
				YycgdExample.Criteria criteira1=yycgdExample1.createCriteria();
				criteira1.andIdEqualTo(id);
				yycgdMapper.deleteByExample(yycgdExample1);
				
				//删除采购单明细
				YycgdmxExample yycgdmxExample1=new  YycgdmxExample();
				yycgdmxExample1.setBusinessyear(year);//指定年份
				YycgdmxExample.Criteria criteira2=yycgdmxExample1.createCriteria();
				criteira2.andYycgdidEqualTo(id);
				yycgdmxMapper.deleteByExample(yycgdmxExample1);	
	}


	/**
	 * 对采购单数据校验后提交采购单，实际更新采购单的状态
	 */
	@Override
	public void saveYycgdSubmit(String yycgdid, String useryyid)
			throws Exception {
		if (yycgdid==null||yycgdid=="") {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,901 , null));
		}
		Yycgd yycgd = findYycgdById(yycgdid);
		//找不到采购单
		if(yycgd == null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,501, null));
		}
		//采购单已提交不允许再次提交药品
		if (!(yycgd.getZt().equals("1")||yycgd.getZt().equals("4"))) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,502, null));
		}

		//只允许提交自己创建的采购单
		if (!yycgd.getUseryyid().equals(useryyid)) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,503, null));
		}

		int count=findYycgdmxCountByYycgdid(yycgdid, null);
		//根据采购单id查询采购单明细，进行信息判断校验。
		if (count==0) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,504, null));
		}
		List<YycgdmxCustom> yycgdmxCustoms=findYycgdmxListByYycgdid(yycgdid,null);
		YycgdmxCustom yycgdmxCustom=null;
		for (int i = 0; i < yycgdmxCustoms.size(); i++) {
			yycgdmxCustom=yycgdmxCustoms.get(i);
			if (yycgdmxCustom.getCgl()==null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,505, null));
			}
			//药品信息状态这里不做判断，如果要判断需要判断药品交易状态和药品供货状态
			//校验药品供货状态是否正常，无法供货包括两个方面：1供货供应此药品但监督单位不允许，2供货商不供应本药品
		}
		//根据采购单号更新采购单状态，更新为2：已提交未审核

		String year=yycgdid.substring(0,4);
		YycgdCustom yycgdcustom_update=new YycgdCustom();
		yycgdcustom_update.setId(yycgdid);
		yycgdcustom_update.setZt("2");
		yycgdcustom_update.setTjtime(MyUtil.getNowDate());//提交时间
		yycgdcustom_update.setBusinessyear(year);
		//更新采购单
		yycgdMapper.updateByPrimaryKeySelective(yycgdcustom_update);
	}



	@Override
	public List<YycgdCustom> findCheckYycgdList(String userjdid, String year,
			YycgdQueryVo yycgdQueryVo) throws Exception {
		//非空判断
		yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();
		yycgdQueryVo.setBusinessyear(year);
		YycgdCustom yycgdCustom=yycgdQueryVo.getYycgdCustom();
		if (yycgdCustom==null) {
			yycgdCustom=new YycgdCustom();
		}
		yycgdCustom.setZt("2");
		yycgdQueryVo.setYycgdCustom(yycgdCustom);

		Userjd userjd=userjdMapper.selectByPrimaryKey(userjdid);
		//		if (userjd==null) {
		//			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,561, null));
		//		}
		Useryy useryy=yycgdQueryVo.getUseryy();
		String dq=userjd.getDq();
		if (useryy==null) {
			useryy=new Useryy();
		}
		useryy.setDq(dq);
		yycgdQueryVo.setUseryy(useryy);
		return yycgdMapperCustom.findYycgdList(yycgdQueryVo);
	}



	@Override
	public int findCheckYycgdCount(String userjdid, String year,
			YycgdQueryVo yycgdQueryVo) throws Exception {
		yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();
		yycgdQueryVo.setBusinessyear(year);
		YycgdCustom yycgdCustom=yycgdQueryVo.getYycgdCustom();
		if (yycgdCustom==null) {
			yycgdCustom=new YycgdCustom();
		}
		yycgdCustom.setZt("2");
		yycgdQueryVo.setYycgdCustom(yycgdCustom);

		Userjd userjd=userjdMapper.selectByPrimaryKey(userjdid);
		//		if (userjd==null) {
		//			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,561, null));
		//		}
		Useryy useryy=yycgdQueryVo.getUseryy();

		if (useryy==null) {
			useryy=new Useryy();
		}
		String dq=userjd.getDq();
		useryy.setDq(dq);
		yycgdQueryVo.setUseryy(useryy);
		return yycgdMapperCustom.findYycgdCount(yycgdQueryVo);
	}



	@Override
	public void saveYycgdCheckStatus(String yycgdid, YycgdCustom yycgdCustom)
			throws Exception {
		// 采购单状态为审核中方可提交审核
		// 采购单状态为未提交或审核不通过时方可提交
		Yycgd yycgd=findYycgdById(yycgdid);
		if (yycgd==null) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 501, null));
		}
		String zt=yycgd.getZt();
		if (!zt.equals("2")) {
			// 采购单只允许在审核中方可提交 审核
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 514,
					null));
		}
		//设置审核通过或者不通过，否则提示
		if (yycgdCustom==null
				||yycgdCustom.getZt()==null
				||(!yycgdCustom.getZt().equals("3")&&!yycgdCustom.getZt().equals("4"))) {
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 513,
					null));
		}

		//更新采购单，这里将采购单状态以及审批意见与采购单信息放在一张表，所以会有点问题，如果想要记录信息审核信息可以另外建一张审核表
		String year=yycgdid.substring(0, 4);

		Yycgd yycgd_update = new Yycgd();
		yycgd_update.setId(yycgdid);

		// 更新状态
		yycgd_update.setZt(yycgdCustom.getZt());
		// 更新审核时间
		yycgd_update.setShtime(MyUtil.getNowDate());
		// 更新审核意见
		yycgd_update.setShyj(yycgdCustom.getShyj());
		yycgd_update.setBusinessyear(year);

		yycgdMapper.updateByPrimaryKeySelective(yycgd_update);


		//采购明细数据聚合
		if(yycgdCustom.getZt().equals("3")){
			//如果审核通过进行数据聚合

			//将采购单明细记录插入到交易明细表中
			//取出采购单明细记录
			List<YycgdmxCustom> yycgdmxList = this.findYycgdmxListByYycgdid(yycgdid, null);
			//将采购单明细记录插入到交易明细表中
			for(YycgdmxCustom yycgdmxCustom:yycgdmxList){
				//创建一个交易明细对象
				Yybusiness yybusiness = new Yybusiness();
				yybusiness.setBusinessyear(year);

				yybusiness.setId(UUIDBuild.getUUID());
				yybusiness.setYycgdid(yycgdid);//采购单id
				yybusiness.setYpxxid(yycgdmxCustom.getId());//药品id
				yybusiness.setUseryyid(yycgdmxCustom.getUseryyid());//医院id
				yybusiness.setZbjg(yycgdmxCustom.getZbjg());//中标价格
				yybusiness.setJyjg(yycgdmxCustom.getJyjg());//交易价格
				yybusiness.setCgl(yycgdmxCustom.getCgl());//采购量
				yybusiness.setCgje(yycgdmxCustom.getCgje());//采购金额
				yybusiness.setCgzt(yycgdmxCustom.getCgzt());//采购状态
				yybusiness.setUsergysid(yycgdmxCustom.getUsergysid());//供货商

				yybusinessMapper.insert(yybusiness);

			}
			}
		}



		@Override
		public List<YycgdmxCustom> findDisposeYycgdList(String usergysid,
				String year, YycgdQueryVo yycgdQueryVo) throws Exception {

			// 供货商只允许查询自己供应的采购药品信息
			// 设置供货商id
			YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
			yycgdmxCustom = yycgdmxCustom != null ? yycgdmxCustom
					: new YycgdmxCustom();
			yycgdmxCustom.setUsergysid(usergysid);

			// 采购药品明细状态为“未确认送货”
			String cgzt = "1";
			yycgdmxCustom.setCgzt(cgzt);

			yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);

			// 采购单为审核通过
			String zt = "3";
			YycgdCustom yycgdCustom=yycgdQueryVo.getYycgdCustom();
			if (yycgdCustom==null) {
				yycgdCustom=new YycgdCustom();
			}
			yycgdCustom.setZt(zt);
			yycgdQueryVo.setYycgdCustom(yycgdCustom);

			//设置年份
			yycgdQueryVo.setBusinessyear(year);
			return yycgdMapperCustom.findYycgdmxList(yycgdQueryVo);
		}



		@Override
		public int findDisposeYycgdCount(String usergysid, String year,
				YycgdQueryVo yycgdQueryVo) throws Exception {
			// 供货商只允许查询自己供应的采购药品信息
			// 设置供货商id
			YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
			yycgdmxCustom = yycgdmxCustom != null ? yycgdmxCustom
					: new YycgdmxCustom();
			yycgdmxCustom.setUsergysid(usergysid);

			// 采购药品明细状态为“未确认送货”
			String cgzt = "1";
			yycgdmxCustom.setCgzt(cgzt);

			yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);

			// 采购单为审核通过
			String zt = "3";
			YycgdCustom yycgdCustom=yycgdQueryVo.getYycgdCustom();
			if (yycgdCustom==null) {
				yycgdCustom=new YycgdCustom();
			}
			yycgdCustom.setZt(zt);
			yycgdQueryVo.setYycgdCustom(yycgdCustom);

			//设置年份
			yycgdQueryVo.setBusinessyear(year);
			return yycgdMapperCustom.findYycgdmxCount(yycgdQueryVo);
		}


		@Override
		public void saveSendStatus(String yycgdid, String ypxxid) throws Exception {

			// 查询出采购药品记录
			Yycgdmx yycgdmx = this.findYycgdmxByYycgdidandYpxxid(yycgdid, ypxxid);

			if (yycgdmx == null) {
				// 提示：找到采购药品明细记录
				// ...
			}
			// 采购状态
			String cgzt = yycgdmx.getCgzt();
			if (!cgzt.equals("1")) {
				// 提示：采购药品在未确定 送货时方可发货
				// ...
			}

			// 设置更新状态为已发货
			yycgdmx.setCgzt("2");
			// 年份
			String year = yycgdid.substring(0, 4);
			yycgdmx.setBusinessyear(year);

			yycgdmxMapper.updateByPrimaryKey(yycgdmx);

		}



		@Override
		public List<YycgdmxCustom> findYycgdReceiveList(String useryyid,
				String year, YycgdQueryVo yycgdQueryVo) throws Exception {
			// 只允许查询自己入库的采购药品信息

			YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
			yycgdmxCustom = yycgdmxCustom != null ? yycgdmxCustom
					: new YycgdmxCustom();
			yycgdmxCustom.setUseryyid(useryyid);
			if (yycgdmxCustom.getCgzt()=="3") {

			}else {
				//采购药品明细状态为“已发货”
				String cgzt = "2";
				yycgdmxCustom.setCgzt(cgzt);
			}

			yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);

			//设置年份
			yycgdQueryVo.setBusinessyear(year);
			return yycgdMapperCustom.findYycgdmxList(yycgdQueryVo);

		}



		@Override
		public int findYycgdReceivCount(String useryyid, String year,
				YycgdQueryVo yycgdQueryVo) throws Exception {
			YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
			yycgdmxCustom = yycgdmxCustom != null ? yycgdmxCustom
					: new YycgdmxCustom();
			yycgdmxCustom.setUseryyid(useryyid);
			if (yycgdmxCustom.getCgzt()=="3") {
				
			}else {
				//采购药品明细状态为“已发货”
				String cgzt = "2";
				yycgdmxCustom.setCgzt(cgzt);
			}
			

			yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);

			//设置年份
			yycgdQueryVo.setBusinessyear(year);
			return yycgdMapperCustom.findYycgdmxCount(yycgdQueryVo);
		}



		@Override
		public void saveYycgdrk(String yycgdid, String ypxxid,
				YycgdrkCustom yycgdrkCustom) throws Exception {
			/*约束：
		采购单药品明细状态为“已发货”，方可入库
		入库量小于等于采购量方可入库
	            数据库操作：
		向入库信息表插入记录
			入库金额= 入库量*交易价
		更新采购单明细表状态为“已入库”*/

			Yycgdmx yycgdmx=findYycgdmxByYycgdidandYpxxid(yycgdid, ypxxid);
			if (yycgdmx==null) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,509, null));
			}

			// 采购状态
			String cgzt = yycgdmx.getCgzt();
			if (!cgzt.equals("2")) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,520, null));
			}

			Integer rkl=yycgdrkCustom.getRkl();
			Integer cgl=yycgdmx.getCgl();
			if (rkl>cgl) {
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,519, null));
			}



			// 设置更新状态为已发货
			yycgdmx.setCgzt("3");
			// 年份
			String year = yycgdid.substring(0, 4);
			yycgdmx.setBusinessyear(year);
			yycgdmxMapper.updateByPrimaryKey(yycgdmx);

			//向入库信息表插入记录

			Yycgdrk yycgdrk = new Yycgdrk();
			//将页面提交的入库信息设置到yycgdrk
			BeanUtils.copyProperties(yycgdrkCustom, yycgdrk);

			yycgdrk.setBusinessyear(year);
			yycgdrk.setId(UUIDBuild.getUUID());//主键
			yycgdrk.setYycgdid(yycgdid);//采购单id
			yycgdrk.setYpxxid(ypxxid);//药品id
			yycgdrk.setRktime(MyUtil.getNowDate());//入库时间

			//入库金额= 入库量*交易价
			//采购药品交易价
			Float jyjg = yycgdmx.getJyjg();

			Float rkje =  jyjg* rkl;
			//设置入库金额
			yycgdrk.setRkje(rkje);
			//采购状态设置固定值 为3已入库,此字段为统计分析时数据聚合使用
			yycgdrk.setCgzt("3");


			yycgdrkMapper.insert(yycgdrk);

		}



		@Override
		public List<YycgdmxCustom> findYycgdmxListSum(String yycgdid,
				YycgdQueryVo yycgdQueryVo) throws Exception {
			yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();
			//设置采购单id
			YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
			yycgdmxCustom = yycgdmxCustom!=null?yycgdmxCustom:new YycgdmxCustom();
			yycgdmxCustom.setYycgdid(yycgdid);
			yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);
			
			//设置年份
			String businessyear = yycgdid.substring(0, 4);
			yycgdQueryVo.setBusinessyear(businessyear);

			return yycgdMapperCustom.findYycgdmxListSum(yycgdQueryVo);
		}



		@Override
		public List<YycgdmxCustom> findYycgdrkList(String useryyid,
				String year, YycgdQueryVo yycgdQueryVo) throws Exception {
			YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
			yycgdmxCustom = yycgdmxCustom != null ? yycgdmxCustom
					: new YycgdmxCustom();
			yycgdmxCustom.setUseryyid(useryyid);
			if (yycgdmxCustom.getCgzt()=="3") {

			}else {
				//采购药品明细状态为“已发货”
				String cgzt = "2";
				yycgdmxCustom.setCgzt(cgzt);
			}

			yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);

			//设置年份
			yycgdQueryVo.setBusinessyear(year);
			return yycgdMapperCustom.findYycgdmxrkList(yycgdQueryVo);
		}



		@Override
		public int findYycgdrkCount(String useryyid, String year,
				YycgdQueryVo yycgdQueryVo) throws Exception {
			YycgdmxCustom yycgdmxCustom = yycgdQueryVo.getYycgdmxCustom();
			yycgdmxCustom = yycgdmxCustom != null ? yycgdmxCustom
					: new YycgdmxCustom();
			yycgdmxCustom.setUseryyid(useryyid);
			

			yycgdQueryVo.setYycgdmxCustom(yycgdmxCustom);

			//设置年份
			yycgdQueryVo.setBusinessyear(year);
			return yycgdMapperCustom.findYycgdmxrkCount(yycgdQueryVo);
		}


	}
