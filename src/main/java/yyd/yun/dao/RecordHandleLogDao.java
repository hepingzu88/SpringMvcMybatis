package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.RecordHandleLog;

public interface RecordHandleLogDao {

	public int addRecordHandleLog(RecordHandleLog log);
	
	public List<RecordHandleLog> selectRecordHandleLog(RecordHandleLog log);
	
	public int delete(@Param("arrys")String arrys []);
}
