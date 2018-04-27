package yyd.yun.web.thesaurus.action;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Action;
import yyd.yun.beans.Operator;
import yyd.yun.beans.ResultModel;
import yyd.yun.beans.Semantic;
import yyd.yun.service.ActionService;
import yyd.yun.service.SemanticService;

@Controller
@RequestMapping("/thesaurus/action")
public class BehaviorAction {

	@Autowired
	private ActionService actionService;
	
	@Autowired
	private SemanticService semanticService;
	
	@RequestMapping(value="/action-add",method=RequestMethod.GET)
	public String actionAddIndex(){
		return "thesaurus/action/action-add";
	}
	
	@RequestMapping(value="/action-add",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel actionAdd(Action action){
		Action ac = actionService.findByName(action.getAction());
		if(ac != null){
			return new ResultModel("此行为已存在",false);
		}
		if(actionService.addAction(action) > 0){
			return new ResultModel("增加成功",true);
		}
		return new ResultModel("增加失败",false);
	}
	
	@RequestMapping(value="/action-index",method=RequestMethod.GET)
	public String actionIndex(){
		return "thesaurus/action/action-index";
	}
	
	@RequestMapping(value="/action-find",method=RequestMethod.POST)
	public String actionIndexFind(Integer id,Integer pageNum,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<Action> page = actionService.findAction(admin.getId(),pageNum);
		map.put("page", page);
		
		return "thesaurus/action/action-index-list";
	}
	
	@RequestMapping(value="/action-delete",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel actionDelete(Integer id,String action){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Semantic se = new Semantic();
		se.setOperatorId(admin.getId());
		se.setIntentId(id);
		if(!semanticService.seletSemantic(se).isEmpty()){
			return new ResultModel("该行为已被语料所使用，无法删除",false);
		}
		if(actionService.delAction(id, admin.getId(),action) > 0){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
}
