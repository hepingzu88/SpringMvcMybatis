package yyd.yun.beans;


import java.io.Serializable;

public class Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String userName;
	
	private String reuqestIp;
	
	private String className;
	
	private String methodName;
	
	private String startTime;
	
	private String endTime;
	
	private String actionType;
	
	private String params;
	
	private String detail;
	
	private Integer state;

	public Log(Integer id, String userName, String reuqestIp, String className, String methodName,
			String startTime, String endTime,String actionType, String params, String detail, Integer state) {
		super();
		this.id = id;
		this.userName = userName;
		this.reuqestIp = reuqestIp;
		this.className = className;
		this.methodName = methodName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.actionType = actionType;
		this.params = params;
		this.detail = detail;
		this.state = state;
	}
	
	public Log() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReuqestIp() {
		return reuqestIp;
	}

	public void setReuqestIp(String reuqestIp) {
		this.reuqestIp = reuqestIp;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", userName=" + userName + ", reuqestIp=" + reuqestIp + ", className=" + className
				+ ", methodName=" + methodName + ", startTime=" + startTime + ", endTime=" + endTime + ", actionType="
				+ actionType + ", params=" + params + ", detail=" + detail + ", state=" + state + "]";
	}

	
}

