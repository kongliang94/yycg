package yycg.business.dao.mapper;

import java.util.List;

import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;

public interface YycgdMapperCustom {

	//获取采购单编码（年份+Oracle序列码）
	public String getYycgdBm(String year)throws Exception;

	//根据采购单Id获取采购单信息
	public YycgdCustom findYycgdById(String id)throws Exception;

	//对采购单基本信息进行修改操作
	public void updateYycgd(String id, YycgdCustom yycgdCustom)throws Exception;
	//采购单明细查询
	public List<YycgdmxCustom> findYycgdmxList(YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYycgdmxCount(YycgdQueryVo yycgdQueryVo) throws Exception;
	public List<YycgdmxCustom> findYycgdmxListSum(YycgdQueryVo yycgdQueryVo) throws Exception;
	
	//采购单信息
	public List<YycgdmxCustom> findAddYycgdmxList(YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findAddYycgdmxCount(YycgdQueryVo yycgdQueryVo)throws Exception;
	
	//采购单维护
	public List<YycgdCustom> findYycgdList(YycgdQueryVo yycgdQueryVo)throws Exception;
	public int findYycgdCount(YycgdQueryVo yycgdQueryVo)throws Exception;

	public List<YycgdmxCustom> findYycgdmxrkList(YycgdQueryVo yycgdQueryVo)throws Exception;

	public int findYycgdmxrkCount(YycgdQueryVo yycgdQueryVo)throws Exception;
}
