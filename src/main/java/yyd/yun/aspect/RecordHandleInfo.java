package yyd.yun.aspect;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import yyd.yun.aspect.service.RecordHandleLogService;
import yyd.yun.beans.Operator;
import yyd.yun.beans.RecordHandleLog;


@Component
@Aspect
public class RecordHandleInfo {

	private final Logger logger = Logger.getLogger(this.getClass());  

    private String requestPath = null ; // 请求地址  
    private String userName = "" ; // 用户名  
    private String targetName = "";//类名
    private String methodName;//方法名
    private String params ="";// 传入参数  
    private long startTimeMillis = 0; // 开始时间  
    private long endTimeMillis = 0; // 结束时间  
    private Operator currUser = null;
  
    @Autowired 
    private RecordHandleLogService recordHandleLogService;
	
	/*@Pointcut("execution(* yyd.yun.service.*.*(..))")
	public void anyMethod(){
	}
	*/
	/*// 引用切入点表达式 (好处可重复使用) 
	@Before("anyMethod()")
	public void before(){
        //fileName  为例子
	}


	@After("anyMethod()")
	public  void after(JoinPoint joinPoint) { 
		
	}*/
	
	
	@Around("@annotation(yyd.yun.aspect.CommentAnno)")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		Object obj = null;
		try {
			currUser = (Operator) SecurityUtils.getSubject().getPrincipal();
	        startTimeMillis = System.currentTimeMillis(); //记录方法开始执行的时间  
	        
			obj = pjp.proceed();//invok obj是执行的方法返回的结果，如果没有返回值，就是null
			
			RecordHandleLog log = new RecordHandleLog();
			targetName = pjp.getTarget().getClass().getName();  //类名
			methodName = pjp.getSignature().getName();  //方法名
			requestPath = getHttpServletRequest().getLocalAddr();//请求ip
			
			Object[] arguments = pjp.getArgs();  
	        Class targetClass = null;
	        try {
	            targetClass = Class.forName(targetName);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }  
	        Method[] methods = targetClass.getMethods();
	        String operationName = "";
	        String operationType = "";
	        for (Method method : methods) {  
	            if (method.getName().equals(methodName)) {  
	                Class[] clazzs = method.getParameterTypes();  
	                if (clazzs!=null&&clazzs.length == arguments.length&&method.getAnnotation(CommentAnno.class)!=null) {  
	                    operationName = method.getAnnotation(CommentAnno.class).operationName();
	                    operationType = method.getAnnotation(CommentAnno.class).operationType();
	                    break;  
	                }  
	            }  
	        }
	        endTimeMillis = System.currentTimeMillis();
	        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);  //格式化开始时间
	        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis); //格式化结束时间
	        params = "";
	      
			for (int i = 0; i < arguments.length; i++) {
				if(i == arguments.length -1){
					params +=arguments[i];
				}else if(arguments[i] != ""){
					params +=arguments[i]+",";
				}
			}
			log.setActionType(operationType);
	        log.setParams(params);
	        log.setClassName(targetName);
	        log.setMethodName(methodName);
	        log.setReuqestIp(requestPath);
	        log.setStartTime(startTime);
	        log.setEndTime(endTime);
	        log.setDetail(operationName);
	        log.setState(1);
	        currUser = (Operator) SecurityUtils.getSubject().getPrincipal();
	        userName = "findByAdminName".equals(methodName) ? arguments[0].toString() : currUser != null ? currUser.getUsername() : "未知用户"; 
	        log.setUserName(userName);
	        int index = recordHandleLogService.addRecordHandleLog(log);
	        if(index > 0){
	        	logger.info("succeed~~    ip:"+requestPath+" 执行人："+currUser.getUsername()+" 执行service："+targetName+" 执行方法："+methodName);
	        }
		} catch (Exception e) {
			logger.info(" ip:"+requestPath+" 执行人："+currUser.getUsername()+" 执行service："+targetName+" 执行方法："+methodName);
		}
		return obj;
	}
	
	
	public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;  
        HttpServletRequest request = sra.getRequest();
        return request;
    }
	 
}
