package yyd.yun.web.thesaurus.affective;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Affective;
import yyd.yun.beans.Operator;
import yyd.yun.beans.Semantic;
import yyd.yun.common.ResultModel;
import yyd.yun.service.AffectiveService;
import yyd.yun.service.SemanticService;

@RequestMapping("/thesaurus/affective")
@Controller
public class AffectiveAction {

	@Autowired
	private AffectiveService affectiveServcie;
	
	@Autowired
	private SemanticService semanticService;
	
	@RequestMapping(value="/affective-add",method=RequestMethod.GET)
	public String affectiveAdd(){
		return "thesaurus/affective/affective-add";
	}
	
	@RequestMapping(value="/affective-add",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel affectiveAddPost(Affective affective){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		affective.setOperatorId(admin.getId());
		List<Affective> list = affectiveServcie.findAffectiveList(affective);
		if(!list.isEmpty()){
			return new ResultModel("情感类型已存在，请勿重复添加",false);
		}
		if(affectiveServcie.addAffective(affective) > 0){
			return new ResultModel("新增成功",true);
		}
		return new ResultModel("新增失败",false);
	}
	
	@RequestMapping(value="/affective-index",method=RequestMethod.GET)
	public String affectiveIndex(Integer pageNum,Integer id,ModelMap map){
		return "thesaurus/affective/affective-index";
	}
	
	@RequestMapping(value="/affective-index",method=RequestMethod.POST)
	public String affectiveIndexList(Integer pageNum,Integer id,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Affective affective = new Affective();
		affective.setOperatorId(admin.getId());
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<Affective> page = affectiveServcie.findPageInfoAffectiv(affective, pageNum);
		map.put("page", page);
		return "thesaurus/affective/affective-index-list";
	}
	
	@RequestMapping(value="/affective-delete",method = RequestMethod.POST)
	@ResponseBody
	public ResultModel affectiveDelete(Integer id,String name){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Semantic semantic = new Semantic();
		semantic.setSentiment(name);
		semantic.setOperatorId(admin.getId());
		if(!semanticService.seletSemantic(semantic).isEmpty()){
			return new ResultModel("该情感状态被语料使用中，无法删除",false);
		}
		if(affectiveServcie.deleteAffective(id, admin.getId(),name) > 0){
			return new ResultModel("删除成功",false);
		}
		return new ResultModel("删除失败",false);
	}
	
	
}
