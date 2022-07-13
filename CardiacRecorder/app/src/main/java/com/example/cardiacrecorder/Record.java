package com.example.cardiacrecorder;

public class Record implements Comparable<Record> {
    private String systolic;
    private String diastolic;
    private String pulse;
    private String date;
    private String time;
    private String comment;
    private String bpStatus;
    private String pulseStatus;

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

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBpStatus() {
        return bpStatus;
    }

    public void setBpStatus(String bpStatus) {
        this.bpStatus = bpStatus;
    }

    public String getPulseStatus() {
        return pulseStatus;
    }

    public void setPulseStatus(String pulseStatus) {
        this.pulseStatus = pulseStatus;
    }

    @Override
    public int compareTo(Record record) {
        return this.systolic.compareTo(record.getSystolic());
    }

}
