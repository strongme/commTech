package org.strongme.commtech.bean;



public class ExtraCommBean  implements java.io.Serializable {

     private Integer id;
     private String studentid;
     private String studentname;
     private String comment;
     private String classname;
     
     


    public String getStudentname() {
		return studentname;
	}


	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}


	public ExtraCommBean() {
    }

    
    public ExtraCommBean(String studentid, String comment) {
        this.studentid = studentid;
        this.comment = comment;
    }


    public String getClassname() {
		return classname;
	}


	public void setClassname(String classname) {
		this.classname = classname;
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
    

    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
   








}