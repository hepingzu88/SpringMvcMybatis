package yyd.yun.beans;

import java.io.Serializable;

public class TbMusicSong implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String song;
    
	public TbMusicSong() {
		// TODO Auto-generated constructor stub
	}
	
    public TbMusicSong(Integer id, String song) {
		super();
		this.id = id;
		this.song = song;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song == null ? null : song.trim();
    }

	@Override
	public String toString() {
		return "TbMusicSong [id=" + id + ", song=" + song + "]";
	}
    
}