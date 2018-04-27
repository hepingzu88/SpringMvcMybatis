package yyd.yun.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.CommentAnno;
import yyd.yun.beans.Affective;
import yyd.yun.beans.SemanticInfo;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.AffectiveDao;
import yyd.yun.service.AffectiveService;

@Service
public class AffectiveServiceImpl implements AffectiveService{

	@Autowired
	private AffectiveDao affactiveDao;
	
	private static Logger logger = LoggerFactory.getLogger(AffectiveServiceImpl.class);
	
	@CommentAnno(operationType="情感状态新增操作:",operationName="新增情感状态")
	@Override
	public int addAffective(Affective affective) {
		return affactiveDao.addAffective(affective);
	}

	@CommentAnno(operationType="情感状态删除操作:",operationName="删除情感状态")
	@Override
	public int deleteAffective(Integer id, Integer operatorId,String name) {
		return affactiveDao.deleteAffective(id, operatorId);
	}

	@CommentAnno(operationType="情感状态修改操作:",operationName="修改情感状态")
	@Override
	public int updateAffective(Affective affective) {
		return affactiveDao.updateAffective(affective);
	}

	@Override
	public List<Affective> findAffectiveList(Affective affective) {
		return affactiveDao.findAffectiveList(affective);
	}

	@Override
	public Affective findAffective(Integer id, Integer operatorId) {
		return affactiveDao.findAffective(id, operatorId);
	}

	@Override
	public PageInfo<Affective> findPageInfoAffectiv(Affective affective,Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<Affective> list = affactiveDao.findAffectiveList(affective);
		logger.info(" semantic   -----   " + list);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<Affective>(list);
         } else {
            return null;
         }
	}

	
}
