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
import yyd.yun.beans.Entity;
import yyd.yun.beans.Operator;
import yyd.yun.beans.SceneVo;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.EntityDao;
import yyd.yun.dao.SceneVoDao;
import yyd.yun.service.EntityService;

@Service
public class EntityServiceImpl implements EntityService{

	private static Logger logger = LoggerFactory.getLogger(EntityServiceImpl.class);
	
	@Autowired
	private EntityDao dao;
	
	@Autowired
	private SceneVoDao sceneVoDao;
	
	@CommentAnno(operationType="新增实体操作:",operationName="新增实体")
	@Override
	public int addEntity(Entity entity) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		entity.setOperatorId(admin.getId());
		if(dao.addEntity(entity) > 0){
			logger.info("addEntity succeed" + entity);
			return 1;
		}
		logger.info("addEntity failure " + entity);
		return -1;
	}

	@Override
	public List<Entity> queryById(Integer operatorId){
		return dao.queryById(operatorId);
	}
	
	@Override
	public Entity findEntityByName(String entity){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		return dao.findEntityByName(admin.getId(), entity);
	}

	@Override
	public PageInfo<SceneVo> queryByIdEntity(Integer adminId,Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		SceneVo vo = new SceneVo();
		vo.setOperatorId(adminId);
		List<SceneVo> list = sceneVoDao.findSceneAndEntity(vo);
		 if (list != null && list.size() > 0) { 
			 logger.info("entity list "  + list +"  entity"+list);
			 return new PageInfo<SceneVo>(list);
         } else {
            return null;
         }
	}

	@CommentAnno(operationType="修改实体操作:",operationName="修改实体")
	@Override
	public int updateEntity(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@CommentAnno(operationType="删除实体操作:",operationName="删除实体")
	@Override
	public int delEntity(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<SceneVo> findSceneAndEntity(SceneVo vo){
		return sceneVoDao.findSceneAndEntity(vo);
	}

	@Override
	public List<Entity> findEntityById(Entity entity) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		entity.setOperatorId(admin.getId());
		return dao.findEntityById(entity);
	}

}
