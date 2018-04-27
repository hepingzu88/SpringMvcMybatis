package yyd.yun.beans;

import java.util.Date;

public class SceneVo extends Scene{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer intentId;
	
	private String intent;
	
	private String intentEnglish;
	
	private Integer entityId;
	
	private String entity;
	
	private String entityEnglish;

	public SceneVo(Integer id, String scene, String sceneEnglish, Date createDate, Integer status,
			Integer operatorId, Integer intentId, String intent, String intentEnglish, Integer entityId, String entity,
			String entityEnglish) {
		super(id, scene, sceneEnglish, createDate, status, operatorId);
		this.intentId = intentId;
		this.intent = intent;
		this.intentEnglish = intentEnglish;
		this.entityId = entityId;
		this.entity = entity;
		this.entityEnglish = entityEnglish;
	}

	public SceneVo() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIntentId() {
		return intentId;
	}

	public void setIntentId(Integer intentId) {
		this.intentId = intentId;
	}

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public String getIntentEnglish() {
		return intentEnglish;
	}

	public void setIntentEnglish(String intentEnglish) {
		this.intentEnglish = intentEnglish;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
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

	@Override
	public String toString() {
		return "SceneAndIntentVo [intentId=" + intentId + ", intent=" + intent + ", intentEnglish=" + intentEnglish
				+ ", entityId=" + entityId + ", entity=" + entity + ", entityEnglish=" + entityEnglish + "]";
	}

}
