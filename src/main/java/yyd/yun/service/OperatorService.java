package yyd.yun.service;



import java.util.List;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Operator;
import yyd.yun.beans.OperatorVo;
import yyd.yun.common.ResultModel;

public interface OperatorService {
	
	public Operator queryByUsername(String username);
	
	public int add(Operator operator);
	
	public PageInfo<OperatorVo> selectUserPermissionRole(Integer pageNum);
	
	public PageInfo<OperatorVo> selectUserPermission(Integer pageNum,Integer id);

	public Operator selectByPrimaryKey(Integer id);
	
	public ResultModel updateByPrimaryKeySelective(Integer id,String oldPassword,String newPassword);
	
	public int deleteByPrimaryKey(Integer id);
	
	int delRoleOperatorRelationship(Integer operatorId);
	
	PageInfo<OperatorVo> selectAllUserRole(Integer pageNum);
	
	int delRoleIdAndOperatorId(Integer operatorISd,Integer roleId);
	
	//selectUserRole
	List<OperatorVo> selectUserRole(OperatorVo vo);
}
