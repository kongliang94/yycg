package yycg.base.dao.mapper;

import java.util.List;

import yycg.base.pojo.vo.Menu;
import yycg.base.pojo.vo.Operation;
import yycg.base.pojo.vo.SysuserCustom;
import yycg.base.pojo.vo.SysuserQueryVo;

public interface SysuserMapperCustom {
	
	//查询用户列表
	public List<SysuserCustom> findSysuserList(SysuserQueryVo sysuserQueryVo) throws Exception;
	
	//查询总数
	public int findSysuserCount(SysuserQueryVo sysuserQueryVo) throws Exception;
	//根据角色Id查询菜单
	public List<Menu> findMenuByroleid(String roleId) throws Exception;
	
	//根据角色Id查询操作权限
	public List<Operation> findOperationByroleid(String roleId) throws Exception;
}
