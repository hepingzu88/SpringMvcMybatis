package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbStoryCategory;

public interface TbStoryCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbStoryCategory record);

    int insertSelective(TbStoryCategory record);

    TbStoryCategory selectByPrimaryKey(Integer id);
    
    List<TbStoryCategory> selectTbStoryCategory();

    int updateByPrimaryKeySelective(TbStoryCategory record);

    int updateByPrimaryKey(TbStoryCategory record);
}