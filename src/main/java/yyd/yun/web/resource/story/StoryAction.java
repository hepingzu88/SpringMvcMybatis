package yyd.yun.web.resource.story;

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

import yyd.yun.beans.ResultModel;
import yyd.yun.beans.StoryResVo;
import yyd.yun.beans.TbAge;
import yyd.yun.beans.TbStoryAlbum;
import yyd.yun.beans.TbStoryCategory;
import yyd.yun.beans.TbStoryRes;
import yyd.yun.beans.TbStoryResTag;
import yyd.yun.beans.TbTag;
import yyd.yun.service.MusicService;
import yyd.yun.service.StoryService;

@Controller
@RequestMapping("/story")
public class StoryAction {

	@Autowired
	private MusicService musicService;
	
	@Autowired
	private StoryService storyService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(ModelMap map){
		List<TbStoryCategory> list= storyService.selectTbStoryCategory();
		map.put("listStoryCategory", list);
		return "resource/story/story-index";
	}
	
	@RequestMapping(value="/res/index")
	@ResponseBody
	public Map<String,Object> musicIndex(StoryResVo vo,Integer page,Integer limit){
		if(page == null || page < 1){
			page = 1;
		}
		PageInfo<StoryResVo> pageInfo = storyService.pageStoryResVo(vo, page);
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
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(TbStoryRes storyRes,ModelMap map){
		List<TbStoryRes> tbStoryRes = storyService.listTbStoryRes(storyRes);
		List<TbTag> listTag = null;
		if(!tbStoryRes.isEmpty()){
			TbTag tbTag = new TbTag();
			tbTag.setCategoryId(tbStoryRes.get(0).getCategoryId());
			listTag = musicService.getTbTag(tbTag);
			map.put("storyResId", tbStoryRes.get(0).getId());
			map.put("storyRes",tbStoryRes.get(0));
		}
		List<TbAge> listAge = musicService.listTbAge();
		if(listAge.isEmpty()){
			listAge = null;
		}
		List<TbStoryAlbum> listAlbum = storyService.ListTbStoryAlbum();
		map.put("listAlbum", listAlbum);//专辑
		map.put("listTag", listTag);//全部标签
		map.put("listAge", listAge);
		return "resource/story/story-edit";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel editStoryResAndTag(String tagArr,TbStoryRes tbStoryRes){
		int index = -1;
		int indexOf = -1;
		String tagArray[] = null;
		if(!"".equals(tagArr) && tagArr != null){
			tagArray = tagArr.split(",");
		}
		if(tagArray != null){
			index = storyService.deleteByStoryResId(tbStoryRes.getId());
		}
		if(index >= 0){ 
			TbStoryResTag tbStoryResTag = null;
			for (int i = 0; i < tagArray.length; i++) {
				tbStoryResTag = new TbStoryResTag();
				tbStoryResTag.setTagId(Integer.valueOf(tagArray[i]));
				tbStoryResTag.setStoryResId(tbStoryRes.getId());
				indexOf = storyService.addTbStoryResTag(tbStoryResTag);
			}
		}
		if(indexOf > 0){
			tbStoryRes.setIsTagged(1);
			index = storyService.upateTbStoryRes(tbStoryRes);
		}else{
			tbStoryRes.setIsTagged(0);
			index = storyService.upateTbStoryRes(tbStoryRes);
		}
		if(index > 0){
			return new ResultModel("操作成功",true); 
		}
		return new ResultModel("操作失败",false); 
	}
	
	@RequestMapping(value="/queryStoryResTag",method=RequestMethod.POST)
	@ResponseBody
	public List<TbStoryResTag> queryStoryResTag(TbStoryResTag tag){
		List<TbStoryResTag> tbStoryResTag = storyService.selectStoryResTag(tag);
		if(tbStoryResTag.isEmpty()){
			tbStoryResTag = null;
		}
		return tbStoryResTag;
	}
	
	@RequestMapping(value="/delStoryRes",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel delMusicRes(Integer tbStoryResId){
		if(storyService.delTbStoryRes(tbStoryResId) < 0){
			return new ResultModel("删除失败",false); 
		}
		TbStoryResTag tag = new TbStoryResTag();
		tag.setStoryResId(tbStoryResId);
		List<TbStoryResTag> tbStoryResTag = storyService.selectStoryResTag(tag);
		int index = -1;
		if(!tbStoryResTag.isEmpty()){
			index = storyService.deleteByStoryResId(tbStoryResId);
		}
		if(index > 0){
			return new ResultModel("删除成功",true); 
		}
		return new ResultModel("删除失败",false); 
	}
	
	
	@RequestMapping(value="/storyUpdate",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel storyUpdate(TbStoryRes tbStoryRes){
		TbStoryRes storyRes = new TbStoryRes();
		storyRes.setId(tbStoryRes.getId());
		List<TbStoryRes> list = storyService.listTbStoryRes(storyRes);
		List<TbStoryRes> listTbStoryRes = null;
		if(!list.isEmpty()){
			tbStoryRes.setId(null);
			tbStoryRes.setCategoryId(list.get(0).getCategoryId());
			listTbStoryRes = storyService.listTbStoryRes(tbStoryRes);
		}
		if(!listTbStoryRes.isEmpty()){
			return new ResultModel("同一个故事类别，故事资源名称不能一样",false); 
		}
		storyRes.setName(tbStoryRes.getName());
		if(storyService.upateTbStoryRes(storyRes) > 0){
			return new ResultModel("修改成功",true); 
		}
		return new ResultModel("修改失败",false); 
	}
	
	@RequestMapping(value="/delTag",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel delTag(TbStoryResTag tag,Integer categoryId){
		List<TbStoryResTag> list = storyService.selectStoryResTag(tag);
		if(!list.isEmpty()){
			return new ResultModel("标签已被使用不支持删除",true); 
		}
		if(musicService.delTag(tag.getTagId()) > 0){
			if(storyService.deleteStoryTagId(tag.getTagId()) >= 0){
				return new ResultModel("删除成功",true); 
			}
		}
		return new ResultModel("删除失败",false); 
	}
	
}
