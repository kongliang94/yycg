package yycg.business.service;

import java.sql.Savepoint;
import java.util.List;

import yycg.business.pojo.po.Yycgd;
import yycg.business.pojo.po.Yycgdmx;
import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.pojo.vo.YycgdmxCustom;
import yycg.business.pojo.vo.YycgdrkCustom;

public interface CgdService {
	
	// 创建采购单基本信息，返回采购单id
	public String insertYycgd(String useryyid, String year,
				YycgdCustom yycgdCustom) throws Exception;

	//根据采购单Id获取采购单信息
	public YycgdCustom findYycgdById(String id)throws Exception;

	//对采购单基本信息进行修改操作
	public void updateYycgd(String id, YycgdCustom yycgdCustom)throws Exception;
	public void deleteYycgd(String id,String useryyid)throws Exception;
	
	//提交采购单
	public void saveYycgdSubmit(String yycgdid, String useryyid)
			throws Exception;
	//获取采购单添加的药品信息
	public List<YycgdmxCustom> findAddYycgdmxList(String useryyid,
			String yycgdid, YycgdQueryVo yycgdQueryVo)throws Exception;
	public int findAddYycgdmxCount(String useryyid,
			String yycgdid, YycgdQueryVo yycgdQueryVo)throws Exception;
	//采购单添加的药品添加
	public void insertYycgdmx(String yycgdid,String ypxxid,String usergysid)throws Exception;
	
	//通过采购单Id和药品信息Id获取采购单明细	
	public Yycgdmx findYycgdmxByYycgdidandYpxxid(String yycgdid,String ypxxid) throws Exception;

	//通过采购单id获取采购单明细
	public int findYycgdmxCountByYycgdid(String yycgdid, YycgdQueryVo yycgdQueryVo)
			throws Exception;
	public List<YycgdmxCustom> findYycgdmxListByYycgdid(String yycgdid,
			YycgdQueryVo yycgdQueryVo) throws Exception;
	
	public List<YycgdmxCustom> findYycgdmxListSum(String yycgdid,YycgdQueryVo yycgdQueryVo) throws Exception;
	//根据采购单id和药品信息id更新采购单明细
	public void updateYycgdmx(String yycgdid, String ypxxid, Integer cgl)throws Exception;
	public void deleteYycgdmx(String yycgdid, YycgdmxCustom yycgdmxCustom)throws Exception;
	
	//采购单维护，要获取动态表信息，需要加一个year参数
	/**
	 * 
	 * @param useryyid 医院id
	 * @param year     年份，动态分表使用
	 * @param yycgdQueryVo 封装条件
	 * @return
	 * @throws Exception
	 */
	public List<YycgdCustom> findYycgdList(String useryyid,String year,YycgdQueryVo yycgdQueryVo)throws Exception;
	public int findYycgdCount(String useryyid,String year,YycgdQueryVo yycgdQueryVo)throws Exception;
	
	/**
	 * 审核采购药品
	 * @param userjdid
	 * @param year
	 * @param yycgdQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<YycgdCustom> findCheckYycgdList(String userjdid,String year,YycgdQueryVo yycgdQueryVo)throws Exception;
	public int findCheckYycgdCount(String userjdid,String year,YycgdQueryVo yycgdQueryVo)throws Exception;
	/**
	 * 审核采购单提交
	 * @param yycgdid
	 * @param yycgdCustom
	 * @throws Exception
	 */
	public void saveYycgdCheckStatus(String yycgdid, YycgdCustom yycgdCustom)
			throws Exception;
	/**
	 * 供货商受理采购单
	 * @param usergysid
	 * @param year
	 * @param yycgdQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<YycgdmxCustom> findDisposeYycgdList(String usergysid,
			String year, YycgdQueryVo yycgdQueryVo) throws Exception;
	
	public int findDisposeYycgdCount(String usergysid,
			String year, YycgdQueryVo yycgdQueryVo) throws Exception;
	/**
	 * 提交发货
	 * @param yycgdid
	 * @param ypxxid
	 * @throws Exception
	 */
	public void saveSendStatus(String yycgdid, String ypxxid) throws Exception;
	/**
	 * 采购单入库
	 * @param useryyid
	 * @param year
	 * @param yycgdQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<YycgdmxCustom> findYycgdReceiveList(String useryyid,
			String year, YycgdQueryVo yycgdQueryVo)throws Exception;
	
	public int findYycgdReceivCount(String useryyid,
			String year, YycgdQueryVo yycgdQueryVo)throws Exception;
	
	public void saveYycgdrk(String yycgdid,String ypxxid,YycgdrkCustom yycgdrkCustom)throws Exception;
}
