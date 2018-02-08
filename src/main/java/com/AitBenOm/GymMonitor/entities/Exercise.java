package com.AitBenOm.GymMonitor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;

@Entity
public class Exercise {
    @Id
    @GeneratedValue
    private int idExercise;
    private String exerciseName;

    @ManyToOne
      private Program program;

    @JsonIgnore
    @OneToMany(mappedBy="exercise",cascade =CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Charge> charges;



    public List<Charge> getCharges() {
        return charges;
    }

    public void setCharges(List<Charge> charges) {
        this.charges = charges;
    }

    public Exercise() {
    }

    public Exercise(String exerciseName) {
        this.exerciseName = exerciseName;

    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
