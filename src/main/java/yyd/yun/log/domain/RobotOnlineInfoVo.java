package yyd.yun.log.domain;

import java.util.Arrays;

public class RobotOnlineInfoVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String arr[];

	public String[] getArr() {
		return arr;
	}

	public void setArr(String[] arr) {
		this.arr = arr;
	}

	@Override
	public String toString() {
		return "RobotOnlineInfoVo [arr=" + Arrays.toString(arr) + "]";
	}
	
	
}
