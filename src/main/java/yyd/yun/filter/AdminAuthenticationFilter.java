package yyd.yun.filter;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import yyd.yun.constants.AdminConstant;
import yyd.yun.security.CaptchaRequiredException;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class AdminAuthenticationFilter extends FormAuthenticationFilter {
    private Cache<String, AtomicInteger> passwordRetryCache;

    private Cache<String,String > captchaCache;

    public AdminAuthenticationFilter(CacheManager cacheManager){
        super();
        captchaCache = cacheManager.getCache("yyd.captcha");
        passwordRetryCache = cacheManager.getCache("yyd.passwordRetryCache");
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "create AuthenticationToken error";
            throw new IllegalStateException(msg);
        }
        try {
            /**
             * 校验验证码
             */
            if(!isCaptchaValidate(request,token)){
                throw new CaptchaRequiredException();
            }
            Subject subject = getSubject(request, response);
            subject.login(token);
            //subject.isPermitted("user:show");
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        /**
         * 成功更新最新登录时间和ip，移除登录错误次数
         */
        response.getWriter().write(1);
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        AtomicInteger errorCount = passwordRetryCache.get((String)token.getPrincipal());
        if(errorCount!=null){
            request.setAttribute("errorCount",errorCount.get());
        }
        return super.onLoginFailure(token, e, request, response);
    }

    private boolean isCaptchaValidate(ServletRequest request,AuthenticationToken token) throws AuthenticationException{
        String verifyCode = request.getParameter("verifyCode");
        if(StringUtils.isNotBlank(verifyCode)){
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
            String _verifyCode = captchaCache.get(AdminConstant.VERIFYCODE_PREFIX + session.getId());
            return verifyCode.equals(_verifyCode);
        }
        AtomicInteger errorCount = passwordRetryCache.get(token.getPrincipal().toString());
        if(errorCount==null || errorCount.get()<=3){ //错误3次就需要输入验证码
            return true;
        }else{
            return false;
        }
    }
}
