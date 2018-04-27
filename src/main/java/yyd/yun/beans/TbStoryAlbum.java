package yyd.yun.beans;

import java.io.Serializable;

public class TbStoryAlbum implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String album;

    public TbStoryAlbum(Integer id, String album) {
		super();
		this.id = id;
		this.album = album;
	}
	public TbStoryAlbum() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album == null ? null : album.trim();
    }
	@Override
	public String toString() {
		return "TbStoryAlbum [id=" + id + ", album=" + album + "]";
	}
    
}