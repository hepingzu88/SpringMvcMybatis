package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbEduRes;

public interface TbEduResMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(TbEduRes record);

    int insertSelective(TbEduRes record);

    TbEduRes selectByPrimaryKey(Integer id);

    List<TbEduRes> selectTbEduRes(TbEduRes record);
    
    int updateByPrimaryKeySelective(TbEduRes record);

    int updateByPrimaryKey(TbEduRes record);
}