package com.example.modelos.entidades;

public class Persona {

    private String email, nombre, apellidos, password;
    private int id;

    public Persona(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
