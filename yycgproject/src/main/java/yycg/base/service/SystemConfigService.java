package yycg.base.service;

import java.util.Date;
import java.util.List;

import yycg.base.pojo.po.Basicinfo;
import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.vo.Menu;
import yycg.util.ExcelExportSXXSSF;

/**
 * 系统级别service
 * @author KL
 *
 */
public interface SystemConfigService {
	/**
	 * 根据typecode获取数据字典的信息
	 */
	public List findDictinfoByType(String typecode) throws Exception;
	
	/**
	 * 根据数据字典中的typecode，和dictcode获取一条信息
	 */
	public Dictinfo  findDictinfoByDictcode(String typecode,String dictcode) throws Exception;
	
	/**
	 * 根据系统参数id获取系统参数表信息
	 */
	public Basicinfo findBasicinfoById(String id)throws Exception;
	
	/**
	 * 查询数据库服务器当前时间
	 * @returndatetimeStr 当前时间值
	 * @throws Exception
	 */

	public Date getdatabaseTime() throws Exception;
	
	/**
	 * 生成调用系统管理平台和系统配置平台的加密串
	 */
	public String build3desString()throws Exception;
	
	 /**
     * 根据角色查询相关的模块及操作
     * 
     */
	public List<Menu> findModuleAndOperateByRole(String role) throws Exception;
	
	/**
	 * 查询字典类别详细详细列表
	 * @param typecode
	 * @return
	 * @throws Exception
	 */
    public List<Dictinfo> findDicttypeinfolist(String typecode)  throws Exception;
   
    /**
     * 根据id查询字典类别信息
     * @param id
     * @return
     * @throws Exception
     */
    public Dictinfo getDictinfoById(String id) throws Exception;
    
    /**
     * 根据类型和代码获取字典
     * @param typecode
     * @param code
     * @return
     * @throws Exception
     */
    public Dictinfo findDictinfoByDictinfocode(String typecode, String code) throws Exception;
	
    /**
     * 根据类型和信息获取字典
     * @param typecode
     * @param code
     * @return
     * @throws Exception
     */
    public Dictinfo findDictinfoByDictinfoname(String typecode, String info) throws Exception;
    /**
   	 * 
   	 * 记录操作成功日志
   	 * 
   	 * @param username   操作人姓名
   	 * @param userid   操作人账号
   	 * @param userip  管理员IP地址
   	 * @param messages  操作信息描述
   	 * @throws Exception
   	 * 
   	 */
    public void saveSuccessLog(String userid, String username,String userip, String message)throws Exception ;

    /**
   	 * 
   	 * 记录操作失败日志
   	 * 
   	 * @param username   操作人姓名
   	 * @param userid   操作人账号
   	 * @param userip  管理员IP地址
   	 * @param messages  操作信息描述
   	 * @throws Exception
   	 * 
   	 */
    public void saveFailLog(String userid,String username, String userip, String message)throws Exception;
    
//    /**
//     * 获取系统配置表数据
//     * @param id
//     * @return
//     * @throws Exception
//     */
//    public Basicinfo findBasicinfoById(String id)throws Exception;
    /**
     * 导出excel通用方法
     * @param field
     * @param path
     * @param webpath
     * @param filePrefix
     * @param datas
     * @param flushRows
     * @return
     * @throws Exception
     */
    public ExcelExportSXXSSF excelExport(String field,String path,String webpath,String filePrefix,List datas,int flushRows) throws Exception;
}

