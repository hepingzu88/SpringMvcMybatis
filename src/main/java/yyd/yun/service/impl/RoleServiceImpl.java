package yyd.yun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.beans.OperatorVo;
import yyd.yun.beans.Role;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.OperatorMapperVo;
import yyd.yun.dao.RoleMapper;
import yyd.yun.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private OperatorMapperVo operatorMapperVo;
	
	@Override
	public int insert(Role record){
		return roleMapper.insert(record);
	}
	
	@Override
	public List<Role> queryRoleByUserName(String username) {
		return roleMapper.queryRoleByUserName(username);
	}

	@Override
	public List<Role> queryRoleByUserId(Integer id) {
		return roleMapper.queryRoleByUserId(id);
	}

	//查询所有
	@Override
	public List<Role> selectRoleList(){
		return roleMapper.selectRole();
	}
	
	@Override
	public PageInfo<Role> selectRole(Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<Role> list = roleMapper.selectRole();
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<Role>(list);
         } else {
            return null;
         }
	}
	
	//新增用户角色关系表
	@Override
	public int insertRoleOperator(Integer roleId,Integer operatorId){
		return roleMapper.insertRoleOperator(roleId, operatorId);
	}
	
	//查询角色的权限
	public List<OperatorVo> selectRolePermission(Integer roleId){
		OperatorVo vo = new OperatorVo();
		vo.setRoleId(roleId);
		List<OperatorVo> list = operatorMapperVo.selectRolePermission(vo);
		if(list.isEmpty()){
			return null;
		}
		return list;
	}
	
	//新增角色权限
	public int insertRolePermission(Integer roleId,String authorizationId[]){
		int index = -1;
		List<OperatorVo> list = null;
		OperatorVo vo = null;
		for (int i = 0; i < authorizationId.length; i++) {
			vo = new OperatorVo();
			vo.setRoleId(roleId);
			vo.setPermissionId(Integer.valueOf(authorizationId[i]));
			list =  operatorMapperVo.selectRolePermission(vo);
			if(list.isEmpty()){
				index = roleMapper.insertRolePermission(roleId, Integer.valueOf(authorizationId[i]));
			}
		}
		return index;
	}

	@Override
	public PageInfo<OperatorVo> findRolePermission(Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<OperatorVo> list = operatorMapperVo.findRolePermission();
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<OperatorVo>(list);
         } else {
            return null;
         }
	}
	
	@Override
	public int insertUserRole(Integer id,Integer roleId){
		/*int index = -1;
		List<OperatorVo> list = null;
		OperatorVo vo = null;
		for (int i = 0; i < roleIdList.length; i++) {
			vo = new OperatorVo();
			vo.setRoleId(Integer.valueOf(roleIdList[i]));
			vo.setOperatorId(id);
			list =  operatorMapperVo.selectUserRole(vo);
			if(list.isEmpty()){
				index = roleMapper.insertRoleOperator(Integer.valueOf(roleIdList[i]),id);
			}
		}
		return index;*/
		return roleMapper.insertRoleOperator(roleId,id);
	}
}
