package yycg.base.service;

import java.util.List;

import yycg.base.pojo.po.Sysuser;
import yycg.base.pojo.po.Usergys;
import yycg.base.pojo.po.Userjd;
import yycg.base.pojo.po.Useryy;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.Menu;
import yycg.base.pojo.vo.Operation;
import yycg.base.pojo.vo.SysuserCustom;
import yycg.base.pojo.vo.SysuserQueryVo;
import yycg.base.pojo.vo.UseryyCustom;
import yycg.base.pojo.vo.UseryyQueryVo;

public interface UserService {
	
	// 根据条件查询用户信息
	public List<SysuserCustom> findSysuserList(SysuserQueryVo sysuserQueryVo)
			throws Exception;

	// 根据条件查询列表的总数
	public int findSysuserCount(SysuserQueryVo sysuserQueryVo) throws Exception;

	// 根据用户账号查询用户信息
	public Sysuser findSysuserByUserid(String userId) throws Exception;

	// 根据单位名称 查询单位信息
	public Userjd findUserjdByMc(String mc) throws Exception;

	public Useryy findUseryyByMc(String mc) throws Exception;

	public Usergys findUsergysByMc(String mc) throws Exception;

	//添加用户
	public void insertSysuser(SysuserCustom sysuserCustom) throws Exception;
	//删除用户
	public void deleteSysuser(String id) throws Exception;

	//修改用户
	/**
	 * 
	 * @param id 修改用户的id
	 * @param sysuserCustom 修改用户信息，表单提交的数据
	 * @throws Exception
	 */
	public void updateSysuser(String id,SysuserCustom sysuserCustom)throws Exception;
	//根据id获取用户信息
	public SysuserCustom findSysuserById(String id) throws Exception;
	
/********************************医院信息*******************************/
	
	/**
	 * 查询医院信息数量
	 * @param useryyQueryVo
	 * @return
	 * @throws Exception
	 */
	public int findUseryyCount(UseryyQueryVo useryyQueryVo)throws Exception;
	/**
	 * 查询医院信息列表
	 * @param useryyQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<UseryyCustom> findUseryylist(UseryyQueryVo useryyQueryVo)throws Exception;
	
	/**
	 * 根据医院id获取信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Useryy getUseryyById(String id) throws Exception;
	/**
	 * 根据医院名称获取医院信息
	 * @param mc
	 * @return
	 * @throws Exception
	 */
	public Useryy getUseryyByMc(String mc) throws Exception;
	
	/**
	 * 添加医院信息
	 * @param useryyQueryVo
	 * @throws Exception
	 */
	public void insertUseryy(Useryy useryy) throws Exception;
	/**
	 * 修改医院信息
	 * @param useryyQueryVo
	 * @throws Exception
	 */
	public void updateUseryy(Useryy useryy) throws Exception;
	
	/**
	 * 删除医院信息
	 * @param useryyid
	 * @throws Exception
	 */
	public void deleteUseryy(String id) throws Exception;

	/**
	 * 用户认证
	 * @param userid 账号
	 * @param pwd 密码
	 * @return
	 * @throws Exception 
	 */
	public ActiveUser checkUserInfo(String userid, String pwd) throws Exception;
	
	public List<Menu> findMenuByroleid(String roleId) throws Exception;

	//根据角色Id查询操作权限
	public List<Operation> findOperationByroleid(String roleId) throws Exception;
}
