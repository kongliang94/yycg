package yycg.business.service;

import java.util.List;

import yycg.business.pojo.po.Yythd;
import yycg.business.pojo.po.Yythdmx;
import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;

public interface ThdService {

	 //添加退货单
  	public String insertYythd(String year,Yythd yythd) throws Exception;
  	public void updateYythd(Yythd yythd) throws Exception;
	public Yythd findYythdById(String yythdid) throws Exception;
	
	 /**
     * 退货单列表
     * @param yycgdQueryVo 
     * @return
     * @throws Exception
     */
    public List<YycgdCustom> findYythdList(YycgdQueryVo yycgdQueryVo) throws Exception;
    
    /**
     * 退货单数量
     * @param yycgdQueryVo 
     * @return
     * @throws Exception
     */
    public int findYythdCount(YycgdQueryVo yycgdQueryVo) throws Exception;
    
    /**
     * 退货单明细列表
     * @param yycgdQueryVo 
     * @return
     * @throws Exception
     */
    public List<YycgdCustom> findYythdmxList(String year,YycgdQueryVo yycgdQueryVo) throws Exception;
    
    /**
     * 退货单明细数量
     * @param yycgdQueryVo 
     * @return
     * @throws Exception
     */
    public int findYythdmxCount(String year,YycgdQueryVo yycgdQueryVo) throws Exception;
    
	//添加退货单明细
  	public void insertYythdmx(String yythdid,Yythdmx yythdmx)	throws Exception ;
  	public void saveYythdmxThl(String yythdid,Yythdmx yythdmx) throws Exception;
  	//删除退货单
	public void deleteYythd(String yythdid, String useryyid) throws Exception;
	//退货药品删除
	public void deleteYythdmx(String yythdid, Yythdmx yythdmx_index) throws Exception;
}
