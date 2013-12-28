package org.strongme.commtech.bean;
public class CommTechBean  implements java.io.Serializable {



     private Integer id;
     private String courseid;
     private Integer avgscore;



    public CommTechBean() {
    }

    
    public CommTechBean(String courseid, Integer avgscore) {
        this.courseid = courseid;
        this.avgscore = avgscore;
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
    

    public Integer getAvgscore() {
        return this.avgscore;
    }
    
    public void setAvgscore(Integer avgscore) {
        this.avgscore = avgscore;
    }
   








}