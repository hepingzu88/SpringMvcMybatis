package yyd.yun.beans;

import java.io.Serializable;

public class TbMusicSinger implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String singer;

    public TbMusicSinger() {
		// TODO Auto-generated constructor stub
	}
    
    public TbMusicSinger(Integer id, String singer) {
		super();
		this.id = id;
		this.singer = singer;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer == null ? null : singer.trim();
    }

	@Override
	public String toString() {
		return "TbMusicSinger [id=" + id + ", singer=" + singer + "]";
	}
    
}