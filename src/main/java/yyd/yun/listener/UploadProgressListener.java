package yyd.yun.listener;

import yyd.yun.beans.Progress;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/9 0009.
 * ProgressListener是一个接口，我们需要自己实现它的update方法，
 * 参数pBytesRead表示已经上传到服务器的字节数，pContentLength表示所有文件的总大小，pItems表示第几个文件：
 */
@Component
public class UploadProgressListener implements ProgressListener {
    private HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
        Progress status = new Progress();
        session.setAttribute("status", status);
    }

    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        Progress status = (Progress) session.getAttribute("status");
        status.setBytesRead(pBytesRead);
        status.setContentLength(pContentLength);
        status.setItems(pItems);
    }
}
