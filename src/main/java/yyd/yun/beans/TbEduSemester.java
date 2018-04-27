package yyd.yun.beans;

import java.io.Serializable;

public class TbEduSemester implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String semester;

    public TbEduSemester(){}
    
    
    
    public TbEduSemester(Integer id, String semester) {
		super();
		this.id = id;
		this.semester = semester;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester == null ? null : semester.trim();
    }



	@Override
	public String toString() {
		return "TbEduSemester [id=" + id + ", semester=" + semester + "]";
	}
    
    
}