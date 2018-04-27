package yyd.yun.beans;

import java.io.Serializable;

public class AskAnswer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 问答ID
	 */
	private Integer id;
	
	/**
	 * 问
	 */
	private String question;
	
	/**
	 * 回答
	 */
	private String answer;
	
	/**
	 * 同义词
	 */
	private String synonymous;
	
	/**
	 * 用户ID
	 */
	private Integer operatorId;
	
	/**
	 * 情景ID
	 */
	private Integer sceneId;
	
	/**
	 * 行为ID
	 */
	private Integer actionId; 
	
	/**
	 * 标注
	 */
	private String tag;

	public AskAnswer(Integer id, String question, String answer, String synonymous, Integer operatorId, Integer sceneId,
			Integer actionId, String tag) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.synonymous = synonymous;
		this.operatorId = operatorId;
		this.sceneId = sceneId;
		this.actionId = actionId;
		this.tag = tag;
	}
	
	public AskAnswer() {
	
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSynonymous() {
		return synonymous;
	}

	public void setSynonymous(String synonymous) {
		this.synonymous = synonymous;
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

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "AskAnswer [id=" + id + ", question=" + question + ", answer=" + answer + ", synonymous=" + synonymous
				+ ", operatorId=" + operatorId + ", sceneId=" + sceneId + ", actionId=" + actionId + ", tag=" + tag
				+ "]";
	}
	
}
