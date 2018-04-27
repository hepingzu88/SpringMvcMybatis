package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbMusicCategory;

public interface TbMusicCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbMusicCategory record);

    int insertSelective(TbMusicCategory record);

    TbMusicCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbMusicCategory record);

    int updateByPrimaryKey(TbMusicCategory record);
    
    //查询音乐大类
  	public List<TbMusicCategory> listTbMusicCategory();
}