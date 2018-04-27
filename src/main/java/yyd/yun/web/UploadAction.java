package yyd.yun.web;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yyd.yun.beans.Progress;
import yyd.yun.constants.AdminConstant;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Controller
@RequestMapping("/upload")
public class UploadAction {

    @RequestMapping("/progress")
    @ResponseBody
    public Progress getUploadProgress(HttpSession session){
        Progress progress = (Progress) session.getAttribute(AdminConstant.UPLOAD_PROGRESS);
        if(progress!=null && progress.getSucceed()>0){
            session.removeAttribute("status");
        }
        return progress;
    }

}
