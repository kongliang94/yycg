package yycg.business.service;

import java.util.List;

import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;

public interface YybusinessService {

	/**
	 * 按交易明细统计
	 * @param year 年份
	 * @param groupid 单位id
	 * @param sysid  用户id
	 * @param yycgdQueryVo 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<YycgdmxCustom> findYybusinessList(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYybusinessCount(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
	//交易明细查询总计
	public List<YycgdmxCustom> findYybusinessSum(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
	
	/**
	 * 按药品名称统计
	 * @param year
	 * @param groupid
	 * @param sysid
	 * @param yycgdQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<YycgdmxCustom> findYybusinessGroupbyYpxxList(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYybusinessGroupbyYpxxCount(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
	/**
	 * 按医院统计
	 * @param year
	 * @param groupid
	 * @param sysid
	 * @param yycgdQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<YycgdmxCustom> findYybusinessGroupbyUseryyList(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYybusinessGroupbyUseryyCount(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;

	/**
	 * 按供货商统计
	 * @param year
	 * @param groupid
	 * @param sysid
	 * @param yycgdQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<YycgdmxCustom> findYybusinessGroupbyUsergysList(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
	public int findYybusinessGroupbyUsergysCount(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
	/**
	 * 按区域统计
	 * @param year
	 * @param groupid
	 * @param sysid
	 * @param yycgdQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<YycgdmxCustom> findYybusinessGroupbyAreaList(String year,String groupid,String sysid,YycgdQueryVo yycgdQueryVo) throws Exception;
}
