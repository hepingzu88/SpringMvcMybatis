package yyd.yun.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import yyd.yun.beans.Operator;
import yyd.yun.beans.Permission;
import yyd.yun.beans.Role;
import yyd.yun.service.OperatorService;
import yyd.yun.service.PermissionService;
import yyd.yun.service.RoleService;

/**
 * Created by Administrator on 2017/4/29 0029.
 */
public class AdminAuthorizingRealm extends AuthorizingRealm {

  //  @Autowired
   // private IAdminService adminService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;

    /**
     * 登录认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
       // Admin admin = adminService.findByAdminName(token.getUsername());
        Operator operator = operatorService.queryByUsername(token.getUsername());
        
        
        if(operator!=null){
        	/*return new SimpleAuthenticationInfo(
     				operator,                      // 登录名|登录对象
     				operator.getPassword(),                      // 密码
     				ByteSource.Util.bytes(operator.getSalt()),   // 盐值
     				getName()                                    // realm name
     		);*/
           return new SimpleAuthenticationInfo(
            		operator,
            		operator.getPassword(),
            		getName());
        }else
            throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 通过 principal 获取用户名 ; principal翻译过来为当事人 (用户名或其他信息保存在这个对象中，当然可以自定义)
    	//Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
    	
    	// 创建授权信息对象,并设置角色与权限
    	//SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    	// 通过 principal 获取用户名 ; principal翻译过来为当事人 (用户名或其他信息保存在这个对象中，当然可以自定义)
    	Operator admin = (Operator)principalCollection.getPrimaryPrincipal();
		
		// 创建授权信息对象,并设置角色与权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		// 查询用户的角色，并且转成Set<String> ;  将List<Role> -> Set<String>
		List<Role> roleList = roleService.queryRoleByUserName(admin.getUsername());
		Set<String> roleNameSet = new HashSet<>();
		for(Role r: roleList) {
			roleNameSet.add(r.getRoleName());
		}
		
		// 查询用户的权限，并且转成Set<String> ;  将List<Permission> -> Set<String>
		List<Permission> pmsList = permissionService.queryPermissionByUserName(admin.getUsername());
		Set<org.apache.shiro.authz.Permission> permissionSet = new HashSet<>();
		for(Permission p : pmsList) {
			permissionSet.add(new WildcardPermission(p.getPermission()));
		}
		
		// 将用户角色，权限 放入授权信息，提供shiro使用
		authorizationInfo.setRoles(roleNameSet);
		authorizationInfo.setObjectPermissions(permissionSet);
		
		return authorizationInfo;
    	
    }



}
