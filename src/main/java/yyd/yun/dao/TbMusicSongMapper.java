package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbMusicSong;

public interface TbMusicSongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbMusicSong record);

    int insertSelective(TbMusicSong record);

    List<TbMusicSong> selectTbMusicSong(TbMusicSong tbMusicSong);

    int updateByPrimaryKeySelective(TbMusicSong record);

    int updateByPrimaryKey(TbMusicSong record);
}