package com.example.cardiacrecorder;

public class Model {

    String username,syscomment,dyascomment,bpmcomment,bpm,systolic,dyastolic,date,time;
    int id;


    public Model(int id,String username, String bpm, String systolic, String dyastolic, String syscomment, String dyascomment, String bpmcomment, String date, String time) {
        this.username = username;
        this.bpmcomment = bpmcomment;
        this.syscomment = syscomment;
        this.dyascomment = dyascomment;
        this.bpm = bpm;
        this.systolic = systolic;
        this.dyastolic = dyastolic;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSyscomment() {
        return syscomment;
    }

    public void setSyscomment(String syscomment) {
        this.syscomment = syscomment;
    }

    public String getDyascomment() {
        return dyascomment;
    }

    public void setDyascomment(String dyascomment) {
        this.dyascomment = dyascomment;
    }

    public String getBpmcomment() {
        return bpmcomment;
    }

    public void setBpmcomment(String bpmcomment) {
        this.bpmcomment = bpmcomment;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDyastolic() {
        return dyastolic;
    }

    public void setDyastolic(String dyastolic) {
        this.dyastolic = dyastolic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
