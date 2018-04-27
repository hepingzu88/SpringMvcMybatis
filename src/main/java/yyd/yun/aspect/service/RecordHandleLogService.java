package yyd.yun.aspect.service;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.RecordHandleLog;

public interface RecordHandleLogService {

	public int addRecordHandleLog(RecordHandleLog log);
	
	public PageInfo<RecordHandleLog> selectRecordHandleLog(Integer page,RecordHandleLog log);
	
	public int delete(String arrys []);
}
