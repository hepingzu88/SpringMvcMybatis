package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbEduVersion;

public interface TbEduVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbEduVersion record);

    int insertSelective(TbEduVersion record);

    TbEduVersion selectByPrimaryKey(Integer id);
    
    List<TbEduVersion> selectTbEduVersion();

    int updateByPrimaryKeySelective(TbEduVersion record);

    int updateByPrimaryKey(TbEduVersion record);
}