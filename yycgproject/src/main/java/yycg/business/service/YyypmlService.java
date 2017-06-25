package yycg.business.service;

import java.util.List;

import yycg.business.pojo.po.Yyypml;
import yycg.business.pojo.vo.YyypmlCustom;
import yycg.business.pojo.vo.YyypmlQueryVo;

/**
 * 医院药品目录业务服务接口
 * @author KL
 *
 */
public interface YyypmlService {
	//医院药品目录查询数据列表
	public List<YyypmlCustom> findYyypmlList(YyypmlQueryVo yyypmlQueryVo) throws Exception;
	
	//医院药品目录查询数量
	public int findYyypmlCount(YyypmlQueryVo yyypmlQueryVo) throws Exception;
	
	//医院药品目录添加查询数据列表
	public List<YyypmlCustom> findYyypmlAddList(YyypmlQueryVo yyypmlQueryVo) throws Exception;
	
	//医院药品目录添加查询数量
	public int findYyypmlAddCount(YyypmlQueryVo yyypmlQueryVo) throws Exception;	
	/**
	 * 根据药品信息id、医院id、供货商单位id获取医院药品目录信息
	 * @param ypxxid
	 * @param useryyid
	 * @param usergysid
	 * @return
	 */
	public Yyypml getYyypml(String useryyid,String ypxxid,String usergysid) throws Exception;
	
	/**
	 * 医院药品目录添加药品
	 * @param ypxxid 药品信息id
	 * @param useryyid
	 * @param usergysid 供货商单位id
	 * @throws Exception
	 */
	public void insertYyypml(String useryyid,String ypxxid,String usergysid) throws Exception;
	/**
	 * 医院药品目录删除药品
	 * @param ypxxid
	 * @param useryyid
	 * @param usergysid
	 * @throws Exception
	 */
	public void deleteYyypml(String ypxxid,String useryyid,String usergysid) throws Exception;
}
