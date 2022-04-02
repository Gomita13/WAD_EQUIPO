package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.entidades.Usuario;

import java.sql.*;

public class UsuarioDAO {

    private static final String SQL_SELECT = "select * from Usuario where nombreUsuario=?";
    private static final String SQL_INSERT = "insert into Usuario (nombre,paterno,materno,email,nombreUsuario,claveUsuario,fechaCreacion) values (?,?,?,?,?,?,?)";
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
            System.out.println("ERROR (UsuarioDAO.obtenerConexion): ");
            ex.printStackTrace();
        }
    }

    public UsuarioDTO login(UsuarioDTO dto) throws SQLException {
        UsuarioDTO exists = null;
        PreparedStatement ps = null;
        obtenerConexion();
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setString(1,dto.getEntidad().getNombreUsuario());
            String contra = dto.getEntidad().getClaveUsuario();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(dto.toString());
                System.out.println(rs.getString("claveUsuario"));
                if(contra.equals(rs.getString("claveUsuario"))){
                    exists = new UsuarioDTO();
                    exists.getEntidad().setNombreUsuario(rs.getString("nombreUsuario"));
                    exists.getEntidad().setNombre(rs.getString("nombre"));
                    exists.getEntidad().setPaterno(rs.getString("paterno"));
                    exists.getEntidad().setMaterno(rs.getString("materno"));
                    exists.getEntidad().setEmail(rs.getString("email"));
                    exists.getEntidad().setIdUsuario(rs.getInt("idUsuario"));
                }
            }
            return exists;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void insert(UsuarioDTO dto) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1,dto.getEntidad().getNombre());
            ps.setString(2,dto.getEntidad().getPaterno());
            ps.setString(3,dto.getEntidad().getMaterno());
            ps.setString(4,dto.getEntidad().getEmail());
            ps.setString(5,dto.getEntidad().getNombreUsuario());
            ps.setString(6,dto.getEntidad().getClaveUsuario());
            ps.setDate(7,dto.getEntidad().getFechaDeCreacion());
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

}
