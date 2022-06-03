package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.Conexion;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ProyectoDAO {
    private static final String SELECT_ALL_PROJECTS = "SELECT * FROM proyecto";
    //Obtiene los proyectos en los que es colaborador cierta persona
    private static final String SQL_SELECT = "SELECT proyecto.nombreproyecto, proyecto.inicio, proyecto.fin," +
            " proyecto.administrador FROM proyecto INNER JOIN colaborador ON " +
            "proyecto.nombreproyecto = colaborador.nombreproyecto INNER JOIN persona ON " +
            "colaborador.emailpersona = persona.email WHERE persona.email = ?";
    //Obtiene todos los datos de un proyecto de acuerdo con el nombre
    private static final String SELECT_BY_NOMBRE = "SELECT * FROM proyecto WHERE nombreproyecto = ?";
    //Obtiene los colaboradores de un proyecto en específico
    private static final String SELECT_COLABORADORES = "SELECT persona.nombre, persona.apellidos, persona.email " +
            "FROM persona INNER JOIN colaborador ON persona.email = colaborador.emailpersona " +
            "INNER JOIN proyecto ON proyecto.nombreproyecto = colaborador.nombreproyecto WHERE proyecto.nombreproyecto = ? " +
            "ORDER BY persona.apellidos ASC";
    private static final String SQL_INSERT = "INSERT INTO proyecto (nombreproyecto, inicio, fin, administrador) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE proyecto SET nombreproyecto = ?, inicio = ?, fin = ? " +
            "WHERE nombreproyecto = ?";
    private static final String SQL_DELETE = "DELETE FROM proyecto WHERE nombreproyecto = ?";

    public List<Proyecto> selectAllProjects() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proyecto proyecto;
        List<Proyecto> proyectos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProyecto = rs.getString("nombreproyecto");
                Date inicio = rs.getDate("inicio");
                Date fin = rs.getDate("fin");
                String administrador = rs.getString("administrador");
                proyecto = new Proyecto(nombreProyecto, inicio, fin, administrador);
                proyectos.add(proyecto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return proyectos;
    }

    public List<Proyecto> selectAll(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proyecto proyecto;
        List<Proyecto> proyectos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setString(1, persona.getEmail());
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProyecto = rs.getString("nombreproyecto");
                Date inicio = rs.getDate("inicio");
                Date fin = rs.getDate("fin");
                String administrador = rs.getString("administrador");
                proyecto = new Proyecto(nombreProyecto, inicio, fin, administrador);
                proyectos.add(proyecto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return proyectos;
    }

    public Proyecto selectOne(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proyecto proyectoRes = new Proyecto();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_BY_NOMBRE);
            ps.setString(1, proyecto.getNombreProyecto());
            rs = ps.executeQuery();
            if(rs.next()) {
                String nombreProyecto = rs.getString("nombreProyecto");
                Date inicio = rs.getDate("inicio");
                Date fin = rs.getDate("fin");
                String administrador = rs.getString("administrador");
                proyectoRes.setNombreProyecto(nombreProyecto);
                proyectoRes.setInicio(inicio);
                proyectoRes.setFin(fin);
                proyectoRes.setAdministrador(administrador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return proyectoRes;
    }

    public List<Persona> selectColaboradores(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Persona persona;
        List<Persona> personas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_COLABORADORES);
            ps.setString(1, proyecto.getNombreProyecto());
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                persona = new Persona(email, nombre, apellidos);
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

    public int insert(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setDate(2, proyecto.getInicio());
            ps.setDate(3, proyecto.getInicio());
            ps.setString(4, proyecto.getAdministrador());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setDate(2, proyecto.getInicio());
            ps.setDate(3, proyecto.getFin());
            ps.setString(4, proyecto.getNombreProyecto());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setString(1, proyecto.getNombreProyecto());
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