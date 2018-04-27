package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;

public class Intent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String intent;
	
	private String intentEnglish;
	
	private Integer operatorId;
	
	private Integer sceneId;
	
	private Date createDate;
	
	private Date updateDate;

	public Intent(Integer id, String intent, String intentEnglish, Integer operatorId, Integer sceneId, Date createDate,
			Date updateDate) {
		super();
		this.id = id;
		this.intent = intent;
		this.intentEnglish = intentEnglish;
		this.operatorId = operatorId;
		this.sceneId = sceneId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	public Intent() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
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
		return "Intent [id=" + id + ", intent=" + intent + ", intentEnglish=" + intentEnglish + ", operatorId="
				+ operatorId + ", sceneId=" + sceneId + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}

	
}
