package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;

public class AgeBand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer operatorId;
	
	private String ageBand;
	
	private Date createDate;
	
	private Date updateDate;
	
	public AgeBand() {
		// TODO Auto-generated constructor stub
	}

	public AgeBand(Integer id, Integer operatorId, String ageBand, Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.operatorId = operatorId;
		this.ageBand = ageBand;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "AgeBand [id=" + id + ", operatorId=" + operatorId + ", ageBand=" + ageBand + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getAgeBand() {
		return ageBand;
	}

	public void setAgeBand(String ageBand) {
		this.ageBand = ageBand;
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
	
	
}
