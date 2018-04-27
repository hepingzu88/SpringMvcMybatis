package yyd.yun.web.thesaurus.custom;


import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Apply;
import yyd.yun.beans.Operator;
import yyd.yun.beans.ResultModel;
import yyd.yun.beans.Scene;
import yyd.yun.beans.Semantic;
import yyd.yun.service.ApplyService;
import yyd.yun.service.SceneService;
import yyd.yun.service.SemanticService;
import yyd.yun.service.impl.ApplyServiceImpl;

@Controller
@RequestMapping("thesaurus")
public class CustomAction {

	@Autowired
	private SceneService sceneService;
	
	@Autowired
	private SemanticService semanticService;
	
	@Autowired
	private ApplyService applyService;
	
	@RequestMapping(value="/public/public-scene-add",method=RequestMethod.GET)
	public String publicSceneAddIndex(){
		return "thesaurus/public/public-scene-add";
	}
	
	@RequestMapping(value="/custom/custom-scene-add",method=RequestMethod.GET)
	public String customSceneAddIndex(){
		return "thesaurus/custom/custom-scene-add";
	}
	
	@RequestMapping(value="/custom/custom-scene-add",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel customSceneAdd(Scene scene){
		Scene sc = sceneService.findSceneByName(scene.getScene());
		if(sc != null){
			return new ResultModel("场景已存在",false);
		}
		if(sceneService.addScene(scene) > 0){
			return new ResultModel("增加成功",true);
		}
		return new ResultModel("增加失败",false);
	}
	
	@RequestMapping(value="/custom/custom-index",method=RequestMethod.GET)
	public String custom(Integer id,ModelMap map){ 
		return "thesaurus/custom/custom-index";
	}
	
	@RequestMapping(value="/custom/custom-find",method=RequestMethod.POST)
	public String customIndex(Integer id,Integer pageNum,ModelMap map){ 
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<Scene> page = sceneService.queryById(admin.getId(),pageNum);
		map.put("page", page);
		return "thesaurus/custom/custom-index-list";
	}
	
	@RequestMapping(value="/custom-scene-delete",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel customSceneDelete(Integer id,String scene){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Semantic se = new Semantic();
		se.setOperatorId(admin.getId());
		se.setSceneId(id);
		if(!semanticService.seletSemantic(se).isEmpty()){
			return new ResultModel("该场景下有语料，无法删除",false);
		}
		Apply apply = new Apply();
		apply.setOperatorId(admin.getId());
		List<Apply> list = applyService.selectApply(apply);
		if(!list.isEmpty()){
			if(!ApplyServiceImpl.isExist(list, id+"")){
				return new ResultModel("该场景已被应用使用中，无法删除",false);
			}
		}
		if(sceneService.deleteScene(id, admin.getId(),scene) > 0){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
	@RequestMapping(value="/findSceneById",method=RequestMethod.POST)
	@ResponseBody
	public List<Scene> findSceneById(Scene scene){
		if(scene.getId().equals("") || scene.getId() == null){
			return null;
		}
		List<Scene> list = sceneService.findSceneById(scene);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}
}
