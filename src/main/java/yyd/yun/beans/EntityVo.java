package yyd.yun.beans;

import java.io.Serializable;

public class EntityVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer operatorId;
	
	private Integer roleId;
	
	private Integer permissionId;
	
	private String userName;
	
	private String roleName;
	
	private String permissionName;

	public EntityVo(Integer id, Integer operatorId, Integer roleId, Integer permissionId, String userName,
			String roleName, String permissionName) {
		super();
		this.id = id;
		this.operatorId = operatorId;
		this.roleId = roleId;
		this.permissionId = permissionId;
		this.userName = userName;
		this.roleName = roleName;
		this.permissionName = permissionName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Override
	public String toString() {
		return "EntityVo [id=" + id + ", operatorId=" + operatorId + ", roleId=" + roleId + ", permissionId="
				+ permissionId + ", userName=" + userName + ", roleName=" + roleName + ", permissionName="
				+ permissionName + "]";
	}

}
