package yycg.business.dao.mapper;

import java.util.List;
import java.util.Map;

import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;



public interface YythdMapperCustom {

    /**
     * 生成退货单号
     */
    public String generatorThdbm(String businessyear) throws Exception;
    
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
    public List<YycgdCustom> findYythdmxList(YycgdQueryVo yycgdQueryVo) throws Exception;
    
    /**
     * 退货单明细数量
     * @param yycgdQueryVo 
     * @return
     * @throws Exception
     */
    public int findYythdmxCount(YycgdQueryVo yycgdQueryVo) throws Exception;
    
  
   
}