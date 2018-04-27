package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;

public class Scene implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 场景ID 
	 */
	private Integer id;
	
	/**
	 * 场景
	 */
	private String scene;
	
	/**
	 * 英文场景
	 */
	private String sceneEnglish;
	
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 用户ID
	 */
	private Integer operatorId;

	
	
	public Scene(Integer id, String scene, String sceneEnglish, Date createDate, Integer status, Integer operatorId) {
		super();
		this.id = id;
		this.scene = scene;
		this.sceneEnglish = sceneEnglish;
		this.createDate = createDate;
		this.status = status;
		this.operatorId = operatorId;
	}

	public Scene() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getSceneEnglish() {
		return sceneEnglish;
	}

	public void setSceneEnglish(String sceneEnglish) {
		this.sceneEnglish = sceneEnglish;
	}

	@Override
	public String toString() {
		return "Scene [id=" + id + ", scene=" + scene + ", sceneEnglish=" + sceneEnglish + ", createDate=" + createDate
				+ ", status=" + status + ", operatorId=" + operatorId + "]";
	}

}
