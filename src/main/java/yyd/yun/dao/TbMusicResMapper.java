package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbMusicRes;

public interface TbMusicResMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbMusicRes record);

    int insertSelective(TbMusicRes record);

    TbMusicRes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbMusicRes record);

    int updateByPrimaryKey(TbMusicRes record);
    
    List<TbMusicRes> getTbMusicRes(TbMusicRes record);
}