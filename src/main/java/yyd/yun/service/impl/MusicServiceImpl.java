package yyd.yun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.CommentAnno;
import yyd.yun.beans.MusicResVo;
import yyd.yun.beans.OperatorVo;
import yyd.yun.beans.TbAge;
import yyd.yun.beans.TbMusicCategory;
import yyd.yun.beans.TbMusicRes;
import yyd.yun.beans.TbMusicResTag;
import yyd.yun.beans.TbMusicSong;
import yyd.yun.beans.TbResCategory;
import yyd.yun.beans.TbTag;
import yyd.yun.beans.TbTagAndCategoryVo;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.MusicResVoDao;
import yyd.yun.dao.TbAgeMapper;
import yyd.yun.dao.TbMusicCategoryMapper;
import yyd.yun.dao.TbMusicResMapper;
import yyd.yun.dao.TbMusicResTagMapper;
import yyd.yun.dao.TbMusicSongMapper;
import yyd.yun.dao.TbResCategoryMapper;
import yyd.yun.dao.TbTagMapper;
import yyd.yun.service.MusicService;

@Service
public class MusicServiceImpl implements MusicService{

	@Autowired
	private TbTagMapper tbTagMapper; 
	
	@Autowired 
	private TbResCategoryMapper tbResCategoryMapper;
	
	@Autowired 
	private MusicResVoDao musicResVoDao;
	
	@Autowired
	private TbMusicResMapper tbMusicResMapper;
	
	@Autowired
	private TbAgeMapper tbAgeMapper;
	
	@Autowired
	private TbMusicResTagMapper tbMusicResTagMapper;
	
	@Autowired
	private TbMusicSongMapper TbMusicSongMapper;
	
	@Autowired
	private TbMusicCategoryMapper tbMusicCategoryMapper;
	
	@CommentAnno(operationType="新增标签操作:",operationName="新增标签")
	@Override
	public int saveTbTag(TbTag tag) {
		return tbTagMapper.insert(tag);
	}

	@Override
	public List<TbTag> getTbTag(TbTag tag) {
		return tbTagMapper.selectByTag(tag);
	}

	@Override
	public List<TbTag> listTbTag() {
		return tbTagMapper.selectAllTbTag();
	}

	@Override
	public List<TbResCategory> listResCategory() {
		return tbResCategoryMapper.selectAllTbResCategory();
	}

	@Override
	public PageInfo<MusicResVo> pageMusicResVo(MusicResVo vo,Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<MusicResVo> list = musicResVoDao.selectMusicResVo(vo);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<MusicResVo>(list);
         } else {
            return null;
         }
	}

	@Override
	public List<TbMusicRes> getTbMusicRes(TbMusicRes musicRes) {
		return tbMusicResMapper.getTbMusicRes(musicRes);
	}

	@Override
	public List<TbAge> listTbAge() {
		return tbAgeMapper.listTbAge();
	}

	@CommentAnno(operationType="修改音乐资源操作:",operationName="修改音乐资源")
	@Override
	public int updateMusicRes(TbMusicRes musicRes) {
		return tbMusicResMapper.updateByPrimaryKeySelective(musicRes);
	}

	@CommentAnno(operationType="新增音乐资源操作:",operationName="新增资源标签")
	@Override
	public int insertMusicResTag(TbMusicResTag tbMusicResTag) {
		return tbMusicResTagMapper.insert(tbMusicResTag);
	}

	@CommentAnno(operationType="删除音乐资源操作:",operationName="删除资源")
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tbMusicResMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TbMusicResTag> selectTbMusicResTag(TbMusicResTag tag) {
		return tbMusicResTagMapper.selectByPrimaryKey(tag);
	}

	@CommentAnno(operationType="删除音乐资源标签操作:",operationName="删除资源标签")
	@Override
	public int deleteByPrimaryMusicResId(Integer musicResId) {
		return tbMusicResTagMapper.deleteByPrimaryMusicResId(musicResId);
	}

	@Override
	public List<TbMusicSong> selectTbMusicSong(TbMusicSong tbMusicSong) {
		List<TbMusicSong> song = TbMusicSongMapper.selectTbMusicSong(tbMusicSong);
		if(!song.isEmpty()){
			return song;
		}
		return null;
	}

	@CommentAnno(operationType="修改音乐:",operationName="修改音乐名")
	@Override
	public int updateSong(TbMusicSong tbMusicSong) {
		return TbMusicSongMapper.updateByPrimaryKeySelective(tbMusicSong);
	}

	@Override
	public List<TbMusicCategory> listTbMusicCategory() {
		return tbMusicCategoryMapper.listTbMusicCategory();
	}

	@Override
	public PageInfo<TbTagAndCategoryVo> selectTbTagAndCategoryVo(Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<TbTagAndCategoryVo> list = tbResCategoryMapper.selectTbTagAndCategoryVo();
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<TbTagAndCategoryVo>(list);
         } else {
            return null;
         }
	}

	@Override
	public int delTag(Integer tagId) {
		return tbTagMapper.deleteByPrimaryKey(tagId);
	}

	@Override
	public int deleteMusicResTagId(Integer tagId) {
		return tbMusicResTagMapper.deleteMusicResTagId(tagId);
	}

	@Override
	public int updateTag(TbTag tag) {
		return tbTagMapper.updateByPrimaryKeySelective(tag);
	}

}
