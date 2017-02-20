package yycg.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yycg.business.dao.mapper.YpxxMapper;
import yycg.business.dao.mapper.YpxxMapperCustom;
import yycg.business.pojo.vo.YpxxCustom;
import yycg.business.pojo.vo.YpxxQueryVo;
import yycg.business.service.YpxxService;

@Service
public class YpxxServiceImpl implements YpxxService{

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

}
