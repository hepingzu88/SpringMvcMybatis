package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.OperatorVo;

public interface OperatorMapperVo {

	//查询所有用户
	public List<OperatorVo> selectUserPermissionRole();
	
	//查询用户权限
	public List<OperatorVo> selectUserPermission(@Param("id")Integer id);
	
	//查询角色权限
	List<OperatorVo> selectRolePermission(OperatorVo vo);
	
	//查询角色全部权限
	List<OperatorVo> findRolePermission();
	
	//selectUserRole
	List<OperatorVo> selectUserRole(OperatorVo vo);
	
	List<OperatorVo> selectAllUserRole();
}
