package yyd.yun.beans;

public class MusicResVo extends TbMusicRes{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String song;
	
	private String singer;
	
	public Integer state;
	
	public String category;

	public String age;
	
	public MusicResVo(String song, String singer, Integer state) {
		super();
		this.song = song;
		this.singer = singer;
		this.state = state;
	}

	public MusicResVo() {
		
	}

	public String getSong() {
		return song;
	}


	public void setSong(String song) {
		this.song = song;
	}


	public String getSinger() {
		return singer;
	}


	public void setSinger(String singer) {
		this.singer = singer;
	}


	public String getState() {
		return  (state == null||state == 0) ? "否" : "是";
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "MusicResVo [song=" + song + ", singer=" + singer + "]";
	}
	
	
	
}
