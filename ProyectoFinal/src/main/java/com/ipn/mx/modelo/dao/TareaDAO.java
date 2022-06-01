package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.Conexion;
import com.ipn.mx.modelo.entidades.Persona;
import com.ipn.mx.modelo.entidades.Proyecto;
import com.ipn.mx.modelo.entidades.Tarea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {
    private static final String SQL_SELECT = "SELECT * FROM tarea";
    // Estas son las tareas pendientes o completadas de un proyecto (sin importar el encargado)
    private static final String SELECT_BY_PROYECTO = "SELECT * FROM tarea WHERE nombreproyecto = ?";
    // Esta es la que se muestra en el dashboard
    private static final String SELECT_BY_ENCARGADO = "SELECT * FROM tarea WHERE encargado = ?";
    // Esta es la que se muestra en Proyecto con sus tareas en la parte de "mis tareas"
    private static final String SELECT_BY_ENCARGADO_PROYECTO = "SELECT * FROM tarea WHERE encargado = ? " +
            "AND nombreproyecto = ? ORDER BY completada ASC";
    private static final String SQL_UPDATE = "UPDATE tarea SET nombretarea = ?, nombreproyectp = ?, encargado = ?, " +
            "descripcion = ? WHERE nombretarea = ?";
    private static final String SQL_DELETE = "DELETE FROM tarea WHERE nombretarea = ?";

    public List<Tarea> selectTareas(Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_BY_PROYECTO);
            ps.setString(1, proyecto.getNombreProyecto());
            rs = ps.executeQuery();
            addTareas(rs, tareas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return tareas;
    }

    public List<Tarea> selectTareasEncargado(Persona persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ENCARGADO);
            ps.setString(1, persona.getEmail());
            rs = ps.executeQuery();
            addTareas(rs, tareas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return tareas;
    }

    public List<Tarea> selectTareasEncargadoProyecto (Persona persona, Proyecto proyecto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ENCARGADO_PROYECTO);
            ps.setString(1, persona.getEmail());
            ps.setString(2, proyecto.getNombreProyecto());
            rs = ps.executeQuery();
            addTareas(rs, tareas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return tareas;
    }

    public int update(Tarea tarea) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, tarea.getNombreTarea());
            ps.setString(2, tarea.getNombreProyecto());
            ps.setString(3, tarea.getEncargado());
            ps.setString(4, tarea.getDescripcion());
            ps.setString(5, tarea.getNombreTarea());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(Tarea tarea) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setString(1, tarea.getNombreTarea());
            rows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return rows;
    }

    private void addTareas(ResultSet rs, List<Tarea> tareas) throws SQLException {
        Tarea tarea;
        while (rs.next()) {
            String nombreTarea = rs.getString("nombretarea");
            String nombreProyecto = rs.getString("nombreproyecto");
            String encargado = rs.getString("encargado");
            String descripcion = rs.getString("descripcion");
            boolean completada = rs.getBoolean("completada");
            tarea = new Tarea(nombreTarea, nombreProyecto, encargado, descripcion, completada);
            tareas.add(tarea);
        }
    }
}
