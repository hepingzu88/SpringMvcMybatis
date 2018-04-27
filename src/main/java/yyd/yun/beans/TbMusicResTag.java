package yyd.yun.beans;

import java.io.Serializable;

public class TbMusicResTag implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer musicResId;

    private Integer tagId;
    
    public TbMusicResTag() {
		// TODO Auto-generated constructor stub
	}

    public TbMusicResTag(Integer id, Integer musicResId, Integer tagId) {
		super();
		this.id = id;
		this.musicResId = musicResId;
		this.tagId = tagId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMusicResId() {
        return musicResId;
    }

    public void setMusicResId(Integer musicResId) {
        this.musicResId = musicResId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

	@Override
	public String toString() {
		return "TbMusicResTag [id=" + id + ", musicResId=" + musicResId + ", tagId=" + tagId + "]";
	}
    
    
}