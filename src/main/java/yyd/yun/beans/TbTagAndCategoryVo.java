package yyd.yun.beans;

public class TbTagAndCategoryVo extends TbResCategory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer tagId;
	
	private String tagName;
	
	private String categoryId;

	public TbTagAndCategoryVo() {
		super();
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public TbTagAndCategoryVo(Integer tagId, String tagName, String categoryId) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "TbTagAndCategoryVo [tagId=" + tagId + ", tagName=" + tagName + ", categoryId=" + categoryId + "]";
	} 
	
	
}
