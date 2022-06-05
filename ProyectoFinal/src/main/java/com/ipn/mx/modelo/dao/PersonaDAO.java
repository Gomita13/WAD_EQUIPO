package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.Conexion;
import com.ipn.mx.modelo.entidades.Persona;

import java.sql.*;
import java.util.*;

public class PersonaDAO {
    private static final String SQL_SELECT = "SELECT email, nombre, apellidos, password FROM persona";
    private static final String SELECT_BY_ID = "SELECT email, nombre, apellidos, password FROM persona WHERE email=?";
    private static final String SELECT_LOGIN = "SELECT email, nombre, apellidos, password FROM persona WHERE email=?" +
            " AND password = ?";
    private static final String SELECT_BY_PROJECT = "SELECT p.email, p.nombre, p.apellidos from persona p INNER JOIN" +
            " colaborador c on p.email = c.emailpersona INNER JOIN proyecto p2 on p2.nombreproyecto = c.nombreproyecto" +
            " WHERE p2.nombreproyecto = 'Web'";
    private static final String SQL_INSERT = "INSERT INTO persona (email, nombre, apellidos, password) "
            + " VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET email=?, nombre=?, apellidos=?, password=?" +
            " WHERE email=?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE email=?";

    public List<Persona> selectAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String password = rs.getString("password");
                persona = new Persona(email, nombre, apellidos, password);
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return personas;
    }

    public boolean login(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean status = false;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_LOGIN);
            ps.setString(1, persona.getEmail());
            ps.setString(2, persona.getPassword());
            rs = ps.executeQuery();
            status = rs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return status;
    }

    public Persona selectOne(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Persona personaRes = new Persona();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setString(1, persona.getEmail());
            rs = ps.executeQuery();
            if(rs.next()) {
                String email = rs.getString("email");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String password = rs.getString("password");
                personaRes.setEmail(email);
                personaRes.setNombre(nombre);
                personaRes.setApellidos(apellidos);
                personaRes.setPassword(password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return personaRes;
    }

    public int insert(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, persona.getEmail());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellidos());
            ps.setString(4, persona.getPassword());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(Persona persona, String emailOld) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, persona.getEmail());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellidos());
            ps.setString(4, persona.getPassword());
            ps.setString(5, emailOld);
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setString(1, persona.getEmail());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }
}