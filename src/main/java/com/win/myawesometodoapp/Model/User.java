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

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Task.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private Collection<Task> tasks;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name ="user_role", joinColumns = @JoinColumn (name ="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

}
