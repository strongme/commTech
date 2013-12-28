package org.strongme.commtech.bean;



public class CourseBean  implements java.io.Serializable {
	
     private Integer id;
     private String courseid;
     private String coursename;


    public CourseBean() {
    }

    public CourseBean(String courseid) {
        this.courseid = courseid;
    }
    
    public CourseBean(String courseid, String coursename) {
        this.courseid = courseid;
        this.coursename = coursename;
    }

   

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getCourseid() {
        return this.courseid;
    }
    
    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
    

    public String getCoursename() {
        return this.coursename;
    }
    
    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
   








}