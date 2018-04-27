package yyd.yun.web.apply;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Apply;
import yyd.yun.beans.Operator;
import yyd.yun.beans.Scene;
import yyd.yun.common.ResultModel;
import yyd.yun.service.ApplyService;
import yyd.yun.service.SceneService;

@Controller
@RequestMapping("/apply")
public class ApplyAction{

	@Autowired
	private ApplyService applyService; 
	
	@Autowired
	private SceneService sceneService;
	
	@RequestMapping(value="/apply-index",method=RequestMethod.GET)
	public String applyIndex(){
		return "apply/apply-index";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.POST)
	public String applyIndex(ModelMap map,Integer pageNum){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<Apply> page = applyService.queryAllApply(admin.getId(),pageNum);
		map.put("page", page);
		return "apply/apply-index-list";
	}
	
	
	@RequestMapping(value="/apply-add",method=RequestMethod.GET)
	public String applyAdd(){
		return "apply/apply-add";
	}
	
	@RequestMapping(value="/apply-add",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel applyAddPost(Apply apply){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Apply applys = applyService.findByName(admin.getId(), apply.getApplyName());
		if(applys != null){
			return new ResultModel("应用已存在",false);
		}
		String key = createKey(apply.getApplyName());
		apply.setApplyKey(key);
		apply.setOperatorId(admin.getId());
		
		if(applyService.addApply(apply) > 0){
			return new ResultModel("Success",true);
		}
		return new ResultModel("Failure",false);
	}
	
	
	@RequestMapping(value="/apply-scene",method=RequestMethod.GET)
	public String applyScene(Integer id,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		List<Scene> sceneList = null;
		List<Scene> publicSceneList = null;
		Apply apply = applyService.findById(admin.getId(), id);
		if(apply != null){
			String scene = apply.getSceneId();
			if(!"".equals(scene) && scene != null){
				String []sceneArr = scene.split(",");
				sceneList = sceneService.queryList(sceneArr);
			}
			String publicScene = apply.getPublicSceneId();
			if(!"".equals(publicScene) && publicScene != null){
				String []sceneArr = publicScene.split(",");
				publicSceneList = sceneService.queryList(sceneArr);
			}
		}
		map.put("sceneList",sceneList);
		map.put("publicSceneList", publicSceneList);
		map.put("list", apply);
		return "apply/apply-scene";
	}
	
	@RequestMapping(value="/apply-scene-find",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> applySceneFind(Integer id){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		Map<String,Object> map = new HashMap<>();
		Apply apply = applyService.findById(admin.getId(), id);
		List<Scene> li = sceneService.findById(1);
		List<Scene> list = sceneService.findById(admin.getId());
		map.put("apply", apply);//应用
		map.put("list", list);//用户所有自定义场景
		map.put("li", li);//公开场景
		return map;
	}
	
	
	
	@RequestMapping(value="/apply-add-scene",method=RequestMethod.GET)
	public String applyAddScene(Integer id,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		List<Scene> li = sceneService.findById(1);
		List<Scene> list = sceneService.findById(admin.getId());
		Apply apply = applyService.findById(admin.getId(), id);
		if(apply == null){
			apply = null;
		}
		map.put("apply", apply);//应用
		map.put("list", list);//用户所有自定义场景
		map.put("li", li);//公开场景
		return "apply/apply-add-scene";
	}
	
	
	@RequestMapping(value="/apply-add-scene",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel applySceneAdd(Apply apply,ModelMap map){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		apply.setOperatorId(admin.getId());
		if(applyService.updateApply(apply) > 0){
			return new ResultModel("succeed",true);
		}
		return new ResultModel("failure",false);
	}
	
	@RequestMapping(value="/apply-del",method=RequestMethod.GET)
	@ResponseBody
	public ResultModel applyDelScene(Apply apply){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		apply.setOperatorId(admin.getId());
		if(applyService.applyDelScene(apply,false) > 0){
			return new ResultModel("succeed",true);
		}
		return new ResultModel("Failure",false);
	}
	
	@RequestMapping(value="/apply-delPublic",method=RequestMethod.GET)
	@ResponseBody
	public ResultModel delPublic(Apply apply){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		apply.setOperatorId(admin.getId());
		if(applyService.applyDelScene(apply,true) > 0){
			return new ResultModel("succeed",true);
		}
		return new ResultModel("Failure",false);
	}
	
	@RequestMapping(value="/deleteApply",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel delete(Integer id,String applyName){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(id == null){
			return new ResultModel("删除失败",false);
		}
		Apply apply = applyService.findById(admin.getId(), id);
		if(apply == null){
			return new ResultModel("删除失败",false);
		}
		if((!"".equals(apply.getSceneId()) && apply.getSceneId() != null) || (!"".equals(apply.getPublicSceneId()) && apply.getPublicSceneId() != null)){
			return new ResultModel("该应用下有场景，无法删除",false);
		}
		if(applyService.deleteApply(admin.getId(),id,applyName)> 0){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
	
	public static String createKey(String name){
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		String key = randomNumberGenerator.nextBytes().toHex();
		// 密码加密
		String encryptionKey = new SimpleHash("md5", name, ByteSource.Util.bytes(key),2).toHex();
		return encryptionKey;
	}

	public static void main(String[] args) {
		String base = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz123456789";  
		
		//String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < base.length(); i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	  	System.out.println(sb.toString());    
	}
	
}
