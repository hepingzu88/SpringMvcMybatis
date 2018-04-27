package yyd.yun.beans;

public class OperatorVo extends Operator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleName;
	
	private Integer roleId;
	
	private String permission;
	
	private Integer permissionId;
	
	private String permissionName;
	
	private Integer operatorId;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Override
	public String toString() {
		return "OperatorVo [roleName=" + roleName + ", roleId=" + roleId + ", permission=" + permission
				+ ", permissionId=" + permissionId + ", permissionName=" + permissionName + ", operatorId=" + operatorId
				+ "]";
	}
}
