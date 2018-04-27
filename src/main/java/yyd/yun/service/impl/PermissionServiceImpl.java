package yyd.yun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Permission;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.PermissionMapper;
import yyd.yun.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public int insert(Permission record){
		return permissionMapper.insert(record);
	}
	
	@Override
	public List<Permission> queryPermissionByUserName(String username) {
		return permissionMapper.queryPermissionByUserName(username);
	}

	@Override
	public List<Permission> selectPermission() {
		return permissionMapper.selectPermission();
	}

	@Override
	public int insertAuthorization(Integer id, Integer permissionId) {
		return permissionMapper.insertAuthorization(id, permissionId);
	}
	
	//删除权限关系
	@Override
	public int deleteByPermission(Integer roleId,Integer permissionId){
		return permissionMapper.deleteByPermission(roleId, permissionId);
	}
	
	@Override
	public Permission selectByPermission(Integer roleId,Integer permissionId){
		return permissionMapper.selectByPermission(roleId,permissionId);
	}

	@Override
	public PageInfo<Permission> selectPermissionPage(Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<Permission> list = permissionMapper.selectPermission();
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<Permission>(list);
         } else {
            return null;
         }
	}
}
