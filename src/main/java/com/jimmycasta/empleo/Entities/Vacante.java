package com.jimmycasta.empleo.Entities;

import java.util.Date;

public class Vacante {

    private long id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private double salario;
    private int destacado;
    private String image = "no-image.png";
    private String estatus;
    private String detalles;
    private Categoria categoria;

    public Vacante() {
    }

    public Vacante(long id, String nombre, String descripcion, Date fecha, double salario, int destacado, String image, String estatus, String detalles, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.salario = salario;
        this.destacado = destacado;
        this.image = image;
        this.estatus = estatus;
        this.detalles = detalles;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getDestacado() {
        return destacado;
    }

    public void setDestacado(int destacado) {
        this.destacado = destacado;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEstatus() {
        return estatus;
    }

    public Categoria getCategoria() {
        return categoria;
    }


    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Vacante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", salario=" + salario +
                ", destacado=" + destacado +
                ", image='" + image + '\'' +
                ", estatus='" + estatus + '\'' +
                ", detalles='" + detalles + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
