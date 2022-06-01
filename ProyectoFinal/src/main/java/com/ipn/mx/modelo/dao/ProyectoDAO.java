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
    private static final String SQL_SELECT = "SELECT * FROM proyecto";
    private static final String SELECT_BY_NOMBRE = "SELECT * FROM proyecto WHERE nombreproyecto = ?";
    private static final String SQL_INSERT = "INSERT INTO proyecto (nombreproyecto, inicio, fin) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE proyecto SET nombreproyecto = ?, inicio = ?, fin = ? " +
            "WHERE nombreproyecto = ?";
    private static final String SQL_DELETE = "DELETE FROM proyecto WHERE nombreproyecto = ?";

    public List<Proyecto> selectAll() {
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
                String nombreProyecto = rs.getString("email");
                Date inicio = rs.getDate("inicio");
                Date fin = rs.getDate("fin");
                proyecto = new Proyecto(nombreProyecto, inicio, fin);
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
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SELECT_BY_NOMBRE);
            ps.setString(1, proyecto.getNombreProyecto());
            rs = ps.executeQuery();
            rs.absolute(1); // Nos posicionamos en el primer registro que devuelve
            String nombreProyecto = rs.getString("nombreProyecto");
            Date inicio = rs.getDate("inicio");
            Date fin = rs.getDate("fin");
            proyecto.setNombreProyecto(nombreProyecto);
            proyecto.setInicio(inicio);
            proyecto.setFin(fin);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }
        return proyecto;
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