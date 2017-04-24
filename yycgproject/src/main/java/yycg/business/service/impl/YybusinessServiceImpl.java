package yycg.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yycg.base.dao.mapper.UsergysMapper;
import yycg.base.dao.mapper.UserjdMapper;
import yycg.base.dao.mapper.UseryyMapper;
import yycg.base.pojo.po.Usergys;
import yycg.base.pojo.po.Userjd;
import yycg.base.pojo.po.Useryy;
import yycg.business.dao.mapper.YybusinessMapperCustom;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;
import yycg.business.service.YybusinessService;


@Service
public class YybusinessServiceImpl implements YybusinessService {

	@Autowired
	YybusinessMapperCustom yybusinessMapperCustom;
	@Autowired
	UserjdMapper userjdMapper;
	@Autowired
	UseryyMapper useryyMapper;
	@Autowired
	UsergysMapper usergysMapper;
	
	
	//交易明细对条件的判断抽取出来
	public YycgdQueryVo handleCondition(String year, String groupid,
			String sysid, YycgdQueryVo yycgdQueryVo)throws Exception{
		yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();
		if (groupid.equals("1")||groupid.equals("2")) {
			Userjd userjd=userjdMapper.selectByPrimaryKey(sysid);
			Useryy useryy=yycgdQueryVo.getUseryy();
			useryy=useryy!=null?useryy:new Useryy();
			useryy.setDq(userjd.getDq());
			yycgdQueryVo.setUseryy(useryy);
		}else if (groupid.equals("3")) {
			//医院，卫生室
			Useryy useryy=yycgdQueryVo.getUseryy();
			useryy=useryy!=null?useryy:new Useryy();
			useryy.setId(sysid);
			yycgdQueryVo.setUseryy(useryy);
			
		}else if (groupid.equals("4")) {
			
		
			//供货商
			Usergys usergys = yycgdQueryVo.getUsergys();
			usergys = usergys != null ? usergys : new Usergys();
			// 设置供货商id，只查询本供货商相关交易明细
			usergys.setId(sysid);
			yycgdQueryVo.setUsergys(usergys);
		}
		
		yycgdQueryVo.setBusinessyear(year);
		return yycgdQueryVo;
	}
	@Override
	public List<YycgdmxCustom> findYybusinessList(String year, String groupid,
			String sysid, YycgdQueryVo yycgdQueryVo) throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessList(yycgdQueryVo);
	}

	@Override
	public int findYybusinessCount(String year, String groupid, String sysid,
			YycgdQueryVo yycgdQueryVo) throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessCount(yycgdQueryVo);
	}

	@Override
	public List<YycgdmxCustom> findYybusinessSum(String year, String groupid,
			String sysid, YycgdQueryVo yycgdQueryVo) throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessSum(yycgdQueryVo);
	}
	@Override
	public List<YycgdmxCustom> findYybusinessGroupbyYpxxList(String year,
			String groupid, String sysid, YycgdQueryVo yycgdQueryVo)
			throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessGroupbyYpxxList(yycgdQueryVo);
	}
	@Override
	public int findYybusinessGroupbyYpxxCount(String year, String groupid,
			String sysid, YycgdQueryVo yycgdQueryVo) throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessGroupbyYpxxCount(yycgdQueryVo);
	}
	@Override
	public List<YycgdmxCustom> findYybusinessGroupbyUseryyList(String year,
			String groupid, String sysid, YycgdQueryVo yycgdQueryVo)
			throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessGroupbyUseryyList(yycgdQueryVo);
	}
	@Override
	public int findYybusinessGroupbyUseryyCount(String year, String groupid,
			String sysid, YycgdQueryVo yycgdQueryVo) throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessGroupbyUseryyCount(yycgdQueryVo);
	}
	@Override
	public List<YycgdmxCustom> findYybusinessGroupbyUsergysList(String year,
			String groupid, String sysid, YycgdQueryVo yycgdQueryVo)
			throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessGroupbyUsergysList(yycgdQueryVo);
	}
	@Override
	public int findYybusinessGroupbyUsergysCount(String year, String groupid,
			String sysid, YycgdQueryVo yycgdQueryVo) throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessGroupbyUsergysCount(yycgdQueryVo);
	}
	@Override
	public List<YycgdmxCustom> findYybusinessGroupbyAreaList(String year,
			String groupid, String sysid, YycgdQueryVo yycgdQueryVo)
			throws Exception {
		handleCondition(year, groupid, sysid, yycgdQueryVo);
		return yybusinessMapperCustom.findYybusinessGroupbyAreaList(yycgdQueryVo);
	}

}
