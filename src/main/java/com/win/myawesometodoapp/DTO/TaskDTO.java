package com.win.myawesometodoapp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long taskId;
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
