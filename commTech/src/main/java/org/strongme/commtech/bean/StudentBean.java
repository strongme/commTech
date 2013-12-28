package org.strongme.commtech.bean;



public class StudentBean  implements java.io.Serializable {



     private Integer id;
     private String studentid;
     private String studentname;
     private String studentpassword;
     private String classid;


    public StudentBean() {
    }

    public StudentBean(String studentid) {
        this.studentid = studentid;
    }
    
    public StudentBean(String studentid, String studentname, String studentpassword, String classid) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.studentpassword = studentpassword;
        this.classid = classid;
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

    public String getStudentname() {
        return this.studentname;
    }
    
    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
    

    public String getStudentpassword() {
        return this.studentpassword;
    }
    
    public void setStudentpassword(String studentpassword) {
        this.studentpassword = studentpassword;
    }
    

    public String getClassid() {
        return this.classid;
    }
    
    public void setClassid(String classid) {
        this.classid = classid;
    }
   








}