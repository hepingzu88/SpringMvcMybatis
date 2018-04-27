package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;

public class Action implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 行为ID
	 */
	private Integer id;
	
	/**
	 * 行为
	 */
	private String action;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 修改时间
	 */
	private Date updateDate;
	
	/**
	 * 用户ID
	 */
	private Integer operatorId;

	
	public Action(Integer id, String action, Date createDate, Date updateDate, Integer operatorId) {
		super();
		this.id = id;
		this.action = action;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.operatorId = operatorId;
	}

	public Action() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", action=" + action + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", operatorId=" + operatorId + "]";
	}

}
