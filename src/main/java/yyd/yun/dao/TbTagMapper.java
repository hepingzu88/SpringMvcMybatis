package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbTag;

public interface TbTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbTag record);

    int insertSelective(TbTag record);

    TbTag selectByPrimaryKey(Integer id);
    
    List<TbTag> selectByTag(TbTag record);
    
    List<TbTag> selectAllTbTag(); 

    int updateByPrimaryKeySelective(TbTag record);

    int updateByPrimaryKey(TbTag record);
}