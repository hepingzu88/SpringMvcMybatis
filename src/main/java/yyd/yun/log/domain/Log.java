package yyd.yun.log.domain;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一i值 
	 */
	private Integer i;
	
	/**
	 * 时间
	 */
	private Date time;
	
	/**
	 * rtype
	 */
	private String rtype;
	
	/**
	 * id
	 */
	private Long id;

	/**
	 * text
	 */
	private String text;
	
	/**
	 * text_url
	 */
	private String textUrl;
	
	/**
	 * intent意图
	 */
	private String intent;
	
	/**
	 * service
	 */
	private String service;
	
	/**
	 * service_from
	 */
	private String serviceFrom;
	
	/**
	 * operation
	 */
	private String operation;
	
	/**
	 * 
	 */
	private String moreResults;
	
	/**
	 * 
	 */
	 private String  answer;
	 
	 /**
	  * 
	  */
	 private String semantic;
	 
	 /**
	  * 
	  */
	 private String version;
	 
	 /**
	  * 
	  */
	 private Integer wrong;
	 
	 /**
	  * 
	  */
	 private String reply;

	 public Log(){}

	public Log(Integer i, Date time, String rtype, Long id, String text, String textUrl, String intent, String service,
			String serviceFrom, String operation, String moreResults, String answer, String semantic, String version,
			Integer wrong, String reply) {
		super();
		this.i = i;
		this.time = time;
		this.rtype = rtype;
		this.id = id;
		this.text = text;
		this.textUrl = textUrl;
		this.intent = intent;
		this.service = service;
		this.serviceFrom = serviceFrom;
		this.operation = operation;
		this.moreResults = moreResults;
		this.answer = answer;
		this.semantic = semantic;
		this.version = version;
		this.wrong = wrong;
		this.reply = reply;
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRtype() {
		return rtype;
	}


	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextUrl() {
		return textUrl;
	}

	public void setTextUrl(String textUrl) {
		this.textUrl = textUrl;
	}

	public String getIntent() {
		return intent;
	}


	public void setIntent(String intent) {
		this.intent = intent;
	}


	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getServiceFrom() {
		return serviceFrom;
	}

	public void setServiceFrom(String serviceFrom) {
		this.serviceFrom = serviceFrom;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMoreResults() {
		return moreResults;
	}

	public void setMoreResults(String moreResults) {
		this.moreResults = moreResults;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getWrong() {
		return wrong;
	}

	public void setWrong(Integer wrong) {
		this.wrong = wrong;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Log [i=" + i + ", time=" + time + ", id=" + id + ", text=" + text + ", textUrl=" + textUrl
				+ ", service=" + service + ", serviceFrom=" + serviceFrom + ", operation=" + operation
				+ ", moreResults=" + moreResults + ", answer=" + answer + ", semantic=" + semantic + ", version="
				+ version + ", wrong=" + wrong + ", reply=" + reply + "]";
	}

	
}
