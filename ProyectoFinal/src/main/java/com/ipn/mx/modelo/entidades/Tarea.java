package com.ipn.mx.modelo.entidades;

public class Tarea {
    private String nombreTarea;
    private String nombreProyecto;
    private String encargado;
    private String descripcion;
    private boolean completada;

    public Tarea() {
    }

    public Tarea(String nombreTarea, String nombreProyecto) {
        this.nombreTarea = nombreTarea;
        this.nombreProyecto = nombreProyecto;
    }

    public Tarea(String nombreTarea, String nombreProyecto, boolean completada) {
        this.nombreTarea = nombreTarea;
        this.nombreProyecto = nombreProyecto;
        this.completada = completada;
    }

    public Tarea(String nombreTarea, String nombreProyecto, String encargado, String descripcion, boolean completada) {
        this.nombreTarea = nombreTarea;
        this.nombreProyecto = nombreProyecto;
        this.encargado = encargado;
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}