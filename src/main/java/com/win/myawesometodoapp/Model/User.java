package com.win.myawesometodoapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Task.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private Collection<Task> tasks;


}
