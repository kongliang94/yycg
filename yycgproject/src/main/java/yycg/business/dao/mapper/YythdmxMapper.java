package yycg.business.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yycg.business.pojo.po.Yythdmx;
import yycg.business.pojo.po.YythdmxExample;

public interface YythdmxMapper {
    int countByExample(YythdmxExample example);

    int deleteByExample(YythdmxExample example);

    int deleteByPrimaryKey(String id);

    int insert(Yythdmx record);

    int insertSelective(Yythdmx record);

    List<Yythdmx> selectByExample(YythdmxExample example);

    Yythdmx selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Yythdmx record, @Param("example") YythdmxExample example);

    int updateByExample(@Param("record") Yythdmx record, @Param("example") YythdmxExample example);

    int updateByPrimaryKeySelective(Yythdmx record);

    int updateByPrimaryKey(Yythdmx record);
}