package com.ipn.mx.modelo.entidades;


import java.sql.Date;

public class Proyecto {
    private String nombreProyecto;
    private Date inicio;
    private Date fin;
    private String administrador;

    public Proyecto() {
    }

    public Proyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Proyecto(String nombreProyecto, Date inicio, Date fin, String administrador) {
        this.nombreProyecto = nombreProyecto;
        this.inicio = inicio;
        this.fin = fin;
        this.administrador = administrador;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}