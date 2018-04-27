package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbEduGrade;

public interface TbEduGradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbEduGrade record);

    int insertSelective(TbEduGrade record);
    
    List<TbEduGrade> selectGrade();

    TbEduGrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbEduGrade record);

    int updateByPrimaryKey(TbEduGrade record);
}