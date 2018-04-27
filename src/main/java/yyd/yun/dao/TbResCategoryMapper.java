package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbResCategory;
import yyd.yun.beans.TbTagAndCategoryVo;

public interface TbResCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbResCategory record);

    int insertSelective(TbResCategory record);

    TbResCategory selectByPrimaryKey(Integer id);
    
    List<TbResCategory> selectAllTbResCategory();

    int updateByPrimaryKeySelective(TbResCategory record);

    int updateByPrimaryKey(TbResCategory record);
    
    //标签分类
    List<TbTagAndCategoryVo> selectTbTagAndCategoryVo();
}