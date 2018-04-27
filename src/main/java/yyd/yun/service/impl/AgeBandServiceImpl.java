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
import yyd.yun.beans.AgeBand;
import yyd.yun.beans.Operator;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.AgeBandDao;
import yyd.yun.service.AgeBandService;

@Service
public class AgeBandServiceImpl implements AgeBandService{

	private static Logger logger = LoggerFactory.getLogger(AgeBandServiceImpl.class);
	
	@Autowired
	private AgeBandDao dao;
	
	@CommentAnno(operationType="行为新增操作:",operationName="新增行为")
	@Override
	public int addAgeBand(AgeBand ageBand) {
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		ageBand.setOperatorId(admin.getId());
		if(dao.addAgeBand(ageBand) > 0){
			logger.info("add Action succeed" + ageBand);
			return 1;
		}
		logger.info("add Action failure " + ageBand);
		return -1;
	}

	
	@Override
	public PageInfo<AgeBand> findAgeBand(Integer id,Integer pageNum){
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<AgeBand> list = dao.queryByIdAgeBand(id);
		 logger.info("scene list "  + list);
		 if (list != null && list.size() > 0) { 
			 logger.info("action list "  + list +"  action"+list);
			 return new PageInfo<AgeBand>(list);
         } else {
            return null;
         }
	}
	
	@Override
	public AgeBand findByName(String ageBand){
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		return dao.findByName(ageBand, admin.getId());
	}
	
	@Override
	public List<AgeBand> queryByIdAgeBand(Integer id) {
		return dao.queryByIdAgeBand(id);
	}

	@Override
	public List<AgeBand> findAgeBandById(AgeBand ageBand){
		return dao.findAgeBandById(ageBand);
	}
	
	@Override
	public int updateAgeBand(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@CommentAnno(operationType="行为删除操作:",operationName="删除行为")
	@Override
	public int delAgeBand(Integer id,Integer operatorId,String action) {
		return dao.delAgeBand(id, operatorId);
	}

}
