package org.strongme.commtech.bean;
public class ClassBean  implements java.io.Serializable {


     private Integer id;
     private String classid;
     private String classname;
    public ClassBean() {
    }

    public ClassBean(String classid) {
        this.classid = classid;
    }
    
    public ClassBean(String classid, String classname) {
        this.classid = classid;
        this.classname = classname;
    }

   

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getClassid() {
        return this.classid;
    }
    
    public void setClassid(String classid) {
        this.classid = classid;
    }
    

    public String getClassname() {
        return this.classname;
    }
    
    public void setClassname(String classname) {
        this.classname = classname;
    }
   








}