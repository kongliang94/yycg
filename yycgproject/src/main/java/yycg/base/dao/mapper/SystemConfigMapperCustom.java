package yycg.base.dao.mapper;

import java.util.Date;
import java.util.List;

import yycg.base.pojo.vo.Menu;



public interface SystemConfigMapperCustom {
	
	/**
	 * 查询数据库服务器当前时间
	 * 
	 * 
	 * @returndatetimeStr 当前时间值
	 * @throws Exception
	 */
	public Date getDataBaseTime()throws Exception;
	
    /**
     * 根据角色查询相关的模块及操作
     * 
     */
	public List<Menu> findModuleAndOperateByRole(String role) throws Exception;
	
	
}