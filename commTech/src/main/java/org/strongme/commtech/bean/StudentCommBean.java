package org.strongme.commtech.bean;



public class StudentCommBean  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String studentid;
     private String courseid;
     private Integer score;


    public StudentCommBean() {
    }

    
    public StudentCommBean(String studentid, String courseid, Integer score) {
        this.studentid = studentid;
        this.courseid = courseid;
        this.score = score;
    }


    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getStudentid() {
        return this.studentid;
    }
    
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
    

    public String getCourseid() {
        return this.courseid;
    }
    
    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
    

    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
   








}