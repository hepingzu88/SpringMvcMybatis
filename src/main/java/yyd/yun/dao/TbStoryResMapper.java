package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbStoryRes;

public interface TbStoryResMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbStoryRes record);

    int insertSelective(TbStoryRes record);

    TbStoryRes selectByPrimaryKey(Integer id);
    
    List<TbStoryRes> listTbStoryRes(TbStoryRes record);

    int updateByPrimaryKeySelective(TbStoryRes record);

    int updateByPrimaryKey(TbStoryRes record);
}