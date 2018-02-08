package com.AitBenOm.GymMonitor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int idUser;

    private String userName;
    private String lastName;
    private String email;
    private String pwd;

    @JsonIgnore
    @OneToMany(mappedBy="user",cascade =CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Program> programList;

    public User() {
    }

    public User(String userName, String lastName, String email, String pwd) {
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.pwd = pwd;

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }
}
