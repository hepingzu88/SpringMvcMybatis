package yyd.yun.service;


import com.github.pagehelper.PageInfo;

import yyd.yun.beans.AskAnswer;

public interface AskAnswerService {

	public PageInfo<AskAnswer> find(AskAnswer ask,Integer pageNum);
	
	public int addAskAnswer(AskAnswer ask);
}
