package org.strongme.commtech.bean;


public class TeacherScoreStatisticBean {
	
	private String teacherId;
	private String teacherName;
	private String courseName;
	private double avgScore;
	private int countStudent;
	
	
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	public int getCountStudent() {
		return countStudent;
	}
	public void setCountStudent(int countStudent) {
		this.countStudent = countStudent;
	}
	
	

}
