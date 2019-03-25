package com.fshu.ourgraduationproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
 a getter and setter class
 //TODO --> modify for DB import
 */
public class gradesImport {
    //values to fetched from dataBase
    //may be moved to specialised class
    private double oral;
    private double attendance;
    private double midTerm;
    private double finalExam;

    //fireBase object
    FirebaseDatabase mFireBase;
    DatabaseReference mDataBaseReference;

    public gradesImport(){

    }

    public gradesImport(double oral,double attendance,double midTerm, double finalExam){
        this.oral = oral;
        this.attendance = attendance;
        this.midTerm = midTerm;
        this.finalExam = finalExam;
    }

    public void setOral(double oral){
        this.oral = oral;
    }

    public double getOral(){
        return oral;
    }

    public void setAttendance(double attendance){
        this.attendance = attendance;
    }

    public double getAttendance(){
        return attendance;
    }

    public void setMidTerm(double midTerm){
        this.midTerm = midTerm;
    }

    public double getMidTerm(){
        return midTerm;
    }

    public void setFinalExam(double finalExam){
        this.finalExam = finalExam;
    }

    public double getFinalExam(){
        return finalExam;
    }



}
