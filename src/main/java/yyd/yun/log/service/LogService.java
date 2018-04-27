package yyd.yun.log.service;


import com.github.pagehelper.PageInfo;

import yyd.yun.log.domain.Log;

public interface LogService {

	PageInfo<Log> queryAllLog(Integer pageNum);
	int queryVisitCount(String startTime,String endTime);
}
