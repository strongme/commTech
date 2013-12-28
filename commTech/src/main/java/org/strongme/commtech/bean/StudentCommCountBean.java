package org.strongme.commtech.bean;

/**
 * 已统计人数的统计
 * @author walter
 *
 */
public class StudentCommCountBean {
	
	private long alreadyCount;
	private String className;
	private long totalCount;
	public long getAlreadyCount() {
		return alreadyCount;
	}
	public void setAlreadyCount(long alreadyCount) {
		this.alreadyCount = alreadyCount;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	

}
