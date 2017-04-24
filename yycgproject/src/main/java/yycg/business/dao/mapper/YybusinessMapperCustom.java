package yycg.business.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yycg.business.pojo.po.Yybusiness;
import yycg.business.pojo.po.YybusinessExample;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;

public interface YybusinessMapperCustom {
	//交易明细查询
	public List<YycgdmxCustom> findYybusinessList(YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYybusinessCount(YycgdQueryVo yycgdQueryVo) throws Exception;
	//交易明细查询总计
	public List<YycgdmxCustom> findYybusinessSum(YycgdQueryVo yycgdQueryVo) throws Exception;

	//按药品分类 统计
	public List<YycgdmxCustom> findYybusinessGroupbyYpxxList(YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYybusinessGroupbyYpxxCount(YycgdQueryVo yycgdQueryVo) throws Exception;
	//按医院统计
	public List<YycgdmxCustom> findYybusinessGroupbyUseryyList(YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYybusinessGroupbyUseryyCount(YycgdQueryVo yycgdQueryVo) throws Exception;

	//按供货商统计
	public List<YycgdmxCustom> findYybusinessGroupbyUsergysList(YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYybusinessGroupbyUsergysCount(YycgdQueryVo yycgdQueryVo) throws Exception;
	//按区域统计
	public List<YycgdmxCustom> findYybusinessGroupbyAreaList(YycgdQueryVo yycgdQueryVo) throws Exception;
	
}