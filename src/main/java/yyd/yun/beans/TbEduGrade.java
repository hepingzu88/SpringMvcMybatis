package yyd.yun.beans;

import java.io.Serializable;

public class TbEduGrade implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String grade;

    public TbEduGrade() {
		// TODO Auto-generated constructor stub
	}
    
    public TbEduGrade(Integer id, String grade) {
		super();
		this.id = id;
		this.grade = grade;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

	@Override
	public String toString() {
		return "TbEduGrade [id=" + id + ", grade=" + grade + "]";
	}
    
}