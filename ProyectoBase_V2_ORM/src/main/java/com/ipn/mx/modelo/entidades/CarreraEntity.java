package com.ipn.mx.modelo.entidades;

import javax.persistence.*;

@Entity
@Table(name = "carrera", schema = "escuelaweb", catalog = "")
public class CarreraEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCarrera", nullable = false)
    private int idCarrera;
    @Basic
    @Column(name = "nombreCarrera", nullable = false, length = 100)
    private String nombreCarrera;
    @Basic
    @Column(name = "descripcionCarrera", nullable = false, length = 255)
    private String descripcionCarrera;

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getDescripcionCarrera() {
        return descripcionCarrera;
    }

    public void setDescripcionCarrera(String descripcionCarrera) {
        this.descripcionCarrera = descripcionCarrera;
    }
}
