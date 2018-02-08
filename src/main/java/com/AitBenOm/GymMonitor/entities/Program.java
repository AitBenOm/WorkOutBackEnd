package com.AitBenOm.GymMonitor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Program   {

    @Id
    @GeneratedValue
    private int idProgram;

    private String programName;


    private Date dateOfCreation;


    private Date lastModification;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy="program",cascade =CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Exercise> exercises;


    public Program() {
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Program(String programName, Date dateOfCreation, Date lastModification){
        this.programName = programName;
        this.dateOfCreation = dateOfCreation;
        this.lastModification = lastModification;

    }

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

  }
