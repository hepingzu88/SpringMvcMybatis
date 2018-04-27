package yyd.yun.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.CommentAnno;
import yyd.yun.beans.Operator;
import yyd.yun.beans.Scene;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.SceneDao;
import yyd.yun.service.SceneService;

@Service
public class SceneServiceImpl implements SceneService{

	private static Logger logger = LoggerFactory.getLogger(SceneService.class);
	
	@Autowired
	private SceneDao sceneDao;
	
	@CommentAnno(operationType="新增场景操作:",operationName="新增场景")
	@Override
	public int addScene(Scene scene) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(admin.getId() != 1){
			scene.setOperatorId(admin.getId());
			scene.setStatus(1);
		}else{
			scene.setOperatorId(admin.getId());
			scene.setStatus(0);
		}
		if(sceneDao.addScene(scene) > 0){
			logger.info("add scene succeed" + scene);
			return 1;
		}
		logger.info("add scene failure " + scene);
		return -1;
	}

	@Override
	public List<Scene> findScene() {
		return null;
	}
	
	@Override
	public Scene findSceneByName(String scene){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		return sceneDao.findSceneByName(admin.getId(), scene);
	}

	@Override
	public PageInfo<Scene> queryById(Integer id,Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<Scene> list = sceneDao.queryById(id);
		 logger.info("scene list "  + list);
		 if (list != null && list.size() > 0) { 
			 logger.info("scene list "  + list +"  scene"+list);
			 return new PageInfo<Scene>(list);
         } else {
            return null;
         }
	}

	@Override
	public List<Scene> findById(Integer id) {
		return sceneDao.findById(id);
	}

	@Override
	public List<Scene> queryList(String[] sceneArr) {
		return sceneDao.queryList(sceneArr);
	}

	@Override
	public List<Scene> findSceneById(Scene scene){
		return sceneDao.findSceneById(scene);
	}
	
	@CommentAnno(operationType="删除场景操作:",operationName="删除场景")
	@Override
	public int deleteScene(Integer id,Integer operatorId,String scene){
		return sceneDao.deleteScene(id, operatorId);
	}
}
