package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbEduSemester;

public interface TbEduSemesterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbEduSemester record);

    int insertSelective(TbEduSemester record);

    TbEduSemester selectByPrimaryKey(Integer id);
    
    List<TbEduSemester> selectTbEduSemester();

    int updateByPrimaryKeySelective(TbEduSemester record);

    int updateByPrimaryKey(TbEduSemester record);
}