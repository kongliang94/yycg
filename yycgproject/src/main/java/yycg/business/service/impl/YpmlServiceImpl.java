package yycg.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.base.service.SystemConfigService;
import yycg.business.dao.mapper.GysypmlControlMapper;
import yycg.business.dao.mapper.GysypmlMapper;
import yycg.business.dao.mapper.GysypmlMapperCustom;
import yycg.business.dao.mapper.YpxxMapper;
import yycg.business.pojo.po.Gysypml;
import yycg.business.pojo.po.GysypmlControl;
import yycg.business.pojo.po.GysypmlControlExample;
import yycg.business.pojo.po.GysypmlExample;
import yycg.business.pojo.po.Ypxx;
import yycg.business.pojo.vo.GysypmlCustom;
import yycg.business.pojo.vo.GysypmlQueryVo;
import yycg.business.service.YpmlService;
import yycg.util.UUIDBuild;

@Service
public class YpmlServiceImpl implements YpmlService {

	@Autowired
	private GysypmlMapperCustom gysypmlMapperCustom;
	@Autowired
	private GysypmlMapper gysypmlMapper;
	@Autowired
	private GysypmlControlMapper gysypmlControlMapper;

	@Autowired
	private YpxxMapper ypxxMapper;
	@Autowired
	private SystemConfigService systemConfigService;
	
	@Override
	public List<GysypmlCustom> findGysypmlList(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception {
		// 非空判断
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();

		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		// 设置供货商id
		gysypmlCustom.setUsergysid(usergysId);

		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);

		return gysypmlMapperCustom.findGysypmlList(gysypmlQueryVo);
	}

	@Override
	public int findGysypmlCount(String usergysId, GysypmlQueryVo gysypmlQueryVo)
			throws Exception {
		// 非空判断
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();

		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		// 设置供货商id
		gysypmlCustom.setUsergysid(usergysId);

		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		return gysypmlMapperCustom.findGysypmlCount(gysypmlQueryVo);
	}

	@Override
	public List<GysypmlCustom> findAddGysypmlList(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception {
		// 非空判断
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();

		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		// 设置供货商id
		gysypmlCustom.setUsergysid(usergysId);

		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		return gysypmlMapperCustom.findAddGysypmlList(gysypmlQueryVo);
	}

	@Override
	public int findAddGysypmlCount(String usergysId,
			GysypmlQueryVo gysypmlQueryVo) throws Exception {
		// 非空判断
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();

		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}

		// 设置供货商id
		gysypmlCustom.setUsergysid(usergysId);

		gysypmlQueryVo.setGysypmlCustom(gysypmlCustom);
		return gysypmlMapperCustom.findAddGysypmlCount(gysypmlQueryVo);
	}

	@Override
	public void insertGysypml(String usergysid, String ypxxid) throws Exception {
		
		// 只允许添加供货商药品目录中没有的药品
		// 校验方法：先根据供货商id和药品id查询供货商药品目录记录，如果查询到说明已存在
		Gysypml gysypml=findGysypmlByUsergysidAndYpxxid(usergysid, ypxxid);
		
		if (gysypml!=null) {
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
		// 向供货商药品目录表gysypml插入一条记录
		Gysypml gysypml_insert=new Gysypml();
		gysypml_insert.setId(UUIDBuild.getUUID());
		gysypml_insert.setUsergysid(usergysid);
		gysypml_insert.setYpxxid(ypxxid);
		gysypmlMapper.insert(gysypml_insert);

		// 同时向供货商药品目录控制表gysypml_control插入一条记录
		// 如果控制表已存在供货商药品不用插入
		// 根据供货商id和药品id查询
		GysypmlControl gysypmlControl=findGysypmlControlByUsergysidAndYpxxid(usergysid, ypxxid);
		if (gysypmlControl==null) {
			
			GysypmlControl gysypmlControl_insert=new GysypmlControl();
			gysypmlControl_insert.setId(UUIDBuild.getUUID());
			gysypmlControl_insert.setUsergysid(usergysid);
			gysypmlControl_insert.setYpxxid(ypxxid);
			//这里通过系统设置将供货商药品信息默认为正常
			gysypmlControl_insert.setControl(systemConfigService.findBasicinfoById("00101").getValue());
			
			gysypmlControlMapper.insert(gysypmlControl_insert);
		}
		

	}

	@Override
	public Gysypml findGysypmlByUsergysidAndYpxxid(String usergysid,
			String ypxxid) throws Exception {
		//gysypmlMapper.selectByExample();
		GysypmlExample gysypmlExample=new GysypmlExample();
		GysypmlExample.Criteria criteria=gysypmlExample.createCriteria();
		criteria.andUsergysidEqualTo(usergysid);
		criteria.andYpxxidEqualTo(ypxxid);
		List<Gysypml> gysypmls = gysypmlMapper.selectByExample(gysypmlExample);
		if (null!=gysypmls&&gysypmls.size()==1) {
			return gysypmls.get(0);
		}
		return null;
	}

	@Override
	public GysypmlControl findGysypmlControlByUsergysidAndYpxxid(
			String usergysid, String ypxxid) throws Exception {
		GysypmlControlExample gysypmlControlExample=new GysypmlControlExample();
		GysypmlControlExample.Criteria criteria =gysypmlControlExample.createCriteria();
		criteria.andYpxxidEqualTo(ypxxid);
		criteria.andUsergysidEqualTo(usergysid);
		
		List<GysypmlControl> list=gysypmlControlMapper.selectByExample(gysypmlControlExample);
		if (null!=list&&list.size()==1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteGysypmlByUsergysidAndYpxxid(String usergysid,
			String ypxxid) throws Exception {
		//判断参数是否为空
		if(ypxxid == null || usergysid == null){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		Gysypml gysypml=findGysypmlByUsergysidAndYpxxid(usergysid, ypxxid);
		
		if (gysypml==null) {
			// 说明药品不存在
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 402, null));
		}
		GysypmlExample gysypmlExample=new GysypmlExample();
		GysypmlExample.Criteria criteria=gysypmlExample.createCriteria();
		criteria.andYpxxidEqualTo(ypxxid);
		criteria.andUsergysidEqualTo(usergysid);
		gysypmlMapper.deleteByExample(gysypmlExample);
		
	}

	@Override
	public List<GysypmlCustom> findGysypmlControlList(
			GysypmlQueryVo gysypmlQueryVo) throws Exception {
		// 非空判断
		gysypmlQueryVo = gysypmlQueryVo != null ? gysypmlQueryVo
				: new GysypmlQueryVo();

		GysypmlCustom gysypmlCustom = gysypmlQueryVo.getGysypmlCustom();
		if (gysypmlCustom == null) {
			gysypmlCustom = new GysypmlCustom();
		}
		
		return gysypmlMapperCustom.findGysypmlControlList(gysypmlQueryVo);
	}

	@Override
	public int findGysypmlControlCount(GysypmlQueryVo gysypmlQueryVo)
			throws Exception {
		
		return gysypmlMapperCustom.findGysypmlControlCount(gysypmlQueryVo);
	}

	@Override
	public void updateGysypmlControl(String ypxxid, String usergysid,String control, String advice)throws Exception {
		if(ypxxid == null || usergysid == null || control == null || control.equals("")){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
		GysypmlControl gysypmlControl=findGysypmlControlByUsergysidAndYpxxid(usergysid, ypxxid);
		if (gysypmlControl==null) {
			
		}
		gysypmlControl.setAdvice(advice);
		gysypmlControl.setControl(control);
		
		gysypmlControlMapper.updateByPrimaryKey(gysypmlControl);
		
	}

}
