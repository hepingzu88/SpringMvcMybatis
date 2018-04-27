package yyd.yun.beans;

import java.io.Serializable;

public class TbAge implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String age;

    public TbAge() {
		// TODO Auto-generated constructor stub
	}
    
    
    
    public TbAge(Integer id, String age) {
		super();
		this.id = id;
		this.age = age;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }



	@Override
	public String toString() {
		return "TbAge [id=" + id + ", age=" + age + "]";
	}
    
    
    
}