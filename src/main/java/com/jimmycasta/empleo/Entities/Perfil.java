package com.jimmycasta.empleo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "perfiles")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String perfil;

    public Perfil() {
    }

    public Perfil(int id, String perfil) {
        this.id = id;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
