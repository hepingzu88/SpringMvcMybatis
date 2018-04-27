package yyd.yun.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.MusicResVo;
import yyd.yun.beans.TbAge;
import yyd.yun.beans.TbMusicCategory;
import yyd.yun.beans.TbMusicRes;
import yyd.yun.beans.TbMusicResTag;
import yyd.yun.beans.TbMusicSong;
import yyd.yun.beans.TbResCategory;
import yyd.yun.beans.TbTag;
import yyd.yun.beans.TbTagAndCategoryVo;


public interface MusicService {

	//新增资源标签
	public int saveTbTag(TbTag tag);
	
	//根据标签对象查询
	public List<TbTag> getTbTag(TbTag tbTag);
	
	//查询全部资源标签
	public List<TbTag> listTbTag();
	
	//查询所有资源类别
	public List<TbResCategory> listResCategory();
	
	//查询所有音乐与歌手
	public PageInfo<MusicResVo> pageMusicResVo(MusicResVo vo,Integer pageNum);
	
	//音乐资源
	public List<TbMusicRes> getTbMusicRes(TbMusicRes musicRes);
	
	//查询所有年龄段
	public List<TbAge> listTbAge();
	
	//修改音乐资源
	public int updateMusicRes(TbMusicRes musicRes);
	
	//新增音乐资源表  对应资源id 与音乐资源id
	public int insertMusicResTag(TbMusicResTag tbMusicResTag);
	
	//删除音乐资源表
	public int deleteByPrimaryKey(Integer id);
	
	//查询音乐资源标签表
	public List<TbMusicResTag> selectTbMusicResTag(TbMusicResTag tag);
	
	//删除音乐资源标签表
	public int deleteByPrimaryMusicResId(Integer musicResId);
	
	//查询歌曲表
	public List<TbMusicSong> selectTbMusicSong(TbMusicSong tbMusicSong);
	
	//修改音乐
	public int updateSong(TbMusicSong tbMusicSong);
	
	//查询音乐大类
	public List<TbMusicCategory> listTbMusicCategory();
	
	
	//标签分类
	PageInfo<TbTagAndCategoryVo> selectTbTagAndCategoryVo(Integer pageNum);
	
	//删除标签
	int delTag(Integer tagId);
	
	//根据tagId删除资源对应 关系数据
	int deleteMusicResTagId(Integer tagId);
	
	//修改tag标签名
	int updateTag(TbTag tag);
}
