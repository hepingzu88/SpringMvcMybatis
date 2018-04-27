package yyd.yun.web.answer;


import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Operator;
import yyd.yun.beans.Semantic;
import yyd.yun.common.ResultModel;
import yyd.yun.service.AskAnswerService;
import yyd.yun.service.SemanticService;

@Controller
@RequestMapping("/answer/custom")
public class AnswerAction {

	@Autowired 
	private AskAnswerService askAnswerService;
	
	@Autowired
	private SemanticService semanticService;
	
	/*@RequestMapping(value="/custom-answer-index")
	public String answerAdd(Integer id,Integer pageNum,ModelMap map){
		map.put("id", id);
		map.put("page", pageNum);
		return "answer/answer-index";
	}*/
	
	//input
	/*@RequestMapping(value="/custom-answer-find",method=RequestMethod.POST)
	public String answerFind(Integer id,Integer pageNum,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<Semantic> page = semanticService.findByIdAndUserId(id, admin.getId(),pageNum);
		map.put("page", page);
		return "answer/answer-index-list";
	}*/
	
	//数据表格
	@RequestMapping(value="/custom-answer-index")
	public String answerAdd(Integer id,Integer pageNum,ModelMap map){
		map.put("id", id);
		map.put("page", pageNum);
		return "answer/answer";
	}
	
	//数据表格
	@RequestMapping(value="/custom-answer-find")
	@ResponseBody
	public Map<String,Object> answerFind(Integer id,Integer page,Integer limit,ModelMap modelMap,Integer sceneId,Semantic info){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(page==null||page<1){
			page=1;
        }
		Map<String,Object> map = new HashMap<>();
		PageInfo<Semantic> pages = semanticService.findByIdAndUserId(id, admin.getId(),page);
		if(pages == null){
			map = null;
			return map;
		}
		map.put("code", 0);
		map.put("msg","");
		map.put("count",pages.getTotal());
		map.put("data", pages.getList());
		return map;
	}
	
	
	/*@RequestMapping(value="/custom-answer-index",method=RequestMethod.GET)
	public String customAnswerIndex(Integer id,Integer pageNum,ModelMap map){
		System.out.println(id  +"   id");
		AskAnswer ask = new AskAnswer();
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		ask.setOperatorId(admin.getId());
		ask.setSceneId(id);
		PageInfo<AskAnswer> page = askAnswerService.find(ask, pageNum);
		map.put("page", page);
		return "answer/answer-index";
	}*/
	
	/*@RequestMapping(value="/custom-answer-add",method=RequestMethod.GET)
	public String customanswerAdd(Integer id,ModelMap map){
		List<Semantic> semantic = semanticService.findByIdAndUserId(id, admin.getId());
		map.put("list", semantic);
		return "answer/answer-add";
	}*/
	
	@RequestMapping(value="custom-answer-add",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel querstionAndAnswer(String arr,Integer id,Integer operatorId){
		int index = semanticService.updateSemanticAnswer(arr,id,operatorId);
		if(index > 0){
			return new ResultModel("succeed",true);
		}
		return new ResultModel("failure",false);
	}
	
	
}
