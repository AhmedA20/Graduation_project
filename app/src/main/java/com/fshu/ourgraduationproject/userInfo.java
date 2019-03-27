package com.fshu.ourgraduationproject;
/*
This class is intermediate tos tore and transfer user info
MAYBE DISPOSED LATER
 */
public class userInfo {
    private String uName;
    private String year;
    private String department;
    private String subDepartment;
    private String dbUid;//firebase user id
    private boolean studentOrDoctor;//true only for student

    public userInfo(){

    }

    public userInfo(String uName, String year, String department, String subDepartment, String dbUid, boolean studentOrDoctor){
        this.uName = uName;
        this.year = year;
        this.department = department;
        this.subDepartment = subDepartment;
        this.dbUid = dbUid;
        this.studentOrDoctor = studentOrDoctor;
    }

    public String getID(){
            return dbUid;
    }

    public void setID(String dbValue){
            this.dbUid =dbValue;
    }

    public String getName(){
            return uName;
    }

    public void setName(String dbValue){
            this.uName =dbValue;
    }

    public String getYear(){
        return year;
    }

    public void setYear(String dbValue){
        this.year =dbValue;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String dbValue){
        this.department =dbValue;
    }

    public String getSubDepartment(){
        return subDepartment;
    }

    public void setSubDepartment(String dbValue){
        this.subDepartment =dbValue;
    }

    public boolean getStudentOrDoctor(){
        return studentOrDoctor;
    }

    public void setStudentOrDoctor(boolean dbValue){
        this.studentOrDoctor = dbValue;
    }





}
