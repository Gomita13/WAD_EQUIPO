package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Usuario;

public class UsuarioDTO {

    private Usuario entidad;

    public UsuarioDTO(){
        entidad = new Usuario();
    }

    public Usuario getEntidad(){
        return entidad;
    }

    public void setEntidad(Usuario entidad){
        this.entidad = entidad;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("idUsuario: "+entidad.getIdUsuario()+"\n");
        sb.append("Nombre: "+entidad.getNombre()+"\n");
        sb.append("Paterno: "+entidad.getPaterno()+"\n");
        sb.append("Materno: "+entidad.getMaterno()+"\n");
        sb.append("Email: "+entidad.getEmail()+"\n");
        sb.append("Nombre de usuario: "+entidad.getNombreUsuario()+"\n");
        sb.append("Fecha de creacion: "+entidad.getFechaDeCreacion()+"\n");
        return sb.toString();
    }
}
