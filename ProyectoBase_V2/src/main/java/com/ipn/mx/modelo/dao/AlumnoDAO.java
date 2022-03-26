/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AlumnoDTO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darkdestiny
 */
public class AlumnoDAO {

    private static String SQL_INSERT_ALUMNO = "insert into Alumno (nombreAlumno, paternoAlumno, maternoAlumno, emailAlumno, idCarrera) values (?,?,?,?,?)";
    private static String SQL_UPDATE_ALUMNO = "update Alumno set nombreAlumno=?, paternoAlumno=?, maternoAlumno=?, emailAlumno=?, idCarrera=? where idAlumno=?";
    private static String SQL_SELECT_IDCARRERA = "select idCarrera from Carrera where nombreCarrera=?";
    private static String SQL_SELECT_ALUMNO = "select * from Alumno where idAlumno=?";
    private static String SQL_SELECT_NOMBRECARRERA = "select nombreCarrera from Carrera where idCarrera=?";

    public AlumnoDAO(){}

    private Connection conexion;

    private void obtenerConexion() {
        //obtener conexion
        String usuario = "root";
        String clave = "";
        String url = "jdbc:mysql://localhost:3306/EscuelaWeb?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        //String url = "jdbc:mysql://localhost:3306/EscuelaWeb?
        //serverTimeZone=America/Mexico_City&allowPublicKeyRetrieval=true&
        //useSSL=false";

        String driverBD = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int selectCarrera(String nombre) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_IDCARRERA);
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt("idCarrera");
            }
            return 0;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public String selectCarrera(int id) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_NOMBRECARRERA);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getString("nombreCarrera");
            }

            return null;

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void create(AlumnoDTO dto) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT_ALUMNO);
            ps.setString(1,dto.getEntidad().getNombreAlumno());
            ps.setString(2,dto.getEntidad().getPaternoAlumno());
            ps.setString(3,dto.getEntidad().getMaternoAlumno());
            ps.setString(4,dto.getEntidad().getEmailAlumno());
            ps.setInt(5,dto.getEntidad().getIdCarrera());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void update(AlumnoDTO dto) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE_ALUMNO);
            ps.setString(1,dto.getEntidad().getNombreAlumno());
            ps.setString(2,dto.getEntidad().getPaternoAlumno());
            ps.setString(3,dto.getEntidad().getMaternoAlumno());
            ps.setString(4,dto.getEntidad().getEmailAlumno());
            ps.setInt(5,dto.getEntidad().getIdCarrera());
            ps.setLong(6,dto.getEntidad().getIdAlumno());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public AlumnoDTO select(AlumnoDTO dto) throws SQLException{
        AlumnoDTO res = new AlumnoDTO();
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_ALUMNO);
            ps.setLong(1,dto.getEntidad().getIdAlumno());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                res.getEntidad().setIdAlumno(dto.getEntidad().getIdAlumno());
                res.getEntidad().setNombreAlumno(rs.getString("nombreAlumno"));
                res.getEntidad().setPaternoAlumno(rs.getString("paternoAlumno"));
                res.getEntidad().setMaternoAlumno(rs.getString("maternoAlumno"));
                res.getEntidad().setEmailAlumno(rs.getString("emailAlumno"));
                res.getEntidad().setIdCarrera(rs.getInt("idCarrera"));
            }
            return res;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
}
