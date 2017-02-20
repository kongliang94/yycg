package yycg.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yycg.base.dao.mapper.BasicinfoMapper;
import yycg.base.dao.mapper.DictinfoMapper;
import yycg.base.pojo.po.Basicinfo;
import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.po.DictinfoExample;
import yycg.base.pojo.vo.Menu;
import yycg.base.service.SystemConfigService;
import yycg.util.ExcelExportSXXSSF;

@Service
public class SystemConfigServiceImpl implements SystemConfigService{

	@Autowired
	private DictinfoMapper dictinfoMapper;
	@Autowired
	private BasicinfoMapper basicinfoMapper;
	
	
	//根据数据字典typecode获取字典明细信息
	@Override
	public List findDictinfoByType(String typecode) throws Exception {
		DictinfoExample dictinfoExample=new DictinfoExample();
		DictinfoExample.Criteria criteria=dictinfoExample.createCriteria();
		criteria.andTypecodeEqualTo(typecode);
		return dictinfoMapper.selectByExample(dictinfoExample);	
	}

	//根据typeocde和dictcode获取单个字典明细
	public Dictinfo  findDictinfoByDictcode(String typecode,String dictcode) throws Exception{
		DictinfoExample dictinfoExample = new DictinfoExample();
		DictinfoExample.Criteria criteria = dictinfoExample.createCriteria();
		criteria.andDictcodeEqualTo(dictcode);
		criteria.andTypecodeEqualTo(typecode);
		List<Dictinfo> list = dictinfoMapper.selectByExample(dictinfoExample);
		if(list!=null && list.size()==1){
			return list.get(0);
		}
		return null;

	}
	/**
	 * 根据id获取系统配置信息
	 */
	@Override
	public Basicinfo findBasicinfoById(String id) throws Exception {
		return basicinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public Date getdatabaseTime() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String build3desString() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findModuleAndOperateByRole(String role) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dictinfo> findDicttypeinfolist(String typecode)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dictinfo getDictinfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dictinfo findDictinfoByDictinfocode(String typecode, String code)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dictinfo findDictinfoByDictinfoname(String typecode, String info)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSuccessLog(String userid, String username, String userip,
			String message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveFailLog(String userid, String username, String userip,
			String message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExcelExportSXXSSF excelExport(String field, String path,
			String webpath, String filePrefix, List datas, int flushRows)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
