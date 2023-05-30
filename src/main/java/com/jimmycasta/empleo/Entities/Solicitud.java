package com.jimmycasta.empleo.Entities;



import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate fecha;

    private String comentarios;

    private String archivo;

    @OneToOne
    @JoinColumn(name = "idVacante")
    private Vacante vacante;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Solicitud() {
        this.fecha = LocalDate.now();
    }

    public Solicitud(int id, LocalDate fecha, String comentarios, String archivo, Vacante vacante, Usuario usuario) {
        this.id = id;
        this.fecha = LocalDate.now();
        this.comentarios = comentarios;
        this.archivo = archivo;
        this.vacante = vacante;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return LocalDate.now();
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archhivo) {
        this.archivo = archhivo;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", comentarios='" + comentarios + '\'' +
                ", archhivo='" + archivo + '\'' +
                ", vacante=" + vacante +
                ", usuario=" + usuario +
                '}';
    }
}
