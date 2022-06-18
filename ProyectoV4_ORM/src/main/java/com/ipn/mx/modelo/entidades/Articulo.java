package com.ipn.mx.modelo.entidades;

import javax.persistence.*;

@Entity
public class Articulo {

    @Id
    @GeneratedValue
    private int idarticulo;
    private String nombre;
    private String descripcion;
    private int existencias;
    private int stockminimo;
    private int stockmaximo;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria", nullable = false)
    private Categoria idcategoria;

    public Articulo(){

    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
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

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getStockminimo() {
        return stockminimo;
    }

    public void setStockminimo(int stockminimo) {
        this.stockminimo = stockminimo;
    }

    public int getStockmaximo() {
        return stockmaximo;
    }

    public void setStockmaximo(int stockmaximo) {
        this.stockmaximo = stockmaximo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }
}
