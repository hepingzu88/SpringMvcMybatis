package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.TbStoryResTag;

public interface TbStoryResTagMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByStoryResId(Integer storyResId);

    int insert(TbStoryResTag record);

    int insertSelective(TbStoryResTag record);

    TbStoryResTag selectByPrimaryKey(Integer id);
    
    List<TbStoryResTag> selectStoryResTag(TbStoryResTag record);

    int updateByPrimaryKeySelective(TbStoryResTag record);

    int updateByPrimaryKey(TbStoryResTag record);
    
    int deleteStoryTagId(@Param("tagId")Integer tagId);
}