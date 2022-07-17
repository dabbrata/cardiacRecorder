package com.example.cardiacrecorder;

/**
 * this Model is created with constructor and get and set the value.
 */
public class Model {

    String username,syscomment,dyascomment,bpmcomment,bpm,systolic,dyastolic,date,time;
    int id;

    /**
     * Create a constructor named Model like the class name
     * @param id
     * @param username
     * @param bpm
     * @param systolic
     * @param dyastolic
     * @param syscomment
     * @param dyascomment
     * @param bpmcomment
     * @param date
     * @param time
     */
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

    /**
     * get the id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * set the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get user name
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * set user name
     * @param username
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get systolic comment
     * @return
     */
    public String getSyscomment() {
        return syscomment;
    }

    /**
     * set systolic comment
     * @param syscomment
     */
    public void setSyscomment(String syscomment) {
        this.syscomment = syscomment;
    }

    /**
     * get dyastolic comment
     * @return
     */
    public String getDyascomment() {
        return dyascomment;
    }

    /**
     * set dyastolic comment
     * @param dyascomment
     */
    public void setDyascomment(String dyascomment) {
        this.dyascomment = dyascomment;
    }

    /**
     * get bpm comment
     * @return
     */
    public String getBpmcomment() {
        return bpmcomment;
    }

    /**
     * set bpm comment
     * @param bpmcomment
     */
    public void setBpmcomment(String bpmcomment) {
        this.bpmcomment = bpmcomment;
    }

    /**
     * get bpm value
     * @return
     */
    public String getBpm() {
        return bpm;
    }

    /**
     * set bpm value
     * @param bpm
     */
    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    /**
     * get systolic value
     * @return
     */
    public String getSystolic() {
        return systolic;
    }

    /**
     * set systolic value
     * @param systolic
     */
    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    /**
     * get dyastolic value
     * @return
     */
    public String getDyastolic() {
        return dyastolic;
    }

    /**
     * set dyastolic value
     * @param dyastolic
     */
    public void setDyastolic(String dyastolic) {
        this.dyastolic = dyastolic;
    }

    /**
     * gate date
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * set date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * gate time
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * set time
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

}
