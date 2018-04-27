package yyd.yun.common;

import java.io.Serializable;
import java.util.List;

public class ResultModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String message;
	
	private Boolean isSuccess = true;
	
	private List<Object> list;	

	public ResultModel(){}

	public ResultModel(String message, Boolean isSuccess) {
		super();
		this.message = message;
		this.isSuccess = isSuccess;
	}
	
	public ResultModel(String message, Boolean isSuccess, List<Object> list) {
		super();
		this.message = message;
		this.isSuccess = isSuccess;
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
	
}
