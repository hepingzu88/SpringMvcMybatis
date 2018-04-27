package yyd.yun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Permission;

public interface PermissionService {
	
	int insert(Permission record);
	
	public List<Permission> queryPermissionByUserName(String username);
	
	List<Permission> selectPermission();
	
	PageInfo<Permission> selectPermissionPage(Integer pageNum);
	
	//新增授权关系
	int insertAuthorization(@Param("id")Integer id,@Param("permissionId")Integer permissionId);
	
	//删除权限关系
    int deleteByPermission(Integer roleId,Integer permissionId);
    
    Permission selectByPermission(Integer roleId,Integer permissionId);
}
