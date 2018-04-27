package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> queryPermissionByUserName(@Param("username") String username);
    
    List<Permission> selectPermission();
    
    
    //新增授权关系
    int insertAuthorization(@Param("id")Integer id,@Param("permissionId")Integer permissionId);
    
    //删除权限关系
    int deleteByPermission(@Param("roleId")Integer roleId,@Param("permissionId")Integer permissionId);
    
    Permission selectByPermission(@Param("roleId")Integer roleId,@Param("permissionId")Integer permissionId);
}