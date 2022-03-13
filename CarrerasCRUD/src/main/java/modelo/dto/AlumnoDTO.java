package modelo.dto;

import modelo.entidades.Alumno;

import java.io.Serializable;

public class AlumnoDTO implements Serializable {

    private Alumno entidad;

    public AlumnoDTO(){
        entidad = new Alumno();
    }

    public Alumno getEntidad(){
        return entidad;
    }

    public void setEntidad(Alumno entidad){
        this.entidad = entidad;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Clave: ").append(entidad.getIdAlumno()).append("\n");
        sb.append("Nombre: ").append(entidad.getNombreAlumno()).append("\n");
        sb.append("Apellido paterno: ").append(entidad.getPaternoAlumno()).append("\n");
        sb.append("Apellido materno: ").append(entidad.getMaternoAlumno()).append("\n");
        sb.append("Email: ").append(entidad.getEmailAlumno()).append("\n");
        sb.append("Carrera").append(entidad.getIdCarrera()).append("\n");
        return sb.toString();
    }
}
