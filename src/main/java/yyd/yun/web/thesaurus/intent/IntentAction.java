package yyd.yun.web.thesaurus.intent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Intent;
import yyd.yun.beans.Operator;
import yyd.yun.beans.Scene;
import yyd.yun.beans.SceneVo;
import yyd.yun.beans.Semantic;
import yyd.yun.common.ResultModel;
import yyd.yun.service.EntityService;
import yyd.yun.service.IntentService;
import yyd.yun.service.SceneService;
import yyd.yun.service.SemanticService;

@Controller
@RequestMapping("/thesaurus/intent")
public class IntentAction {

	@Autowired
	private SceneService sceneService;	
	
	@Autowired
	private IntentService intentService;
	
	@Autowired
	private SemanticService semanticService;
	
	@Autowired
	private EntityService entityService;
	
	@RequestMapping(value="/intent-index",method=RequestMethod.GET)
	public String intentIndex(ModelMap map){
		return "thesaurus/intent/intent-index";
	}
	
	@RequestMapping(value="/intent-find",method=RequestMethod.POST)
	public String actionIndexFind(Integer id,Integer pageNum,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<SceneVo> page = intentService.findIntent(admin.getId(),pageNum);
		map.put("page", page);
		
		return "thesaurus/intent/intent-index-list";
	}
	
	@RequestMapping(value="/intent-add",method=RequestMethod.GET)
	public String intentAdd(ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		List<Scene> list = sceneService.findById(admin.getId());
		map.put("list",list);
		return "thesaurus/intent/intent-add";
	}
	
	@RequestMapping(value="/intent-add",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel intentAddEntity(Intent intent){
		List<Intent> list = intentService.findIntentById(intent);
		if(!list.isEmpty()){
			return new ResultModel("该场景下意图已存在",false);
		}
		if(intentService.addIntent(intent) > 0){
			return new ResultModel("新增成功",true);
		}
		return new ResultModel("新增失败",false);
	}
	
	@RequestMapping(value="/intent-delete",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel actionDelete(Integer id,String intent){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Semantic se = new Semantic();
		se.setOperatorId(admin.getId());
		se.setIntentId(id);
		if(!semanticService.seletSemantic(se).isEmpty()){
			return new ResultModel("该意图已被语料所使用，无法删除",false);
		}
		if(intentService.delIntent(id, admin.getId(),intent) > 0){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
	//根据场景ID查找意图
	@RequestMapping(value="/findSceneByIdIntent",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findSceneByIdIntent(Intent intent){
		Map<String,Object> map = new HashMap<>();
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		intent.setOperatorId(admin.getId());
		List<Intent> list = intentService.findIntentById(intent);
		if(list.isEmpty()){
			map.put("intentList",null);
		}
		map.put("intentList",list);
		
		Scene sc = new Scene();
		sc.setId(intent.getSceneId());
		sc.setOperatorId(admin.getId());
		List<Scene> scList = sceneService.findSceneById(sc);
		if(scList.isEmpty()){
			map.put("scList", null);
		}
		map.put("scList", scList);
		
		SceneVo vo = new SceneVo();
		vo.setId(intent.getSceneId());
		vo.setOperatorId(admin.getId());
		List<SceneVo> entityList = entityService.findSceneAndEntity(vo);
		if(entityList.isEmpty()){
			map.put("entityList", null);
		}
		map.put("entityList", entityList);
		
		return map;
	}
	
	@RequestMapping(value="/selectSceneAndIntent",method=RequestMethod.POST)
	@ResponseBody
	public List<SceneVo> selectSceneAndIntent(SceneVo vo){
		List<SceneVo> listVo = intentService.findSceneAndIntent(vo);
		if(listVo.isEmpty()){
			return null;
		}
		return listVo;
	}
	
}
