package yyd.yun.beans;

import java.io.Serializable;

public class TbStoryRes implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String url;

    private Integer albumId;

    private Integer storyCategoryId;

    private Integer ageId;

    private Integer categoryId;
    
    private Integer isTagged;
    
    public TbStoryRes() {
		
	}
    
	public TbStoryRes(Integer id, String name, String url, Integer albumId, Integer storyCategoryId, Integer ageId,
			Integer categoryId, Integer isTagged) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.albumId = albumId;
		this.storyCategoryId = storyCategoryId;
		this.ageId = ageId;
		this.categoryId = categoryId;
		this.isTagged = isTagged;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getStoryCategoryId() {
        return storyCategoryId;
    }

    public void setStoryCategoryId(Integer storyCategoryId) {
        this.storyCategoryId = storyCategoryId;
    }

    public Integer getAgeId() {
        return ageId;
    }

    public void setAgeId(Integer ageId) {
        this.ageId = ageId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

	public Integer getIsTagged() {
		return isTagged;
	}

	public void setIsTagged(Integer isTagged) {
		this.isTagged = isTagged;
	}

	@Override
	public String toString() {
		return "TbStoryRes [id=" + id + ", name=" + name + ", url=" + url + ", albumId=" + albumId
				+ ", storyCategoryId=" + storyCategoryId + ", ageId=" + ageId + ", categoryId=" + categoryId
				+ ", isTagged=" + isTagged + "]";
	}

	
}