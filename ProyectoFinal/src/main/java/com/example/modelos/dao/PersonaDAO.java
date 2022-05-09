package com.example.modelos.dao;

import com.example.modelos.dto.PersonaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAO {

    private final String SQL_INSERT = "";
    private final String SQL_SELECT = "";
    private final String SQL_SELECT_ALL = "";
    private final String SQL_UPDATE = "";
    private Connection connection;

    public PersonaDAO(){

    }

    public void getConnection(){
        String usuario = "root";
        String clave = "";
        String url = "jdbc:mysql://localhost:3306/EscuelaWeb?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        String driverBD = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverBD);
            connection = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insert(PersonaDTO dto) throws SQLException{
        PreparedStatement ps = null;
        try{
            getConnection();
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1,dto.getEntidad().getEmail());
            ps.setString(2,dto.getEntidad().getNombre());
            ps.setString(3,dto.getEntidad().getApellidos());
            ps.setString(4,dto.getEntidad().getPassword());
            return ps.execute();
        }finally {
            if(connection != null){
                connection.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }

    public PersonaDTO select(PersonaDTO dto) throws SQLException{
        PersonaDTO res = new PersonaDTO();
        PreparedStatement ps = null;
        try{
            getConnection();
            ps = connection.prepareStatement(SQL_SELECT);
            ps.setInt(1,dto.getEntidad().getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                res.getEntidad().setApellidos(rs.getString("apellidos"));
                res.getEntidad().setEmail(rs.getString("email"));
                res.getEntidad().setNombre(rs.getString("nombre"));
                res.getEntidad().setId(rs.getInt("id"));
                res.getEntidad().setPassword(rs.getString("password"));
            }else{
                return null;
            }
            rs.close();
            return res;
        }finally {
            if(connection != null){
                connection.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }

    public boolean update(PersonaDTO dto) throws SQLException{
        PreparedStatement ps = null;
        try{
            getConnection();
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1,dto.getEntidad().getEmail());
            ps.setString(2,dto.getEntidad().getNombre());
            ps.setString(3,dto.getEntidad().getApellidos());
            ps.setString(4,dto.getEntidad().getPassword());
            ps.setInt(5,dto.getEntidad().getId());
            return ps.execute();
        }finally {
            if(connection != null){
                connection.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }

    public boolean delete(PersonaDTO dto) throws SQLException{
        PreparedStatement ps = null;
        try{
            getConnection();
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setInt(1,dto.getEntidad().getId());
            return ps.execute();
        }finally {
            if(connection != null){
                connection.close();
            }
            if(ps != null){
                ps.close();
            }
        }
    }

}
