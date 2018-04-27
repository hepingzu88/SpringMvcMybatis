package yyd.yun.beans;

import java.io.Serializable;

public class TbEduVersion implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String version;
	
	public TbEduVersion() {
		// TODO Auto-generated constructor stub
	}
    
	public TbEduVersion(Integer id, String version) {
		super();
		this.id = id;
		this.version = version;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

	@Override
	public String toString() {
		return "TbEduVersion [id=" + id + ", version=" + version + "]";
	}
    
}