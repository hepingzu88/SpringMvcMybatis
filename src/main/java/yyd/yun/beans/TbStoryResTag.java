package yyd.yun.beans;

import java.io.Serializable;

public class TbStoryResTag implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer storyResId;

    private Integer tagId;

    public TbStoryResTag() {
		// TODO Auto-generated constructor stub
	}
    
    
    public TbStoryResTag(Integer id, Integer storyResId, Integer tagId) {
		super();
		this.id = id;
		this.storyResId = storyResId;
		this.tagId = tagId;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoryResId() {
        return storyResId;
    }

    public void setStoryResId(Integer storyResId) {
        this.storyResId = storyResId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }


	@Override
	public String toString() {
		return "TbStoryResTag [id=" + id + ", storyResId=" + storyResId + ", tagId=" + tagId + "]";
	}
    
}