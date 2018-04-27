package yyd.yun.beans;

import java.io.Serializable;
import java.util.Date;

public class TbEduRes implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String year;

	private String level;

	private String subject;

	private Integer gradeId;

	private Integer semesterId;

	private Integer versionId;
	
	private String course;
	
	private Integer ageId;

	private String url;

	private Integer categoryId;

	public Integer isTagged;

	public TbEduRes(){}
	
	public TbEduRes(Integer id, String name, String year, String level, String subject, Integer gradeId,
			Integer semesterId, Integer versionId, String course, Integer ageId, String url, Integer categoryId,
			Integer isTagged) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.level = level;
		this.subject = subject;
		this.gradeId = gradeId;
		this.semesterId = semesterId;
		this.versionId = versionId;
		this.course = course;
		this.ageId = ageId;
		this.url = url;
		this.categoryId = categoryId;
		this.isTagged = isTagged;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level == null ? null : level.trim();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject == null ? null : subject.trim();
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(Integer semesterId) {
		this.semesterId = semesterId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getIsTagged() {
		return isTagged;
	}

	public void setIsTagged(Integer isTagged) {
		this.isTagged = isTagged;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Integer getAgeId() {
		return ageId;
	}

	public void setAgeId(Integer ageId) {
		this.ageId = ageId;
	}

	@Override
	public String toString() {
		return "TbEduRes [id=" + id + ", name=" + name + ", year=" + year + ", level=" + level + ", subject=" + subject
				+ ", gradeId=" + gradeId + ", semesterId=" + semesterId + ", versionId=" + versionId + ", course="
				+ course + ", ageId=" + ageId + ", url=" + url + ", categoryId=" + categoryId + ", isTagged=" + isTagged
				+ "]";
	}



	
}