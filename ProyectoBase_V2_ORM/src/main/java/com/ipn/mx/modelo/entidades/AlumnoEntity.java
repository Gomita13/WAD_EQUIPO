package com.ipn.mx.modelo.entidades;

import javax.persistence.*;

@Entity
@Table(name = "alumno", schema = "escuelaweb", catalog = "")
public class AlumnoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAlumno", nullable = false)
    private int idAlumno;
    @Basic
    @Column(name = "nombreAlumno", nullable = false, length = 70)
    private String nombreAlumno;
    @Basic
    @Column(name = "paternoAlumno", nullable = false, length = 35)
    private String paternoAlumno;
    @Basic
    @Column(name = "maternoAlumno", nullable = false, length = 35)
    private String maternoAlumno;
    @Basic
    @Column(name = "emailAlumno", nullable = false, length = 255)
    private String emailAlumno;
    @Basic
    @Column(name = "idCarrera", nullable = false)
    private int idCarrera;

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getPaternoAlumno() {
        return paternoAlumno;
    }

    public void setPaternoAlumno(String paternoAlumno) {
        this.paternoAlumno = paternoAlumno;
    }

    public String getMaternoAlumno() {
        return maternoAlumno;
    }

    public void setMaternoAlumno(String maternoAlumno) {
        this.maternoAlumno = maternoAlumno;
    }

    public String getEmailAlumno() {
        return emailAlumno;
    }

    public void setEmailAlumno(String emailAlumno) {
        this.emailAlumno = emailAlumno;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
}
