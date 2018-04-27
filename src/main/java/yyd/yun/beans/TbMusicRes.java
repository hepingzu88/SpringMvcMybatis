package yyd.yun.beans;

import java.io.Serializable;

public class TbMusicRes implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer songId;

    private Integer singerId;

    private String url;

    private Integer musicCategoryId;

    private Integer ageId;

    private Integer categoryId;
    
    public Integer isTagged;

    public TbMusicRes() {
	}
   
	public TbMusicRes(Integer id, Integer songId, Integer singerId, String url, Integer musicCategoryId, Integer ageId,
			Integer categoryId, Integer isTagged) {
		super();
		this.id = id;
		this.songId = songId;
		this.singerId = singerId;
		this.url = url;
		this.musicCategoryId = musicCategoryId;
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

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getMusicCategoryId() {
        return musicCategoryId;
    }

    public void setMusicCategoryId(Integer musicCategoryId) {
        this.musicCategoryId = musicCategoryId;
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
		return "TbMusicRes [id=" + id + ", songId=" + songId + ", singerId=" + singerId + ", url=" + url
				+ ", musicCategoryId=" + musicCategoryId + ", ageId=" + ageId + ", categoryId=" + categoryId + "]";
	}
    
}