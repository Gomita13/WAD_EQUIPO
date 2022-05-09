package com.example.modelos.dto;

import com.example.modelos.entidades.Persona;

public class PersonaDTO {

    private Persona entidad;

    public PersonaDTO(){
        entidad = new Persona();
    }

    public Persona getEntidad(){
        return entidad;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(entidad.getEmail()).append("\n");
        sb.append("Nombre: ").append(entidad.getNombre()).append("\n");
        sb.append("Apellidos: ").append(entidad.getApellidos()).append("\n");
        return sb.toString();
    }

}
