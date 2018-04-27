package yyd.yun.web.resource.music;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.MusicResVo;
import yyd.yun.beans.ResultModel;
import yyd.yun.beans.TbAge;
import yyd.yun.beans.TbMusicCategory;
import yyd.yun.beans.TbMusicRes;
import yyd.yun.beans.TbMusicResTag;
import yyd.yun.beans.TbMusicSong;
import yyd.yun.beans.TbResCategory;
import yyd.yun.beans.TbTag;
import yyd.yun.beans.TbTagAndCategoryVo;
import yyd.yun.service.MusicService;

@Controller
@RequestMapping("/music")
public class MusicAction {

	@Autowired
	private MusicService musicService;
	
	
	@RequestMapping(value="/res/index")
	@ResponseBody
	public Map<String,Object> musicIndex(MusicResVo vo,Integer page,Integer limit){
		if(page == null || page < 1){
			page = 1;
		}
		PageInfo<MusicResVo> pageInfo = musicService.pageMusicResVo(vo, page);
		Map<String,Object> map = new HashMap<>();
		if(pageInfo == null){
			map = null;
			return map;
		}
		map.put("code", 0);
		map.put("msg","");
		map.put("count",pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String musicResIndex(ModelMap map){
		/*List<TbResCategory> listCategory = musicService.listResCategory();//所有分类
		map.put("listCategory", listCategory);*/
		List<TbMusicCategory> list = musicService.listTbMusicCategory();
		map.put("listMusicCategory", list);
		return "resource/music/music-index";
	}
	
	
	@RequestMapping(value="/addTag",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel addTag(TbTag tag){
		List<TbTag> tbTag = musicService.getTbTag(tag);
		if(!tbTag.isEmpty()){
			return new ResultModel("资源标签名已存在",false); 
		}
		if(musicService.saveTbTag(tag) > 0){
			return new ResultModel("新增成功",true); 
		}
		return new ResultModel("新增资源标签失败",false);
	}
	
	
	@RequestMapping(value="/addTag",method=RequestMethod.GET)
	public String tagAdd(ModelMap map){
		List<TbResCategory> listCategory = musicService.listResCategory();
		map.put("listCategory", listCategory);
		return "resource/music/music-add-tag";
	}
	
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(TbMusicRes musicRes,ModelMap map){
		//先根据Id查询音乐资源中的资源类别   然后根据资源类别ID查询  资源标签表
		List<TbMusicRes> listMusicRes = musicService.getTbMusicRes(musicRes);//根据音乐资源id找到音乐类别
		List<TbTag> listTag = null;
		if(!listMusicRes.isEmpty()){
			TbTag tbTag = new TbTag();
			tbTag.setCategoryId(listMusicRes.get(0).getCategoryId());
			listTag = musicService.getTbTag(tbTag);
			map.put("musicResId", listMusicRes.get(0).getId());
			map.put("musicRes",listMusicRes.get(0));
		}
		
		List<TbAge> listAge = musicService.listTbAge();
		if(listAge.isEmpty()){
			listAge = null;
		}
		map.put("listTag", listTag);//全部音乐资源的标签
		map.put("listAge", listAge);
		return "resource/music/music-edit";
	}
	
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel editMusicResAndTag(Integer ageId,String tagArr,TbMusicResTag tbMusicResTag){
		int index = -1;
		int indexOf = -1;
		String tagArray[] = null;
		if(!"".equals(tagArr) && tagArr != null){
			tagArray = tagArr.split(",");
		}
		if(tagArray != null){
			index = musicService.deleteByPrimaryMusicResId(tbMusicResTag.getMusicResId());
		}
		if(index >= 0){ 
			TbMusicResTag musicResTag = null;
			for (int i = 0; i < tagArray.length; i++) {
				musicResTag = new TbMusicResTag();
				musicResTag.setTagId(Integer.valueOf(tagArray[i]));
				musicResTag.setMusicResId(tbMusicResTag.getMusicResId());
				indexOf = musicService.insertMusicResTag(musicResTag);
			}
		}
		if(indexOf > 0){
			TbMusicRes tbMusicRes = new TbMusicRes();
			tbMusicRes.setAgeId(ageId);
			tbMusicRes.setId(tbMusicResTag.getMusicResId());
			tbMusicRes.setIsTagged(1);
			index = musicService.updateMusicRes(tbMusicRes);
		}else{
			TbMusicRes tbMusicRes = new TbMusicRes();
			tbMusicRes.setAgeId(ageId);
			tbMusicRes.setId(tbMusicResTag.getMusicResId());
			tbMusicRes.setIsTagged(0);
			index = musicService.updateMusicRes(tbMusicRes);
		}
		if(index > 0){
			return new ResultModel("操作成功",true); 
		}
		return new ResultModel("操作失败",false); 
	}
	
	
	@RequestMapping(value="/queryMusicResTag",method=RequestMethod.POST)
	@ResponseBody
	public List<TbMusicResTag> queryMusicResTag(TbMusicResTag tag){
		List<TbMusicResTag> tbMusicResTag = musicService.selectTbMusicResTag(tag);
		if(tbMusicResTag.isEmpty()){
			tbMusicResTag = null;
		}
		return tbMusicResTag;
	}
	
	@RequestMapping(value="/delMusicRes",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel delMusicRes(Integer tbMusicResId){
		if(musicService.deleteByPrimaryKey(tbMusicResId) < 0){
			return new ResultModel("删除失败",false); 
		}
		TbMusicResTag tag = new TbMusicResTag();
		tag.setMusicResId(tbMusicResId);
		List<TbMusicResTag> tbMusicResTag = musicService.selectTbMusicResTag(tag);
		int index = -1;
		if(!tbMusicResTag.isEmpty()){
			index = musicService.deleteByPrimaryMusicResId(tbMusicResId);
		}
		if(index > 0){
			return new ResultModel("删除成功",true); 
		}
		return new ResultModel("删除失败",false); 
	}
	
	@RequestMapping(value="/songUpdate",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel songUpdate(Integer musicResId,String song){
		TbMusicSong tbMusicSong = new TbMusicSong();
		tbMusicSong.setSong(song);
		if(!musicService.selectTbMusicSong(tbMusicSong).isEmpty()){
			return new ResultModel("歌名已存在|建议删除重复歌曲",false); 
		}
		TbMusicRes tbMusicRes = new TbMusicRes();
		tbMusicRes.setId(musicResId);
		List<TbMusicRes> listMusicRes = musicService.getTbMusicRes(tbMusicRes);
		int idnex = -1;
		if(!listMusicRes.isEmpty()){
			tbMusicSong.setId(listMusicRes.get(0).getSongId());
			idnex = musicService.updateSong(tbMusicSong);
		}
		if(idnex > 0){
			return new ResultModel("修改成功",true); 
		}
		return new ResultModel("修改失败",false); 
	}
	
	@RequestMapping(value="/selectTagCategoryList")
	@ResponseBody
	public Map<String,Object> selectTbTagAndCategoryVo(Integer page,Integer limit,ModelMap modelMap){
		/*if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<TbTagAndCategoryVo> page = musicService.selectTbTagAndCategoryVo(pageNum);
		modelMap.put("page", page);
		return "resource/music/tag-category-list";*/
		if(page == null || page < 1){
			page = 1;
		}
		PageInfo<TbTagAndCategoryVo> pageInfo = musicService.selectTbTagAndCategoryVo(page);
		Map<String,Object> map = new HashMap<>();
		if(pageInfo == null){
			map = null;
			return map;
		}
		map.put("code", 0);
		map.put("msg","");
		map.put("count",pageInfo.getTotal());
		map.put("data", pageInfo.getList());
		return map;
		
	}
	
	@RequestMapping(value="/tagCategoryIndex",method=RequestMethod.GET)
	public String tagCategoryIndex(){
		return "resource/music/tag-category-index";
	}
	
	@RequestMapping(value="/delTag",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel delTag(TbMusicResTag tag,Integer categoryId){
		List<TbMusicResTag> list = musicService.selectTbMusicResTag(tag);
		if(!list.isEmpty()){
			return new ResultModel("标签已被使用不支持删除",true); 
		}
		if(musicService.delTag(tag.getTagId()) > 0){
			if(musicService.deleteMusicResTagId(tag.getTagId()) >= 0){
				return new ResultModel("删除成功",true); 
			}
		}
		return new ResultModel("删除失败",false); 
	}
	
	@RequestMapping(value="/tagUpdate",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel tagUpdate(TbTag tag){
		TbMusicResTag tesTag = new TbMusicResTag();
		tesTag.setTagId(tag.getId());
		List<TbMusicResTag> list = musicService.selectTbMusicResTag(tesTag);
		if(!list.isEmpty()){
			return new ResultModel("标签已被使用不支持修改",true); 
		}
		if(musicService.updateTag(tag) > 0){
			return new ResultModel("修改成功",true); 
		}
		return new ResultModel("修改失败",true); 
	} 
	
}
