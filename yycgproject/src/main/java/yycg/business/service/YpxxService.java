package yycg.business.service;

import java.util.List;

import yycg.base.pojo.vo.SysuserCustom;
import yycg.business.pojo.po.Ypxx;
import yycg.business.pojo.vo.YpxxCustom;
import yycg.business.pojo.vo.YpxxQueryVo;

public interface YpxxService {

	public Ypxx getYpxxObject(String id) throws Exception;
	/*
	 * 添加药品信息
	 */
	public void insertYpxx(Ypxx ypxx) throws Exception;
	
	public void updateYpxx(Ypxx ypxx)throws Exception;
	//删除药品
	public void deleteYpxx(String id) throws Exception;
	// 药品目录 查询
	public List<YpxxCustom> findYpxxList(YpxxQueryVo ypxxQueryVo)
				throws Exception;

	public int findYpxxCount(YpxxQueryVo ypxxQueryVo)throws Exception;
}
