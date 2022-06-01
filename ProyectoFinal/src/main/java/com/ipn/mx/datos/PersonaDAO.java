package com.ipn.mx.datos;

import com.ipn.mx.dominio.Persona;

import java.sql.*;
import java.util.*;

public class PersonaDAO {
    private static final String SQL_SELECT = "SELECT email, nombre, apellidos, password FROM persona";
    private static final String SELECT_BY_ID = "SELECT email, nombre, apellidos, password FROM persona WHERE email=?";
    private static final String SQL_INSERT = "INSERT INTO persona (email, nombre, apellidos, password) "
            + " VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET email=?, nombre=?, apellidos=?, password=?" +
            " WHERE email=?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE email=?";

    public List<Persona> listar() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while(rs.next()) {
                String email = rs.getString("email");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String password = rs.getString("password");
                persona = new Persona(email, nombre, apellidos, password);
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return personas;
    }
}