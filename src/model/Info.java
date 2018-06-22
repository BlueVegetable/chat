package model;

import java.util.Date;

public class Info implements Comparable<Info>{
	private String userOne;
	private String userAnother;
	private String content;
	private Date time;
	public String getUserOne() {
		return userOne;
	}
	public void setUserOne(String userOne) {
		this.userOne = userOne;
	}
	public String getUserAnother() {
		return userAnother;
	}
	public void setUserAnother(String userAnother) {
		this.userAnother = userAnother;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String toString() {
		return time+"时,"+userOne+"对"+userAnother+"说:"+content;
	}
	public int compareTo(Info another) {
		return time.compareTo(another.getTime());
	}
}