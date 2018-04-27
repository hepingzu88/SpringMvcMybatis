package yyd.yun.beans;

import java.io.Serializable;

public class TbResCategory implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String category;

    private Integer operatorId;

    private Integer sort;

    private String intro;
    
	public TbResCategory() {
		// TODO Auto-generated constructor stub
	}
	
    public TbResCategory(Integer id, String category, Integer operatorId, Integer sort, String intro) {
		super();
		this.id = id;
		this.category = category;
		this.operatorId = operatorId;
		this.sort = sort;
		this.intro = intro;
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

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

	@Override
	public String toString() {
		return "TbResCategory [id=" + id + ", category=" + category + ", operatorId=" + operatorId + ", sort=" + sort
				+ ", intro=" + intro + "]";
	}
    
}