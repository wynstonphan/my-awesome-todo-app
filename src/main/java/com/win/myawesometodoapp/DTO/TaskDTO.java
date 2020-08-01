package com.win.myawesometodoapp.DTO;


import java.util.Date;


public class TaskDTO {

    private String text;
    private Boolean isComplete;
    private Date dateSubmit;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public Date getSubmitDate() {
        return dateSubmit;
    }

    public void setSubmitDate(Date submitDate) {
        this.dateSubmit = submitDate;
    }
}
