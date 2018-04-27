package yyd.yun.beans;

import java.io.Serializable;

public class TbTag  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String tagName;

    private Integer categoryId;

    private String intro;
    
    public TbTag() {
	}
    
    
    public TbTag(Integer id, String tagName, Integer categoryId, String intro) {
		super();
		this.id = id;
		this.tagName = tagName;
		this.categoryId = categoryId;
		this.intro = intro;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }


	@Override
	public String toString() {
		return "TbTag [id=" + id + ", tagName=" + tagName + ", categoryId=" + categoryId + ", intro=" + intro + "]";
	}
    
}