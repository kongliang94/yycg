package yycg.business.dao.mapper;

import java.util.List;

import yycg.business.pojo.vo.YyypmlCustom;
import yycg.business.pojo.vo.YyypmlQueryVo;

/**
 * 医院药品目录dao接口
 * @author KL
 *
 */
public interface YyypmlMapperCustom {
	//药品目录查询数据列表
	public List<YyypmlCustom> findYyypmlList(YyypmlQueryVo gysypmlQueryVo) throws Exception;
	
	//药品目录查询数量
	public int findYyypmlCount(YyypmlQueryVo gysypmlQueryVo) throws Exception;
	
	//药品目录添加查询数据列表
	public List<YyypmlCustom> findYyypmlAddList(YyypmlQueryVo gysypmlQueryVo) throws Exception;
	
	//药品目录添加查询数量
	public int findYyypmlAddCount(YyypmlQueryVo gysypmlQueryVo) throws Exception;
		
}
