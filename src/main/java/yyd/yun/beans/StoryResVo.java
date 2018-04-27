package yyd.yun.beans;

public class StoryResVo extends TbStoryRes{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String album;
	
	private String category;
	
	private String age;
	
	private Integer state;

	public StoryResVo(String album, String category, String age,Integer state) {
		super();
		this.album = album;
		this.category = category;
		this.age = age;
		this.state = state;
	}
	
	public StoryResVo() {
		
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getState() {
		return (state == null || state == 0) ? "否":"是";
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "StoryResVo [album=" + album + ", category=" + category + ", state=" + state + ",age=" + age + "]";
	}
	
	
}
