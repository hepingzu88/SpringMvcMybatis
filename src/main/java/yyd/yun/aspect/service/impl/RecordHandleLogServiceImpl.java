package yyd.yun.aspect.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.service.RecordHandleLogService;
import yyd.yun.beans.Operator;
import yyd.yun.beans.RecordHandleLog;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.RecordHandleLogDao;



@Service
public class RecordHandleLogServiceImpl implements RecordHandleLogService{

	@Autowired
	private RecordHandleLogDao dao;
	
	@Override
	public int addRecordHandleLog(RecordHandleLog log) {
		return dao.addRecordHandleLog(log);
	}

	@Override
	public PageInfo<RecordHandleLog> selectRecordHandleLog(Integer page,RecordHandleLog log) {
		PageHelper.startPage(page, AdminConstant.ADMIN_PAGE_SIZE);
		Operator admin = (Operator) SecurityUtils.getSubject().getPrincipal();
		if(admin.getId().equals(1) || admin.getUsername().equals("admin")){
			log.setUserName(null);
		}else{
			log.setUserName(admin.getUsername());
		}
		List<RecordHandleLog> list = dao.selectRecordHandleLog(log);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<RecordHandleLog>(list);
         } else {
            return null;
         }
	}

	@Override
	public int delete(String[] arrys) {
		return dao.delete(arrys);
	}

}
