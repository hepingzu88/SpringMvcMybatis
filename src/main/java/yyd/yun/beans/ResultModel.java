package yyd.yun.beans;

import java.io.Serializable;

public class ResultModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String message;
	
	private Boolean isSuccess = true;

	public ResultModel(){}
	
	public ResultModel(String message, Boolean isSuccess) {
		super();
		this.message = message;
		this.isSuccess = isSuccess;
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
}
