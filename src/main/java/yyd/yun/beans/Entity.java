package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;

public class Entity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 行为ID
	 */
	private Integer id;
	
	/**
	 * 实体
	 */
	private String entity;
	
	/**
	 * scene_id
	 */
	private Integer sceneId;
	
	/**
	 * 实体英文名
	 */
	private String entityEnglish;
	
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
	
	public Entity(Integer id, String entity, Integer sceneId, String entityEnglish, Date createDate, Date updateDate,
			Integer operatorId) {
		super();
		this.id = id;
		this.entity = entity;
		this.sceneId = sceneId;
		this.entityEnglish = entityEnglish;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.operatorId = operatorId;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public Entity() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getEntityEnglish() {
		return entityEnglish;
	}

	public void setEntityEnglish(String entityEnglish) {
		this.entityEnglish = entityEnglish;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", entity=" + entity + ", sceneId=" + sceneId + ", entityEnglish=" + entityEnglish
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", operatorId=" + operatorId + "]";
	}

}
