package yyd.yun.beans;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class Progress {
    public static final int PROGRESS_SUCCESS=1;
    public static final int PROGRESS_FAIL=2;
    private long bytesRead;
    private long contentLength;
    private long items;
    private int succeed = 0;


    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public void addBytesRead(long bytesRead) {
        this.bytesRead = this.bytesRead + bytesRead;
    }
    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }
    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }
    @Override
	public String toString() {
		return "Progress [bytesRead=" + bytesRead + ", contentLength=" + contentLength + ", items=" + items
				+ ", succeed=" + succeed + "]";
	}
}
