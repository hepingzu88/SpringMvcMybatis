package yyd.yun.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.LoginReturnInfo;
import yyd.yun.beans.Operator;
import yyd.yun.beans.OperatorVo;
import yyd.yun.beans.Permission;
import yyd.yun.beans.Role;
import yyd.yun.common.ResultModel;
import yyd.yun.service.OperatorService;
import yyd.yun.service.PermissionService;
import yyd.yun.service.RoleService;

/**
 * Created by fulaizhi on 2017/4/22.
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private PermissionService permissionService;
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
    	if(SecurityUtils.getSubject().isAuthenticated()){
    		return "index";
    	}
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request,Model model){
        Object failure = request.getAttribute("loginFailure");
        if(failure!=null){
            LoginReturnInfo errorInfo = LoginReturnInfo.getInstance((String)failure);
            model.addAttribute("errorMsg",errorInfo!=null ? errorInfo.get() : "未知错误！");
            return "login";
        }
        return "index";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    
    
    @RequestMapping(value="/user/list",method=RequestMethod.GET)
    public String userListIndex(){
    	return "user/user-list-index";
    }
    
    @RequestMapping(value="/user/list",method=RequestMethod.POST)
    public String userList(Integer pageNum,ModelMap modelMap){
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<OperatorVo> page = operatorService.selectUserPermissionRole(pageNum);
		modelMap.put("page", page);
    	return "user/user-list";
    }
    
    @RequestMapping(value="/info",method=RequestMethod.GET)
    public String userInfo(ModelMap map){
    	 Operator admin = (Operator)SecurityUtils.getSubject().getPrincipal();
    	 Operator operator = operatorService.selectByPrimaryKey(admin.getId());
    	 map.put("user", operator);
    	return "user/user-info";
    }
    
    
    @RequestMapping(value="/user/updatePassword",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel updatePassword(Integer id,String oldPassword,String newPassword){
    	return  operatorService.updateByPrimaryKeySelective(id,oldPassword,newPassword);
    }
    
    
    @RequestMapping(value="/user-add",method=RequestMethod.GET)
    public String userAdd(ModelMap map){
    	List<Role> list = roleService.selectRoleList();
    	map.put("list",list);
    	return "user/user-add";
    }
    
    @RequestMapping(value="/user-add",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel addUser(Integer roleId,String username,String password,Operator operator){
    	Operator oper = operatorService.queryByUsername(username);
    	if(oper != null){
    		return new ResultModel("用户名已存在",false);
    	}
    	operator.setUsername(username);
    	operator.setPassword(password);
    	if(operatorService.add(operator) < 0){
    		return new ResultModel("新增用户失败",false);
    	}
    	if(roleService.insertRoleOperator(roleId,operator.getId()) < 0){
    		return new ResultModel("新增用户角色失败",false);
    	}
    	return new ResultModel("新增用户成功",true);
    }
    
    //用户列表页面
    @RequestMapping(value="/user/edit",method=RequestMethod.GET)
    public String userEdit(Integer id,Integer roleId,ModelMap map){
    	map.put("id", id);
    	map.put("roleId", roleId);
    	return "user/user-edit";
    }
    
    //用户列表
    @RequestMapping(value="/user/edit",method=RequestMethod.POST)
    public String userEditPost(Integer id,Integer pageNum,ModelMap modelMap){
    	if(pageNum==null||pageNum<1){
            pageNum=1;
        }
    	
		PageInfo<OperatorVo> page = operatorService.selectUserPermission(pageNum, id);
		modelMap.put("page", page);
    	return "user/user-edit-list";
    }
    
    //删除用户
    @RequestMapping(value="/user/delUser",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel delUser(Integer id){
    	//首先根据Id删除用户  然后删除用户角色关联数据pms_role_operator
    	int index = -1;
    	if(operatorService.deleteByPrimaryKey(id) > 0){
    		index = operatorService.delRoleOperatorRelationship(id);
    	}
    	if(index > 0){
    		return new ResultModel("删除用户成功",true); 
    	}
    	return new ResultModel("删除用户失败",false); 
    }
    
    
    //授权页面
    @RequestMapping(value="/user/authorization",method=RequestMethod.GET)
    public String userAuthorization(Integer id,ModelMap map){
    	List<Permission> list = permissionService.selectPermission();
    	map.put("list",list);
    	map.put("id",id);
    	return "user/user-authorization";
    }
    
    // 查询用户权限
    @RequestMapping(value="/user/findRolePermission",method=RequestMethod.POST)
    @ResponseBody
    public List<OperatorVo> queryRolePermission(Integer roleId,ModelMap map){
    	List<OperatorVo> lsit = roleService.selectRolePermission(roleId);
    	return lsit;
    }
    
    //用户授权
    @RequestMapping(value="/user/authorization",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel userAuthorizationPost(Integer id,String authorizationId[],ModelMap map){
    	if(authorizationId.length == 0 || "".equals(id)){
    		return new ResultModel("请选择权限授权",false);
    	}
    	if(roleService.insertRolePermission(id,authorizationId) > 0){
    		return new ResultModel("授权成功",true);
    	}
    	return new ResultModel("权限已存在",false);
    }
    
    //删除权限
    @RequestMapping(value="/user/delPermission",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel delUserPermission(Integer id,Integer permissionId){
    	if("".equals(id) || "".equals(permissionId)){
    		return new ResultModel("删除失败",false);
    	}
    	List<Role> list = roleService.queryRoleByUserId(id);
    	if(permissionService.deleteByPermission(list.get(0).getId(), permissionId) > 0){
    		return new ResultModel("删除成功",true);
    	}
    	return new ResultModel("删除失败",false);
    }
     
    //新增角色
    @RequestMapping(value="/user/addRole",method=RequestMethod.GET)
    public String addRole(){
    	return "user/role/user-role-add";
    }
    
    //新增角色
    @RequestMapping(value="/user/addRole",method=RequestMethod.POST) 
    @ResponseBody
    public ResultModel addRoleEntity(Role role){
    	if(roleService.insert(role) > 0){
    		return new ResultModel("新增成功",true);
    	}
    	return new ResultModel("新增失败",false);
    }
    
    //用户角色
    @RequestMapping(value="/user/role",method=RequestMethod.GET)
    public String userRoleIndex(){
    	return "user/role/user-role-index";
    }
    
    
    @RequestMapping(value="/user/role",method=RequestMethod.POST)
    public String userRoleList(Integer pageNum,ModelMap modelMap){
    	if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<OperatorVo> page = roleService.findRolePermission(pageNum);
		modelMap.put("page", page);
    	return "user/role/user-role-list";
    }
    
    //用户角色LIST
    @RequestMapping(value="/userRoleIndex",method=RequestMethod.GET)
    public String userRoleIndexShow(){
    	return "user/userRole/role-index";
    }
    
   //用户角色LIST
    @RequestMapping(value="/userRoleIndex",method=RequestMethod.POST)
    public String userRoleIndexList(Integer pageNum,ModelMap modelMap){
    	if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<OperatorVo> page = operatorService.selectAllUserRole(pageNum);
		modelMap.put("page", page);
    	return "user/userRole/role-list";
    }
    
    
    //权限
    @RequestMapping(value="/permissionIndex",method=RequestMethod.GET)
    public String permissionIndex(){
    	return "user/permission/permission-index";
    }
    
    @RequestMapping(value="/permissionIndex",method=RequestMethod.POST)
    public String permissionList(Integer pageNum,ModelMap modelMap){
    	if(pageNum==null||pageNum<1){
            pageNum=1;
        }
		PageInfo<Permission> page = permissionService.selectPermissionPage(pageNum);
		modelMap.put("page", page);
    	return "user/permission/permission-list";
    }
    
    @RequestMapping(value="/permission/add",method=RequestMethod.GET)
    public String permissionAddIndex(){
    	return "user/permission/permission-add";
    }
    
    @RequestMapping(value="/permission/add",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel permissionAdd(Permission permission){
    	if(permissionService.insert(permission) > 0){
    		return new ResultModel("新增成功",true);
    	}
    	return new ResultModel("新增失败",false);
    }
    
    
    //删除用户权限
    @RequestMapping(value="/user/delUserRole",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel delUserRole(Integer id,Integer roleId){
    	if(operatorService.delRoleIdAndOperatorId(id, roleId)>0){
    		return new ResultModel("删除成功",true);
    	}
    	return new ResultModel("删除失败",false);
    }
    
    
    @RequestMapping(value="/user/resetRole",method=RequestMethod.GET)
    public String userResetRole(Integer id,ModelMap map){
    	List<Role> list = roleService.selectRoleList();
    	map.put("list", list);
    	map.put("id",id);
    	return "user/user-reset-role";
    }
    
    @RequestMapping(value="/user/findUserRole",method=RequestMethod.POST)
    @ResponseBody
    public List<Role> findUserRole(Integer id,ModelMap map){
    	List<Role> list = roleService.queryRoleByUserId(id);
    	if(list.isEmpty()){
    		return null;
    	}
    	return list;
    }
    
    @RequestMapping(value="/user/resetRole",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel resetRole(OperatorVo vo){
    	List<OperatorVo> list =  operatorService.selectUserRole(vo);
    	if(!list.isEmpty()){
    		return new ResultModel("角色不能重复添加",false);
    	}
    	if(roleService.insertUserRole(vo.getOperatorId(),vo.getRoleId()) >0){
    		return new ResultModel("新增成功",true);
    	}
    	return new ResultModel("新增失败",false);
    }
    
  //  @RequestMapping(value = "/menu",method = RequestMethod.POST)
  //  @ResponseBody
  //  public List<Right> getMenu(){
//        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
//        return roleRightService.getRight(admin.getRoleId());
   //     return null;
 //   }

    
}
