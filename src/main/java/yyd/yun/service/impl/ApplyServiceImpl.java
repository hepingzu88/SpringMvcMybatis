package yyd.yun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.CommentAnno;
import yyd.yun.beans.Apply;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.ApplyDao;
import yyd.yun.service.ApplyService;
import yyd.yun.web.thesaurus.entity.EntityAction;

@Service
public class ApplyServiceImpl implements ApplyService{

	@Autowired
	private ApplyDao applyDao;
	
	@CommentAnno(operationType="应用新增操作:",operationName="新增应用")
	@Override
	public int addApply(Apply apply) {
		return applyDao.addApply(apply);
	}

	private static Logger logger = LoggerFactory.getLogger(EntityAction.class);
	
	@CommentAnno(operationType="应用修改操作:",operationName="修改应用")
	@Override
	public int updateApply(Apply apply) {
		/*Apply applys = applyDao.findById(apply.getOperatorId(),apply.getId());
		if(applys != null){
			if(!"".equals(applys.getSceneId()) && applys.getSceneId() != null){
				String sc = applys.getSceneId();
				sc += ","+apply.getSceneId();
				apply.setSceneId(sc);
			}
			if(!"".equals(applys.getPublicSceneId()) && applys.getPublicSceneId() != null){
				String str = applys.getPublicSceneId();
				str += ","+apply.getPublicSceneId();
				apply.setPublicSceneId(str);
			}
			apply.setSceneId(apply.getSceneId());
			apply.setPublicSceneId(apply.getPublicSceneId());
		}*/
		return applyDao.updateApply(apply);
	}
	
	@Override
	public PageInfo<Apply> queryAllApply(Integer operatorId,Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<Apply> list = applyDao.queryAllApply(operatorId);
		if (list != null && list.size() > 0) { 
			return new PageInfo<Apply>(list);
        } else {
            return null;
        }
	}

	@Override
	public Apply findById(Integer operatorId, Integer id) {
		return applyDao.findById(operatorId, id);
	}

	@Override
	public Apply findByName(Integer operatorId, String applyName) {
		return applyDao.findByName(operatorId, applyName);
	}
	
	@CommentAnno(operationType="应用删除场景操作:",operationName="删除场景")
	@Override
	public int applyDelScene(Apply apply,boolean bool) {
		Apply applys = applyDao.findById(apply.getOperatorId(),apply.getId());
		if(applys == null){
			return -1;
		}
		String [] sc = null;
		if(!bool){
			if(contains(applys.getSceneId())){//true , false 
				sc = applys.getSceneId().split(",");
				String a = splic(sc,apply.getSceneId());//跟数据库的字符串比较,相同删除;
				String arr[] = a.split(",");//
				String scene = "";
				for (int i = 0; i < arr.length; i++) {
					if(i < arr.length-1){
						scene += arr[i]+",";
					}else{
						scene += arr[i];
					}
				}
				applys.setSceneId(scene);
			}else{
				if(applys.getSceneId().equals(apply.getSceneId())){
					applys.setSceneId(null);
				}
			}
			return applyDao.updateApplySceneId(applys);
		}else{
			if(contains(applys.getPublicSceneId())){
				sc = applys.getPublicSceneId().split(",");
				String arr[] = splic(sc,apply.getPublicSceneId()).split(",");
				String scene = "";
				for (int i = 0; i < arr.length; i++) {
					if(i < arr.length-1){
						scene += arr[i]+",";
					}else{
						scene += arr[i];
					}
				}
				applys.setPublicSceneId(scene);
			}else{
				if(applys.getPublicSceneId().equals(apply.getPublicSceneId())){
					applys.setPublicSceneId(null);
				}
			}
			return applyDao.updateApplyPublicSceneId(applys);
		}
	}
	
	public static boolean contains(String str){
		if(str.contains(",")){
			return true;
		}
		return false;
	} 
	
	public static String splic(String sc[],String str){
		String scene = "";
		for (int i = 0; i < sc.length; i++) {
			if(!str.equals(sc[i])){
				if(i < sc.length-1){
					scene += sc[i]+",";
				}else{
					scene += sc[i];
				}
			}
		}
		return scene;
	}

	public static boolean isExist(List<Apply> list,String str){
		for (Apply apply : list) {
			if(!"".equals(apply.getSceneId())){
				if(apply.getSceneId().contains(",")){
					String arr [] = apply.getSceneId().split(",");
					for (int i = 0; i < arr.length; i++) {
						if(str.equals(arr[i])){
							return false;
						}
					}
				}else{
					if(apply.getSceneId().equals(str)){
						return false;
					}
				}
				
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		List<Apply> list = new ArrayList<>();
		Apply ap = new Apply();
		ap.setSceneId("1,2,3,4,5");
		list.add(ap);
		String str = "5";
		String scene = "";
		for (Apply apply : list) {
			if(!"".equals(apply.getSceneId())){
				if(apply.getSceneId().contains(",")){
					String arr [] = apply.getSceneId().split(",");
					for (int i = 0; i < arr.length; i++) {
						if(!str.equals(arr[i])){
							if(i == arr.length-1){
								scene += arr[i];
							}else{
								scene += arr[i]+",";
							}
						}
					}
				}else{
					if(apply.getSceneId().equals(str)){
						System.out.println(str +" bbbbb");
						break;
					}
				}
				
			}
		}
		System.out.println(scene  +" berak");
		String a [] =scene.split(",");
		String result = "";
		for (int i = 0; i < a.length; i++) {
			if(i == a.length-1){
				result += a[i];
			}else{
				result += a[i]+",";
			}
		}
		System.out.println(result);
	}
	
	@CommentAnno(operationType="应用删除操作:",operationName="删除应用")
	@Override
	public int deleteApply(Integer operatorId, Integer id,String applyName) {
		return applyDao.deleteApply(operatorId, id);
	}
	
	@Override
	public List<Apply>  selectApply(Apply apply){
		return applyDao.selectApply(apply);
	}

}
