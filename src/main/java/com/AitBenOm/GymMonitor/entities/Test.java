package com.AitBenOm.GymMonitor.entities;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private  String prenom;

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
