package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.Conexion;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Proyecto;
import com.ipn.mx.modelo.entidades.Tarea;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private static final String INSERT_COLABORADOR = "INSERT INTO colaborador (nombreproyecto, emailpersona) VALUES " +
            "(?, ?)";
    private static final String SQL_UPDATE = "UPDATE proyecto SET nombreproyecto = ?, inicio = ?, fin = ? " +
            "WHERE nombreproyecto = ?";
    private static final String SQL_DELETE = "DELETE FROM proyecto WHERE nombreproyecto = ?";
    private static final String DELETE_COLABORADOR = "DELETE FROM colaborador WHERE nombreproyecto = ? AND " +
            "emailpersona = ?";

    public List<Proyecto> selectAllProjects() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proyecto proyecto;
        List<Proyecto> proyectos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_PROJECTS);
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
                List<Tarea> tareas = new TareaDAO().selectTareas(proyecto);
                List<Tarea> tareasCompletadas = new ArrayList<>();
                for (Tarea tarea : tareas) {
                    if (tarea.isCompletada()) {
                        tareasCompletadas.add(tarea);
                    }
                }
                proyecto.setProgreso(tareasCompletadas.size(), tareas.size());
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
            ps.setDate(3, proyecto.getFin());
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

    public int insertColaborador(Proyecto proyecto, Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(INSERT_COLABORADOR);
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setString(2, persona.getEmail());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(Proyecto proyecto, String proyectoOld) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setDate(2, proyecto.getInicio());
            ps.setDate(3, proyecto.getFin());
            ps.setString(4, proyectoOld);
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

    public int deleteColaborador(Proyecto proyecto, Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(DELETE_COLABORADOR);
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setString(2, persona.getEmail());
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