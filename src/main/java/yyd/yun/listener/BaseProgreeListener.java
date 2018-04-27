package yyd.yun.listener;

import yyd.yun.beans.Progress;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public abstract class BaseProgreeListener {
    protected HttpSession session;
    public void setSession(HttpSession session) {
        this.session = session;
        Progress status = new Progress();
        session.setAttribute("status", status);
    }
}
