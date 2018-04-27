package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Semantic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Integer id;
	
	private String text;
	
	private String answer;
	
	private String semantic;

	private Integer sceneId;
	
	private Integer intentId;
	
	private Integer operatorId;
	
	private String sentiment;
	
	private Integer similarityId;
	
	private String applyAge;
	
	private Date createDate;
	
	private Date updateDate;
	
	private List<SemanticVo> list;

	public Semantic(Integer id, String text, String answer, String semantic, Integer sceneId, Integer intentId,
			Integer operatorId, String sentiment, Integer similarityId, String applyAge, Date createDate,
			Date updateDate, List<SemanticVo> list) {
		super();
		this.id = id;
		this.text = text;
		this.answer = answer;
		this.semantic = semantic;
		this.sceneId = sceneId;
		this.intentId = intentId;
		this.operatorId = operatorId;
		this.sentiment = sentiment;
		this.similarityId = similarityId;
		this.applyAge = applyAge;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.list = list;
	}

	public Semantic(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSemantic() {
		return semantic;
	}

	public void setSemantic(String semantic) {
		this.semantic = semantic;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public Integer getIntentId() {
		return intentId;
	}

	public void setIntentId(Integer intentId) {
		this.intentId = intentId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public Integer getSimilarityId() {
		return similarityId;
	}

	public void setSimilarityId(Integer similarityId) {
		this.similarityId = similarityId;
	}

	public String getApplyAge() {
		return applyAge;
	}

	public void setApplyAge(String applyAge) {
		this.applyAge = applyAge;
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

	public List<SemanticVo> getList() {
		return list;
	}

	public void setList(List<SemanticVo> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Semantic [id=" + id + ", text=" + text + ", answer=" + answer + ", semantic=" + semantic + ", sceneId="
				+ sceneId + ", intentId=" + intentId + ", operatorId=" + operatorId + ", sentiment=" + sentiment
				+ ", similarityId=" + similarityId + ", applyAge=" + applyAge + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", list=" + list + "]";
	}

}
