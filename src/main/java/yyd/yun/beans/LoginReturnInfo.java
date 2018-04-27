package yyd.yun.beans;

import org.apache.commons.lang.StringUtils;

/**
 * Created by fulaizhi on 2017/5/7.
 */
public enum LoginReturnInfo {

    incorrectCredentials("用户名或密码错误"),
    excessiveAttempts("密码错误次数过多"),
    unknownAccount("未知账户"),
    lockedAccount("账户已锁定"),
    unsupportedToken("身份认证异常"),
    captchaRequired("验证码错误");

    private String msg;

    private LoginReturnInfo(String msg){
        this.msg = msg;
    }

    public static LoginReturnInfo getInstance(String className){
        if(StringUtils.isBlank(className)){
            return null;
        }
        LoginReturnInfo info;
        switch (className){
            case "org.apache.shiro.authc.IncorrectCredentialsException":info=LoginReturnInfo.incorrectCredentials;break;
            case "org.apache.shiro.authc.ExcessiveAttemptsException":info=LoginReturnInfo.excessiveAttempts;break;
            case "org.apache.shiro.authc.UnknownAccountException":info=LoginReturnInfo.unknownAccount;break;
            case "org.apache.shiro.authc.LockedAccountException":info=LoginReturnInfo.lockedAccount;break;
            case "org.apache.shiro.authc.pam.UnsupportedTokenException":info=LoginReturnInfo.unsupportedToken;break;
            case "com.yyd.exception.CaptchaRequiredException":info=LoginReturnInfo.captchaRequired;break;
            default : info=null;break;
        }
        return info;
    }

    public String get(){
        return msg;
    }

}
