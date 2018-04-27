package yyd.yun.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.constants.AdminConstant;
import yyd.yun.log.dao.LogDao;
import yyd.yun.log.domain.Log;
import yyd.yun.log.service.LogService;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private LogDao dao;
	
	@Override
	public PageInfo<Log> queryAllLog(Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
        List<Log> list = dao.queryAllLog();
        if (list != null && list.size() > 0) {
            return new PageInfo<Log>(list);
        } else {
            return null;
        }
	}

	@Override
	public int queryVisitCount(String startTime, String endTime) {
		// TODO Auto-generated method stub
		
		return dao.queryVisitCount(startTime,endTime);
		
	}

}
