package yyd.yun.listener;

import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;

import yyd.yun.beans.Progress;


/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class OssProgressListener extends BaseProgreeListener implements ProgressListener {

    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        Progress status = (Progress) session.getAttribute("status");
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                break;
            case REQUEST_CONTENT_LENGTH_EVENT:
                if(bytes>0){
                    status.setContentLength(bytes);
                }
                break;
            case REQUEST_BYTE_TRANSFER_EVENT:
                status.addBytesRead(bytes);
                break;
            case TRANSFER_COMPLETED_EVENT:
                status.setSucceed(Progress.PROGRESS_SUCCESS);
                break;
            case TRANSFER_FAILED_EVENT:
                status.setSucceed(Progress.PROGRESS_FAIL);
                break;
            default:
                break;
        }
    }
}
