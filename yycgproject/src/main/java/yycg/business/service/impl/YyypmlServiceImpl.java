package yycg.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.business.dao.mapper.YyypmlMapper;
import yycg.business.dao.mapper.YyypmlMapperCustom;
import yycg.business.pojo.po.Yyypml;
import yycg.business.pojo.po.YyypmlExample;
import yycg.business.pojo.vo.YyypmlCustom;
import yycg.business.pojo.vo.YyypmlQueryVo;
import yycg.business.service.YyypmlService;
import yycg.util.UUIDBuild;

@Service
public class YyypmlServiceImpl implements YyypmlService {

	

	@Autowired
	private YyypmlMapper yyypmlMapper;
	@Autowired
	private YyypmlMapperCustom yyypmlMapperCustom;
	
	//药品目录查询数据列表
	public List<YyypmlCustom> findYyypmlList(YyypmlQueryVo yyypmlQueryVo) throws Exception{
		
		return yyypmlMapperCustom.findYyypmlList(yyypmlQueryVo);
	}
	
	//药品目录查询数量
	public int findYyypmlCount(YyypmlQueryVo yyypmlQueryVo) throws Exception{
		return yyypmlMapperCustom.findYyypmlCount(yyypmlQueryVo);
	}

	//药品目录查询数据列表
	public List<YyypmlCustom> findYyypmlAddList(YyypmlQueryVo yyypmlQueryVo) throws Exception{
		return yyypmlMapperCustom.findYyypmlAddList(yyypmlQueryVo);
	}
	
	//药品目录查询数量
	public int findYyypmlAddCount(YyypmlQueryVo yyypmlQueryVo) throws Exception{
		return yyypmlMapperCustom.findYyypmlAddCount(yyypmlQueryVo);
	}
	
	/**
	 * 根据药品信息id、医院id、供货商单位id获取供货目录信息
	 * @param ypxxid
	 * @param useryyid
	 * @param usergysid
	 * @return
	 */
	public Yyypml getYyypml(String ypxxid,String useryyid,String usergysid) throws Exception{
		YyypmlExample yyypmlExample = new YyypmlExample();
		YyypmlExample.Criteria  criteria = yyypmlExample.createCriteria();
		criteria.andYpxxidEqualTo(ypxxid);
		criteria.andUseryyidEqualTo(useryyid);
		criteria.andUsergysidEqualTo(usergysid);
		
		List<Yyypml> list = yyypmlMapper.selectByExample(yyypmlExample);
		if(list.size()==1){
			return list.get(0);
		}
		return null;
	}
	
	
	
	/**
	 * 供货药品目录添加药品
	 * @param ypxxid 药品信息id
	 * @param useryyid
	 * @param usergysid 供货商单位id
	 * @return ResultInfo
	 * @throws Exception
	 */
	public void insertYyypml(String useryyid,String ypxxid,String usergysid) throws Exception{
		if(getYyypml(ypxxid,useryyid, usergysid)==null){
			//插入药品目录表
			Yyypml yyypml = new Yyypml();
			yyypml.setId(UUIDBuild.getUUID());
			yyypml.setYpxxid(ypxxid);
			yyypml.setUseryyid(useryyid);
			yyypml.setUsergysid(usergysid);
			yyypmlMapper.insert(yyypml);
		}else{
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 401, null));
		}
	}
	/**
	 * 供货药品目录删除药品
	 * @param ypxxid
	 * @param useryyid
	 * @param usergysid
	 * @return ResultInfo
	 * @throws Exception
	 */
	public void deleteYyypml(String useryyid,String ypxxid,String usergysid) throws Exception{
		if(getYyypml(ypxxid,useryyid, usergysid)!=null){
			YyypmlExample yyypmlExample = new YyypmlExample();
			YyypmlExample.Criteria  criteria = yyypmlExample.createCriteria();
			criteria.andYpxxidEqualTo(ypxxid);
			criteria.andUseryyidEqualTo(useryyid);
			criteria.andUsergysidEqualTo(usergysid);
			yyypmlMapper.deleteByExample(yyypmlExample);
		}else{
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 402, null));
		}
	}

}
