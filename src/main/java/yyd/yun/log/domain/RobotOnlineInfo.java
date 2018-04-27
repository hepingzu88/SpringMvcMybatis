package yyd.yun.log.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 用户使用机器人记录
 * @author yyd
 *
 */
public class RobotOnlineInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 自增i
	 */
	private Integer i;
	
	/**
	 * RID
	 */
	private Integer rid;
	
	/**
	 * rType
	 */
	private String rtype;
	
	/**
	 * 用户ip
	 */
	private String ip;
	
	/**
	 * 用户所在城市
	 */
	private String city;
	
	/**
	 * 用户所在省份
	 */
	private String province;
	
	/**
	 * 用户在线时间
	 */
	private String onlineTime;
	
	/**
	 * 用户下线时间
	 */
	private String offlineTime;
	
	/**
	 * count 
	 */
	private int value;
	
	/**
	 * name
	 */
	private String name;
	
	/**
	 * arr
	 */	
	private String[] arr;
	
	
	
	public RobotOnlineInfo(Integer i, Integer rid, String rtype, String ip, String city, String province,
			String onlineTime, String offlineTime, int value, String name, String[] arr) {
		super();
		this.i = i;
		this.rid = rid;
		this.rtype = rtype;
		this.ip = ip;
		this.city = city;
		this.province = province;
		this.onlineTime = onlineTime;
		this.offlineTime = offlineTime;
		this.value = value;
		this.name = name;
		this.arr = arr;
	}

	public RobotOnlineInfo(){}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String[] getArr() {
		return arr;
	}

	public void setArr(String[] arr) {
		this.arr = arr;
	}

	@Override
	public String toString() {
		return "RobotOnlineInfo [i=" + i + ", rid=" + rid + ", rtype=" + rtype + ", ip=" + ip + ", city=" + city
				+ ", province=" + province + ", onlineTime=" + onlineTime + ", offlineTime=" + offlineTime + ", value="
				+ value + ", name=" + name + ", arr=" + Arrays.toString(arr) + "]";
	}

	
}
