package yyd.yun.beans;

public class EducationVo extends TbEduRes{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String semester;
	
	private String version;
	
	private String grade;
	
	public Integer state;

	public EducationVo(String semester, String version, String grade, Integer state) {
		super();
		this.semester = semester;
		this.version = version;
		this.grade = grade;
		this.state = state;
	}

	public EducationVo(){}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getState() {
		return (state == null || state == 0) ? "否":"是" ;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "EducationVo [semester=" + semester + ", version=" + version + ", grade=" + grade + ", state=" + state
				+ "]";
	}
	
	
}

