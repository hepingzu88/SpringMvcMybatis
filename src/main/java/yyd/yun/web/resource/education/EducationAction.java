package yyd.yun.web.resource.education;

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

import yyd.yun.beans.EducationVo;
import yyd.yun.beans.ResultModel;
import yyd.yun.beans.TbAge;
import yyd.yun.beans.TbEduGrade;
import yyd.yun.beans.TbEduRes;
import yyd.yun.beans.TbEduResTag;
import yyd.yun.beans.TbEduSemester;
import yyd.yun.beans.TbEduVersion;
import yyd.yun.beans.TbTag;
import yyd.yun.service.EducationService;
import yyd.yun.service.MusicService;

@Controller
@RequestMapping("/edu")
public class EducationAction {

	@Autowired
	private MusicService musicService;
	
	@Autowired
	private EducationService  educationService;	
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String eduIndex(ModelMap map){
		//List<TbResCategory> listCategory = musicService.listResCategory();
		//map.put("listCategory", listCategory);
		List<TbEduVersion> list = educationService.selectTbEduVersion();
		map.put("listVersion",list);
		return "resource/education/edu-index";
	}
	
	@RequestMapping(value="/res/index")
	@ResponseBody
	public Map<String,Object> indexEdu(EducationVo vo,Integer page,Integer limit){
		if(page == null || page < 1){
			page = 1;
		}
		PageInfo<EducationVo> pageInfo = educationService.pageEduResVo(vo, page);
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
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel edit(TbEduRes tbEduRes,String tagArr){
		String tagArray[] = null;
		int index = -1;
		int indexOf = -1;
		if(!"".equals(tagArr) && tagArr != null){
			tagArray = tagArr.split(",");
		}
		if(tagArray != null){
			index = educationService.deleteEduResTagId(tbEduRes.getId());
		}
		if(index >= 0){
			TbEduResTag eduResTag = null;
			for (int i = 0; i < tagArray.length; i++) {
				eduResTag = new TbEduResTag();
				eduResTag.setTagId(Integer.valueOf(tagArray[i]));
				eduResTag.setEduResId(tbEduRes.getId());
				indexOf = educationService.insertEduResTag(eduResTag);
			}
		}
		if(indexOf > 0){
			tbEduRes.setIsTagged(1);
			index = educationService.updateTbEduRes(tbEduRes);
		}else{
			tbEduRes.setIsTagged(0);
			index = educationService.updateTbEduRes(tbEduRes);
		}
		if(index > 0){
			return new ResultModel("操作成功",true); 
		}
		return new ResultModel("操作失败",false); 
	}
	
	
	//编辑教程
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(ModelMap map,TbEduRes tbEduRes){
		//查询课程资源表
		List<TbEduRes> listTbEduRes = educationService.listTbEduRes(tbEduRes);
		List<TbTag> listTag = null;
		if(!listTbEduRes.isEmpty()){
			TbTag tbTag = new TbTag();
			tbTag.setCategoryId(listTbEduRes.get(0).getCategoryId());
			listTag = musicService.getTbTag(tbTag);
			map.put("eduResId", listTbEduRes.get(0).getId());
			map.put("eduRes", listTbEduRes.get(0));
		}
		List<TbAge> listAge = musicService.listTbAge();
		if(listAge.isEmpty()){
			listAge = null;
		}
		List<TbEduGrade> listGrade = educationService.listTbEduGrade();
		if(listGrade.isEmpty()){
			listGrade = null;
		}
		List<TbEduSemester> listTbEduSemester = educationService.listTbEduSemester();
		if(listTbEduSemester.isEmpty()){
			listTbEduSemester = null;
		}
		List<TbEduVersion> listTbEduVersion = educationService.listTbEduVersion();
		if(listTbEduVersion.isEmpty()){
			listTbEduVersion = null;
		}
		map.put("listGrade", listGrade);
		map.put("listTbEduSemester", listTbEduSemester);
		map.put("listTbEduVersion", listTbEduVersion);
		map.put("listTag", listTag);//全部音乐资源的标签
		map.put("listAge", listAge);
		return "resource/education/edu-edit";
	}
	
	@RequestMapping(value="/queryEduResTag",method=RequestMethod.POST)
	@ResponseBody
	public List<TbEduResTag> queryEduResTag(TbEduResTag tbEduResTag){
		List<TbEduResTag> listTbEduResTag = educationService.listTbEduResTag(tbEduResTag);
		if(listTbEduResTag.isEmpty()){
			return null;
		}
		return listTbEduResTag;
	}
	
	@RequestMapping(value="/deletEduRes",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel deletEduRes(Integer tbEduResId){
		if(educationService.deleteEduRes(tbEduResId) < 0){
			return new ResultModel("删除失败",false); 
		}
		if(educationService.deleteEduResTagId(tbEduResId) >= 0){
			return new ResultModel("删除成功",true); 
		}
		return new ResultModel("删除失败",false); 
	}
	
	@RequestMapping(value="/updateEduRes",method=RequestMethod.POST)
	@ResponseBody 
	public ResultModel updateEduRes(TbEduRes tbEduRes){
		if(educationService.updateTbEduRes(tbEduRes) > 0){
			return new ResultModel("修改成功",true); 
		}
		return new ResultModel("修改失败",false); 
	}
	
	@RequestMapping(value="/delTag",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel delTag(TbEduResTag tag,Integer categoryId){
		List<TbEduResTag> list = educationService.listTbEduResTag(tag);
		if(!list.isEmpty()){
			return new ResultModel("标签已被使用不支持删除",true); 
		}
		if(musicService.delTag(tag.getTagId()) > 0){
			if(educationService.deleteEduTagId(tag.getTagId()) >= 0){
				return new ResultModel("删除成功",true); 
			}
		}
		return new ResultModel("删除失败",false); 
	}
	
}
