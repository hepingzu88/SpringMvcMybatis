package yyd.yun.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.log.domain.Log;

public interface LogDao {

	public List<Log> queryAllLog() ;

	int queryVisitCount(@Param("startTime")String startTime, @Param("endTime")String endTime);
	
}
