package com.example.cardiacrecorder;

/**
 * Record is a class which stores data for a particular record
 */
public class Record implements Comparable<Record> {
    private String systolic;
    private String diastolic;
    private String pulse;
    private String date;
    private String time;
    private String comment;
    private String bpStatus;
    private String pulseStatus;

    /**
     * constructor is declared here
     * @param systolic
     * systolic
     * @param diastolic
     * diastolic
     * @param pulse
     * pulse
     * @param date
     * date
     * @param time
     * time
     * @param comment
     * comment
     * @param bpStatus
     * blood pressure status
     * @param pulseStatus
     * pulse rate status
     */
    public Record(String systolic, String diastolic, String pulse, String date, String time, String comment, String bpStatus, String pulseStatus) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = pulse;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.bpStatus = bpStatus;
        this.pulseStatus = pulseStatus;
    }

    /**
     * getter for systolic
     * @return
     * String
     */
    public String getSystolic() {
        return systolic;
    }

    /**
     * setter for systolic
     * @param systolic
     */
    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    /**
     * getter for diastolic
     * @return
     * String
     */
    public String getDiastolic() {
        return diastolic;
    }

    /**
     * setter for diastolic
     * @param diastolic
     */
    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    /**
     * getter for pulse
     * @return
     * String
     */
    public String getPulse() {
        return pulse;
    }

    /**
     * setter for pulse
     * @param pulse
     */
    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    /**
     * getter for date
     * @return
     * String
     */
    public String getDate() {
        return date;
    }

    /**
     * setter for date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * getter for time
     * @return
     * String
     */
    public String getTime() {
        return time;
    }

    /**
     * setter for time
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * getter for comment
     * @return
     * String
     */
    public String getComment() {
        return comment;
    }

    /**
     * setter for comment
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * getter for blood pressure status
     * @return
     * String
     */
    public String getBpStatus() {
        return bpStatus;
    }

    /**
     * setter for blood pressure status
     * @param bpStatus
     */
    public void setBpStatus(String bpStatus) {
        this.bpStatus = bpStatus;
    }

    /**
     * getter for pulse status
     * @return
     * String
     */
    public String getPulseStatus() {
        return pulseStatus;
    }

    /**
     * setter for pulse status
     * @param pulseStatus
     */
    public void setPulseStatus(String pulseStatus) {
        this.pulseStatus = pulseStatus;
    }

    /**
     * check between two record if they are equal or not
     * @param record datatype is Record
     * @return
     * 0 if equals
     */
    @Override
    public int compareTo(Record record) {
        return this.systolic.compareTo(record.getSystolic());
    }

}