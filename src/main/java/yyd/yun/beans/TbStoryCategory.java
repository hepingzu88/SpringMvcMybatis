package yyd.yun.beans;

import java.io.Serializable;

public class TbStoryCategory implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String category;
	public TbStoryCategory() {
		// TODO Auto-generated constructor stub
	}
    
    public TbStoryCategory(Integer id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

	@Override
	public String toString() {
		return "TbStoryCategory [id=" + id + ", category=" + category + "]";
	}

	
    
}