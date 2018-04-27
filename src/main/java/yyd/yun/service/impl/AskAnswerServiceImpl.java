package yyd.yun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.CommentAnno;
import yyd.yun.beans.AskAnswer;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.AskAnswerDao;
import yyd.yun.service.AskAnswerService;

@Service
public class AskAnswerServiceImpl implements AskAnswerService{

	@Autowired
	private AskAnswerDao dao;
	
	@Override
	public PageInfo<AskAnswer> find(AskAnswer ask,Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<AskAnswer> list = dao.find(ask);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<AskAnswer>(list);
         } else {
            return null;
         }
	}

	@CommentAnno(operationType="新增问答操作:",operationName="新增问答")
	@Override
	public int addAskAnswer(AskAnswer ask) {
		return dao.addAskAnswer(ask);
	}

}
