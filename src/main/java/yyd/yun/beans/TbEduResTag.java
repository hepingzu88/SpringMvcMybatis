package yyd.yun.beans;

import java.io.Serializable;

public class TbEduResTag implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer eduResId;

    private Integer tagId;
    
	public TbEduResTag() {
		// TODO Auto-generated constructor stub
	}
	
	
    public TbEduResTag(Integer id, Integer eduResId, Integer tagId) {
		super();
		this.id = id;
		this.eduResId = eduResId;
		this.tagId = tagId;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEduResId() {
        return eduResId;
    }

    public void setEduResId(Integer eduResId) {
        this.eduResId = eduResId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }


	@Override
	public String toString() {
		return "TbEduResTag [id=" + id + ", eduResId=" + eduResId + ", tagId=" + tagId + "]";
	}
    
}