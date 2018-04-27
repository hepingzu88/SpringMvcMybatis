package yyd.yun.beans;

import java.io.Serializable;

public class SemanticVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String start;
	
	private String end;
	
	private String value;
	
	private String entity;
	
	public SemanticVo(String start, String end, String value, String entity) {
		super();
		this.start = start;
		this.end = end;
		this.value = value;
		this.entity = entity;
	}
	
	public SemanticVo() {
		
	}
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "SemanticVo [start=" + start + ", end=" + end + ", value=" + value + ", entity=" + entity + "]";
	}
	
	

}
