package yyd.yun.beans;

import java.util.Date;
import java.util.List;

public class SemanticInfo extends Semantic{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String scene;
	
	private Integer sceneId;
	
	private String actionName;
	
	private Integer actionId;
	
	private String intent;
	
	private Integer intentId;
	
	
	public SemanticInfo(Integer id, String text, String answer, String semantic, Integer sceneId, Integer intentId,
			Integer operatorId, String sentiment, Integer similarityId, String applyAge, Date createDate,
			Date updateDate, List<SemanticVo> list, String scene, Integer sceneId2, String actionName, Integer actionId,
			String intent, Integer intentId2) {
		super(id, text, answer, semantic, sceneId, intentId, operatorId, sentiment, similarityId, applyAge, createDate,
				updateDate, list);
		this.scene = scene;
		sceneId = sceneId2;
		this.actionName = actionName;
		this.actionId = actionId;
		this.intent = intent;
		intentId = intentId2;
	}
	public SemanticInfo(){
		
	}
	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}
	
	public String getActionName() {
		return actionName;
	}
	
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	public Integer getActionId() {
		return actionId;
	}
	
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public Integer getIntentId() {
		return intentId;
	}
	public void setIntentId(Integer intentId) {
		this.intentId = intentId;
	}
	@Override
	public String toString() {
		return "SemanticInfo [scene=" + scene + ", sceneId=" + sceneId + ", actionName=" + actionName + ", actionId="
				+ actionId + "]";
	}
	
}
