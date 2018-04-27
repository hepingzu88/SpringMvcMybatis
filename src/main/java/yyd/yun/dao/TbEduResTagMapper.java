package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.TbEduResTag;

public interface TbEduResTagMapper {
	
    int deleteByPrimaryKey(Integer id);
    
    int deleteEduResId(Integer eduResId);

    int insert(TbEduResTag record);

    int insertSelective(TbEduResTag record);

    TbEduResTag selectByPrimaryKey(Integer id);

    List<TbEduResTag> selectTbEduResTag(TbEduResTag record);
    
    int updateByPrimaryKeySelective(TbEduResTag record);

    int updateByPrimaryKey(TbEduResTag record);
    
    int deleteEduTagId(@Param("tagId")Integer tagId);
}