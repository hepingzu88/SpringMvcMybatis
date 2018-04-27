package yyd.yun.web.thesaurus.applyage;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.AgeBand;
import yyd.yun.beans.Operator;
import yyd.yun.beans.ResultModel;
import yyd.yun.beans.Semantic;
import yyd.yun.service.AgeBandService;
import yyd.yun.service.SemanticService;

@RequestMapping("/thesaurus/ageBand")
@Controller
public class ApplyAgeAction {

	@Autowired
	private AgeBandService ageBandService;
	
	@Autowired
	private SemanticService semanticService;
	
	@RequestMapping(value="/ageBand-index",method=RequestMethod.GET)
	public String applyIndex(){
		return "thesaurus/applyAge/applyAge-index";
	}
	
	@RequestMapping(value="/ageBand-add",method=RequestMethod.GET)
	public String ageBandAddIndex(){
		return "thesaurus/applyAge/ageBand-add";
	}
	
	@RequestMapping(value="/ageBand-add",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel actionAdd(AgeBand ageBand){
		AgeBand ac = ageBandService.findByName(ageBand.getAgeBand());
		if(ac != null){
			return new ResultModel("此行为已存在",false);
		}
		if(ageBandService.addAgeBand(ageBand) > 0){
			return new ResultModel("增加成功",true);
		}
		return new ResultModel("增加失败",false);
	}
	
	@RequestMapping(value="/ageBand-find",method=RequestMethod.POST)
	public String actionIndexFind(Integer id,Integer pageNum,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<AgeBand> page = ageBandService.findAgeBand(admin.getId(),pageNum);
		map.put("page", page);
		
		return "thesaurus/applyAge/applyAge-index-list";
	}
	
	@RequestMapping(value="/ageBand-delete",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel actionDelete(Integer id,String ageBand){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Semantic se = new Semantic();
		se.setOperatorId(admin.getId());
		se.setIntentId(id);
		if(!semanticService.seletSemantic(se).isEmpty()){
			return new ResultModel("该行为已被语料所使用，无法删除",false);
		}
		if(ageBandService.delAgeBand(id, admin.getId(),ageBand) > 0){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
}
