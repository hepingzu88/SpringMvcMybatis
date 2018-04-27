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
import yyd.yun.beans.Intent;
import yyd.yun.beans.Operator;
import yyd.yun.beans.SceneVo;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.IntentDao;
import yyd.yun.dao.SceneVoDao;
import yyd.yun.service.IntentService;

@Service
public class IntentServiceImpl implements IntentService{

	private static Logger logger = LoggerFactory.getLogger(IntentServiceImpl.class);
	
	@Autowired
	private IntentDao dao;
	
	@Autowired
	private SceneVoDao sceneAndIntentVoDao;
	
	@CommentAnno(operationType="行为新增操作:",operationName="新增行为")
	@Override
	public int addIntent(Intent intent) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		intent.setOperatorId(admin.getId());
		if(dao.addIntent(intent) > 0){
			logger.info("add Intent succeed" + intent);
			return 1;
		}
		logger.info("add Action failure " + intent);
		return -1;
	}

	
	@Override
	public PageInfo<SceneVo> findIntent(Integer id,Integer pageNum){
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		SceneVo vo = new SceneVo();
		vo.setOperatorId(id);
		List<SceneVo> list = sceneAndIntentVoDao.findSceneAndIntent(vo);
		 logger.info("Intent list "  + list);
		 if (list != null && list.size() > 0) { 
			 logger.info("Intent list "  + list +"  Intent"+list);
			 return new PageInfo<SceneVo>(list);
         } else {
            return null;
         }
	}
	
	@Override
	public Intent findByName(String actionName){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		return dao.findByName(actionName, admin.getId());
	}
	
	@Override
	public List<Intent> queryByIdIntent(Integer id) {
		return dao.queryByIdIntent(id);
	}

	@Override
	public List<Intent> findIntentById(Intent intent){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		intent.setOperatorId(admin.getId());
		return dao.findIntentById(intent);
	}
	
	@Override
	public int updateIntent(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@CommentAnno(operationType="意图删除操作:",operationName="删除意图")
	@Override
	public int delIntent(Integer id,Integer operatorId,String action) {
		return dao.delIntent(id, operatorId);
	}


	@Override
	public List<SceneVo> findSceneAndIntent(SceneVo vo){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		vo.setOperatorId(admin.getId());
		return sceneAndIntentVoDao.findSceneAndIntent(vo);
	}

}
