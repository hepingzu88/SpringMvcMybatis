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
import yyd.yun.beans.Action;
import yyd.yun.beans.Operator;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.ActionDao;
import yyd.yun.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService{

	private static Logger logger = LoggerFactory.getLogger(ActionServiceImpl.class);
	
	@Autowired
	private ActionDao dao;
	
	@CommentAnno(operationType="行为新增操作:",operationName="新增行为")
	@Override
	public int addAction(Action action) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		action.setOperatorId(admin.getId());
		if(dao.addAction(action) > 0){
			logger.info("add Action succeed" + action);
			return 1;
		}
		logger.info("add Action failure " + action);
		return -1;
	}

	
	@Override
	public PageInfo<Action> findAction(Integer id,Integer pageNum){
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<Action> list = dao.queryByIdAction(id);
		 logger.info("scene list "  + list);
		 if (list != null && list.size() > 0) { 
			 logger.info("action list "  + list +"  action"+list);
			 return new PageInfo<Action>(list);
         } else {
            return null;
         }
	}
	
	@Override
	public Action findByName(String actionName){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		return dao.findByName(actionName, admin.getId());
	}
	
	@Override
	public List<Action> queryByIdAction(Integer id) {
		return dao.queryByIdAction(id);
	}

	@Override
	public List<Action> findActionById(Action action){
		return dao.findActionById(action);
	}
	
	@Override
	public int updateAction(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@CommentAnno(operationType="行为删除操作:",operationName="删除行为")
	@Override
	public int delAction(Integer id,Integer operatorId,String action) {
		return dao.delAction(id, operatorId);
	}

}
