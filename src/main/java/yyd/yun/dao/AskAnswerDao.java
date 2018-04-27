package yyd.yun.dao;

import java.util.List;


import yyd.yun.beans.AskAnswer;

public interface AskAnswerDao {
 
	public List<AskAnswer> find(AskAnswer ask);
	
	public int addAskAnswer(AskAnswer ask);
}
