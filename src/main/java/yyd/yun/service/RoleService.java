package yyd.yun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.OperatorVo;
import yyd.yun.beans.Role;

public interface RoleService {
	
	int insert(Role record);
	
	public List<Role> queryRoleByUserName(String username) ;
	
	//根据Id查询用户角色
	List<Role> queryRoleByUserId(@Param("id") Integer id);
	
	//查询所有
	public PageInfo<Role> selectRole(Integer pageNum);
	
	//查询所有
	public List<Role> selectRoleList();
	
	//新增用户角色关系表
	int insertRoleOperator(Integer roleId,Integer operatorId);
	
	//新增用户角色关系表
	int insertUserRole(Integer id,Integer roleId);
	
	//查询角色的权限
	List<OperatorVo> selectRolePermission(Integer roleId);
	
	//新增角色权限
	int insertRolePermission(Integer roleId,String authorizationId[]);
	
	public PageInfo<OperatorVo> findRolePermission(Integer pageNum);
}
