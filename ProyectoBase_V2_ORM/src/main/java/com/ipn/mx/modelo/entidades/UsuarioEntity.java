package com.ipn.mx.modelo.entidades;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "usuario", schema = "escuelaweb", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;
    @Basic
    @Column(name = "paterno", nullable = false, length = 35)
    private String paterno;
    @Basic
    @Column(name = "materno", nullable = false, length = 35)
    private String materno;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "nombreUsuario", nullable = false, length = 100)
    private String nombreUsuario;
    @Basic
    @Column(name = "claveUsuario", nullable = false, length = 255)
    private String claveUsuario;
    @Basic
    @Column(name = "fechaCreacion", nullable = false)
    private Date fechaCreacion;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
