package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;

public class Apply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 应用Id
	 */
	private Integer id;
	
	/**
	 * 应用名称
	 */
	private String applyName;
	
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
	
	/**
	 * 场景ID
	 */
	private String sceneId;
	
	
	/**
	 * 公开场景ID
	 */
	private String publicSceneId;
	
	/**
	 * 应用key
	 */
	private String applyKey;
	
	/**
	 * 应用类型
	 */
	private String applyClass;
	
	/**
	 * 应用描述
	 */
	private String depict;
	
	/**
	 * 应用平台
	 */
	private String platform;

	public Apply(Integer id, String applyName, Date createDate, Date updateDate, Integer operatorId, String sceneId,
			String publicSceneId,String applyKey, String applyClass, String depict, String platform) {
		super();
		this.id = id;
		this.applyName = applyName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.operatorId = operatorId;
		this.sceneId = sceneId;
		this.applyKey = applyKey;
		this.applyClass = applyClass;
		this.depict = depict;
		this.platform = platform;
		this.publicSceneId = publicSceneId;
	}
	
	public Apply() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
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

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getPublicSceneId() {
		return publicSceneId;
	}

	public void setPublicSceneId(String publicSceneId) {
		this.publicSceneId = publicSceneId;
	}

	public String getApplyKey() {
		return applyKey;
	}

	public void setApplyKey(String applyKey) {
		this.applyKey = applyKey;
	}

	public String getApplyClass() {
		return applyClass;
	}

	public void setApplyClass(String applyClass) {
		this.applyClass = applyClass;
	}

	public String getDepict() {
		return depict;
	}

	public void setDepict(String depict) {
		this.depict = depict;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "Apply [id=" + id + ", applyName=" + applyName + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", operatorId=" + operatorId + ", sceneId=" + sceneId + ", publicSceneId="
				+ publicSceneId + ", applyKey=" + applyKey + ", applyClass=" + applyClass + ", depict=" + depict
				+ ", platform=" + platform + "]";
	}

	
}
