package yyd.yun.web.thesaurus.entity;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Entity;
import yyd.yun.beans.Operator;
import yyd.yun.beans.ResultModel;
import yyd.yun.beans.Scene;
import yyd.yun.beans.SceneVo;
import yyd.yun.service.EntityService;
import yyd.yun.service.SceneService;

@Controller
@RequestMapping("/thesaurus/entity")
public class EntityAction {

	private static Logger logger = LoggerFactory.getLogger(EntityAction.class);
	
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private SceneService sceneService;
	
	@RequestMapping(value="entity-add",method=RequestMethod.GET)
	public String endtityAddIndex(ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		List<Scene> list = sceneService.findById(admin.getId());
		map.put("list",list);
		return "thesaurus/entity/entity-add";
	}
	
	@RequestMapping(value="entity-add",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel endtityAdd(Entity entity){
		List<Entity> en = entityService.findEntityById(entity);
		if(!en.isEmpty()){
			return new ResultModel("该场景下实体已存在",false);
		}
		if(entityService.addEntity(entity) > 0){
			return new ResultModel("增加成功",true);
		}
		return new ResultModel("增加失败",false);
	}
	
	@RequestMapping(value="/entity-index",method=RequestMethod.GET)
	public String entityIndex(){
		return "thesaurus/entity/entity-index";
	}
	
	
	@RequestMapping(value="/entity-find",method=RequestMethod.POST)
	public String entityFind(Integer id,Integer pageNum,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<SceneVo> page = entityService.queryByIdEntity(admin.getId(),pageNum);
		map.put("page", page);
		return "thesaurus/entity/entity-index-list";
	}
	
	
}
