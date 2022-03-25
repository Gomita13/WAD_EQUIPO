package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author darkdestiny
 */
@Data
@NoArgsConstructor
public class Alumno implements Serializable {

    private Long idAlumno;
    private String nombreAlumno;
    private String paternoAlumno;
    private String maternoAlumno;
    private String emailAlumno;
    private int idCarrera;

    //private Carrera idCarrera

}