/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AlumnoDTO;
import com.ipn.mx.modelo.dto.CarreraDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darkdestiny
 */
public class Alumno {
    private static final String SQL_SELECT_ALL = "select * from Alumno";

    private Connection conexion;

    public Alumno() {
    }

    private void obtenerConexion() {
        //obtener conexion
        String usuario = "root";
        String clave = "";
        String url = "jdbc:mysql://localhost:3307/EscuelaWeb?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        String driverBD = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List readAll() throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (lista.size() > 0){
                return lista;
            }else{
                return null;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {
            AlumnoDTO dto = new AlumnoDTO();
            dto.getEntidad().setIdAlumno(rs.getLong("idAlumno"));
            dto.getEntidad().setNombreAlumno(rs.getString("nombreAlumno"));
            dto.getEntidad().setPaternoAlumno(rs.getString("paternoAlumno"));
            dto.getEntidad().setMaternoAlumno(rs.getString("maternoAlumno"));
            dto.getEntidad().setEmailAlumno(rs.getString("emailAlumno"));
            dto.getEntidad().setIdCarrera((int) rs.getLong("idCarrera"));
            resultados.add(dto);
        }
        return resultados;
    }
}
