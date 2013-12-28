package org.strongme.commtech.bean;

public class ComboBean {
	private ExtraCommBean extra;
	private StudentCommBean[] scs;
	public StudentCommBean[] getScs() {
		return scs;
	}
	public void setScs(StudentCommBean[] scs) {
		this.scs = scs;
	}
	public ExtraCommBean getExtra() {
		return extra;
	}
	public void setExtra(ExtraCommBean extra) {
		this.extra = extra;
	}
	
	

}
