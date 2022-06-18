package com.ipn.mx.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Categoria.readAll", query = "SELECT c FROM Categoria c")
public class Categoria {

    @Id
    @GeneratedValue
    private int idcategoria;
    private String nombrecategoria;
    private String descripcioncategoria;

    public Categoria(){

    }

    public Categoria(int id) {
        this.idcategoria = id;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombrecategoria() {
        return nombrecategoria;
    }

    public void setNombrecategoria(String nombrecategoria) {
        this.nombrecategoria = nombrecategoria;
    }

    public String getDescripcioncategoria() {
        return descripcioncategoria;
    }

    public void setDescripcioncategoria(String descripcioncategoria) {
        this.descripcioncategoria = descripcioncategoria;
    }
}
