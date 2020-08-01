package com.win.myawesometodoapp.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="text")
    private String text;

    @Column(name="iscomplete")
    private boolean isComplete;

    @Column(name="datesubmit")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateSubmit;

    @Column(name="user_id")
    private Long userId;
}
