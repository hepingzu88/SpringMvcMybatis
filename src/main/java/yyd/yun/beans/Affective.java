package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;


public class Affective implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 情感状态ID
	 */
	private Integer id;
	
	/**
	 * 情感名称
	 */
	private String affectiveName;
	
	/**
	 * 用户ID
	 */
	private Integer operatorId;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 修改时间
	 */
	private Date updateDate;

	
	public Affective(Integer id, String affectiveName, Integer operatorId, Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.affectiveName = affectiveName;
		this.operatorId = operatorId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Affective(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAffectiveName() {
		return affectiveName;
	}

	public void setAffectiveName(String affectiveName) {
		this.affectiveName = affectiveName;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
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

	@Override
	public String toString() {
		return "Affective [id=" + id + ", affectiveName=" + affectiveName + ", operatorId=" + operatorId
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
}
