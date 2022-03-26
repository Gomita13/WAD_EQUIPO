package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Alumno;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author darkdestiny
 */
@Data
@AllArgsConstructor
public class AlumnoDTO implements Serializable {

    private Alumno entidad;

    public AlumnoDTO() {
        entidad = new Alumno();
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Clave : ").append(getEntidad().getIdAlumno()).append("\n");
        sb.append("Nombre : ").append(getEntidad().getNombreAlumno()).append("\n");
        sb.append("Paterno : ").append(getEntidad().getPaternoAlumno()).append("\n");
        sb.append("Materno : ").append(getEntidad().getMaternoAlumno()).append("\n");
        sb.append("Email : ").append(getEntidad().getEmailAlumno()).append("\n");
        sb.append("Clave Carrera  : ").append(getEntidad().getIdCarrera()).append("\n");
        //sb.append("Clave Carrera  : ").append(getEntidad().getIdCarrera().getIdCarrera).append("\n");

        return sb.toString();
    }
}