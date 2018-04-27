package yyd.yun.service.impl;

import java.util.List;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Operator;
import yyd.yun.beans.OperatorVo;
import yyd.yun.common.ResultModel;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.OperatorMapper;
import yyd.yun.dao.OperatorMapperVo;
import yyd.yun.service.OperatorService;

@Service
public class OperatorServiceImpl implements OperatorService{

	@Autowired
	private OperatorMapper operatorMapper;
	
	@Autowired
	private OperatorMapperVo operatorMapperVo;

	private static Logger logger = LoggerFactory.getLogger(OperatorService.class);
	
	public Operator queryByUsername(String username) {
		return operatorMapper.selectByUserName(username);
	}

	public ResultModel updateByPrimaryKeySelective(Integer id,String oldPassword,String newPasswordr){
		Operator admin = operatorMapper.selectByPrimaryKey(id);
		SimpleHash md5 = new SimpleHash("MD5",oldPassword,null,1);
    	if(!admin.getPassword().equals(md5.toHex())){
    		return new ResultModel("旧密码错误",false);
    	}
    	SimpleHash newMd5 = new SimpleHash("MD5",newPasswordr,null,1);
    	admin.setPassword(newMd5.toHex());
    	if(operatorMapper.updateByPrimaryKeySelective(admin) > 0){
    		return new ResultModel("修改成功",true);
    	}
    	return new ResultModel("修改失败",false);
	}
	
	
	public int add(Operator operator) {
		SimpleHash md5 = new SimpleHash("MD5",operator.getPassword(),null,1);
		operator.setPassword(md5.toHex());
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		operator.setSalt(randomNumberGenerator.nextBytes().toHex());
		return operatorMapper.insert(operator);
	}
	
	// 生成admin账号密码
	public static void main(String[] args) {
		Operator o1 = new Operator();
		o1.setUsername("user1");
		o1.setPassword("063f33a494dd0857f6a10e4602a2f406");
		o1.setSalt("fd3d3f6d48afc691b6bdff15cd914e9c");
		
		SimpleHash md5 = new SimpleHash("MD5","123456",null,1);
		System.out.println(md5.toHex());
		Operator o = new Operator();
		o.setUsername("user1");
		o.setPassword(md5.toHex());
		
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		o.setSalt(randomNumberGenerator.nextBytes().toHex());
		//e10adc3949ba59abbe56e057f20f883e
		//Operator [id=null, username=user1, password=e10adc3949ba59abbe56e057f20f883e, salt=a7e8a4ad06d4597c448f6502d44f7a83]
		//Operator [id=null, username=user1, password=063f33a494dd0857f6a10e4602a2f406, salt=fd3d3f6d48afc691b6bdff15cd914e9c]
		// 密码加密
		String newPassword = new SimpleHash(
				"md5", 
				o.getPassword(), 
				ByteSource.Util.bytes(o1.getSalt()),
				2).toHex();
		
		o.setPassword(newPassword);
		System.out.println(o);
	}

	@Override
	public PageInfo<OperatorVo> selectUserPermissionRole(Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<OperatorVo> list = operatorMapperVo.selectUserPermissionRole();
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<OperatorVo>(list);
         } else {
            return null;
         }
	}
	
	public PageInfo<OperatorVo> selectUserPermission(Integer pageNum,Integer id){
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<OperatorVo> list = operatorMapperVo.selectUserPermission(id);
		logger.info("Permission   vo" + list   +" id "+ id);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<OperatorVo>(list);
         } else {
            return null;
         }
	}

	@Override
	public Operator selectByPrimaryKey(Integer id) {
		return operatorMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return operatorMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int delRoleOperatorRelationship(Integer operatorId) {
		return operatorMapper.delRoleOperatorRelationship(operatorId);
	}

	@Override
	public PageInfo<OperatorVo> selectAllUserRole(Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<OperatorVo> list = operatorMapperVo.selectAllUserRole();
		logger.info("Permission   vo" + list);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<OperatorVo>(list);
         } else {
            return null;
         }
	}

	@Override
	public int delRoleIdAndOperatorId(Integer operatorId, Integer roleId) {
		return operatorMapper.delRoleIdAndOperatorId(operatorId, roleId);
	}

	@Override
	public List<OperatorVo> selectUserRole(OperatorVo vo) {
		return operatorMapperVo.selectUserRole(vo);
	}
}
