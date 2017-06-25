package yycg.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yycg.base.pojo.po.Sysuser;
import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.business.dao.mapper.YpxxMapper;
import yycg.business.dao.mapper.YpxxMapperCustom;
import yycg.business.pojo.po.Ypxx;
import yycg.business.pojo.po.YpxxExample;
import yycg.business.pojo.vo.YpxxCustom;
import yycg.business.pojo.vo.YpxxQueryVo;
import yycg.business.service.YpxxService;
import yycg.util.UUIDBuild;

@Service
public class YpxxServiceImpl implements YpxxService{

	@Autowired
	private YpxxMapper ypxxMapper;
	@Autowired
	private YpxxMapperCustom ypxxMapperCustom;
	
	@Override
	public List<YpxxCustom> findYpxxList(YpxxQueryVo ypxxQueryVo)
			throws Exception {
		return ypxxMapperCustom.findYpxxList(ypxxQueryVo);
	}

	@Override
	public int findYpxxCount(YpxxQueryVo ypxxQueryVo) throws Exception {
		
		return ypxxMapperCustom.findYpxxCount(ypxxQueryVo);
	}

	//根据通用名、剂型、规格、转换系数、药品生产企业名称、商品名称获取药品信息 
	public Ypxx findYpxxByUnique1(String mc,String jx,String gg,String zhxs,String scqymc,String spmc)throws Exception{
			YpxxExample ypxxExample1=new  YpxxExample();
			YpxxExample.Criteria criteira1=ypxxExample1.createCriteria();
			criteira1.andMcEqualTo(mc);//通用名
			criteira1.andJxEqualTo(jx);//剂型
			criteira1.andGgEqualTo(gg);//规格
			criteira1.andZhxsEqualTo(zhxs);//转换系数
			criteira1.andScqymcEqualTo(scqymc);//生产企业名称
			criteira1.andSpmcEqualTo(spmc);//商品名称
			
			List<Ypxx> list = ypxxMapper.selectByExampleWithBLOBs(ypxxExample1);
			if(list !=null && list.size() == 1){
				return list.get(0);
			}else{
				return null;
			}
		}
	
	/**
	 * 添加药品信息时校验药品信息唯一性
	 */
	public void insertYpxxCheckKey(Ypxx ypxx) throws Exception{

		//判断品目id，生产企业名称、商品名称是否重复
		Ypxx ypxx_l = findYpxxByUnique1(ypxx.getMc(),ypxx.getJx(),ypxx.getGg(),ypxx.getZhxs(),ypxx.getScqymc(),ypxx.getSpmc());
		if(ypxx_l != null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,305,new Object[]{ypxx.getMc(),ypxx.getJx(),ypxx.getGg(),ypxx.getZhxs(),ypxx.getScqymc(),ypxx.getSpmc()}));
		}
		
	}
	@Override
	public void deleteYpxx(String id) throws Exception {
		try{
			ypxxMapper.deleteByPrimaryKey(id);
		}catch(Exception e){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,306, null));
		}
		
	}

	@Override
	public Ypxx getYpxxObject(String id) throws Exception {
		return ypxxMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateYpxx(Ypxx ypxx) throws Exception {
		if(ypxx == null || ypxx.getMc()==null || ypxx.getJx()==null || ypxx.getGg()==null || ypxx.getZhxs()==null){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE,901, null));
		}
		ypxxMapper.updateByPrimaryKey(ypxx);
	}

	@Override
	public void insertYpxx(Ypxx ypxx) throws Exception {
		if(ypxx == null || ypxx.getMc()==null || ypxx.getJx()==null || ypxx.getGg()==null || ypxx.getZhxs()==null){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE,901, null));
		}
		
		insertYpxxCheckKey(ypxx);

		
		ypxx.setJyzt("1");
		ypxxMapper.insert(ypxx);
	}

}
