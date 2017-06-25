package yycg.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yycg.base.pojo.po.Useryy;
import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.business.dao.mapper.YythdMapper;
import yycg.business.dao.mapper.YythdMapperCustom;
import yycg.business.dao.mapper.YythdmxMapper;
import yycg.business.pojo.po.Yycgd;
import yycg.business.pojo.po.Yycgdmx;
import yycg.business.pojo.po.Yythd;
import yycg.business.pojo.po.YythdExample;
import yycg.business.pojo.po.Yythdmx;
import yycg.business.pojo.vo.YycgdCustom;
import yycg.business.pojo.vo.YycgdQueryVo;
import yycg.business.service.CgdService;
import yycg.business.service.ThdService;
import yycg.util.MyUtil;
import yycg.util.UUIDBuild;

@Service
public class ThdServiceImpl implements ThdService{
	
	
	@Autowired
	private YythdMapper yythdMapper;
	@Autowired
	private YythdmxMapper yythdmxMapper;
	@Autowired
	private YythdMapperCustom yythdMapperCustom;
	@Autowired
	private CgdService cgdService;

	@Override
	public String insertYythd(String year, Yythd yythd) throws Exception {
		yythd.setBusinessyear(year);
		String id=yythdMapperCustom.generatorThdbm(year);
		yythd.setId(id);//id和编码一致
		yythd.setBm(id);
		
		yythd.setZt("1");
		yythd.setCjtime(new Date());
		yythdMapper.insert(yythd);
		return id;
	}

	@Override
	public void updateYythd(Yythd yythd) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Yythd findYythdById(String yythdid) throws Exception {
		if(yythdid == null){
  			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 909, null));
  		}
  	   //获取年份
  		String year = yythdid.substring(0, 4);
  		YythdExample yythdExample = new YythdExample();
  		yythdExample.setBusinessyear(year);
  		YythdExample.Criteria criteria = yythdExample.createCriteria();
		criteria.andIdEqualTo(yythdid);
		List<Yythd> list = yythdMapper.selectByExample(yythdExample);
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<YycgdCustom> findYythdList(YycgdQueryVo yycgdQueryVo)
			throws Exception {
		//非空判断
		yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();
		/*yycgdQueryVo.setBusinessyear(year);

		//设置医院id
		Useryy useryy=yycgdQueryVo.getUseryy();
		if (useryy==null) {
			useryy=new Useryy();
		}
		if (yycgdQueryVo.getUseryyCustom()==null) {
			//useryy.setId(useryyid);
			yycgdQueryVo.setUseryy(useryy);
		}*/

		return yythdMapperCustom.findYythdList(yycgdQueryVo);
	}

	@Override
	public int findYythdCount(YycgdQueryVo yycgdQueryVo) throws Exception {
		//非空判断
		yycgdQueryVo=yycgdQueryVo!=null?yycgdQueryVo:new YycgdQueryVo();
		return yythdMapperCustom.findYythdCount(yycgdQueryVo);
	}

	//退货单明细查询
  	@Override
    public int findYythdmxCount(String year,YycgdQueryVo yycgdQueryVo) throws Exception{
    	
    	yycgdQueryVo.setBusinessyear(year);
    	return yythdMapperCustom.findYythdmxCount(yycgdQueryVo);
    }
    @Override
    public List<YycgdCustom> findYythdmxList(String year,YycgdQueryVo yycgdQueryVo) throws Exception{
    	
    	yycgdQueryVo.setBusinessyear(year);
    	return yythdMapperCustom.findYythdmxList(yycgdQueryVo);
    }

	@Override
	public void insertYythdmx(String yythdid, Yythdmx yythdmx) throws Exception {
		if(yythdmx == null || yythdmx.getYycgdid() == null
				|| yythdmx.getYpxxid() == null || yythdid == null){
			ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 901, null));
		}
  		
  		

			Yycgd yycgd = cgdService.findYycgdById(yythdmx.getYycgdid());
			Yythd yythd = findYythdById(yythdmx.getYythdid());
			Yycgdmx yycgdmx = cgdService.findYycgdmxByYycgdidandYpxxid(yythdmx.getYycgdid(), yythdmx.getYpxxid());
			
			//找不到采购单 
			if(yycgd == null){
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,501, null));
			}
			//找不到采购药品明细
			if(yycgdmx == null){
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,509, null));
			}
			//退货单已提交不允许添加药品
			if(!yythd.getZt().equals("1")){
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,610, null));
			}
			if (cgdService.findYycgdmxByYycgdidandYpxxid(yythdmx.getYycgdid(), yythdmx.getYpxxid()) != null) {

				//校验结算状态，已结算不允许退货
				/*if(yycgdmx.getJszt()!=null && yycgdmx.getJszt().equals("2")){
					ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,611, null));
				}*/
				//退货量不能为空
				if(yythdmx.getThl() == null){
					ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,613, null));
				}
				
				int thl = yythdmx.getThl();
				//从交易明细表中获取交易价
				float jyjg = yycgdmx.getJyjg();
				//从交易明细表中获取采购量
				float cgl = yycgdmx.getCgl();
				
				//退货量不能大于采购量
				if(thl>cgl){
					ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 614, null));
				}
				//计算总的退货金额=交易价*退货量
				float thje = jyjg*thl;
				//生成主键
				yythdmx.setId(UUIDBuild.getUUID());
				yythdmx.setThl(thl);
				yythdmx.setThje(thje);
				yythdmx.setThzt("1");//未确认退货
				String year = yythdid.substring(0, 4);
		  		yythdmx.setBusinessyear(year);
				yythdmxMapper.insert(yythdmx);
			}else{
				//相同的药品不允许在退货单中重复添加
				ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE,612, null));
			}
		
	}

	@Override
	public void saveYythdmxThl(String yythdid, Yythdmx yythdmx)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteYythd(String yythdid, String useryyid) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteYythdmx(String yythdid, Yythdmx yythdmx_index)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
