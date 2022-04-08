package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darkdestiny
 */
public class CategoriaDAO {
    private static final String SQL_INSERT="{call spInsertar( ?, ?)}";
    private static final String SQL_UPDATE="{call spActualizar (?, ? ,?)}";
    private static final String SQL_DELETE="{call spEliminar(?)}";
    private static final String SQL_READ="{call spSeleccionarUno(?)}";
    private static final String SQL_READ_ALL="{call spSeleccionarTodo()}";

    private Connection conexion;

    private void obtenerConexion() {
        //obtener conexion
        String usuario = "ezja";
        String clave = "ezja";
        String url = "jdbc:mysql://localhost:3306/ProyectoBase4?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        //String url = "jdbc:mysql://localhost:3306/EscuelaWeb?
        //serverTimeZone=America/Mexico_City&allowPublicKeyRetrieval=true&
        //useSSL=false";

        String driverBD = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void create(CategoriaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = conexion.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
            cs.executeUpdate();
        }finally{
            if (cs != null) cs.close();
            if (conexion != null) conexion.close();
        }
    }
    public void update(CategoriaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = conexion.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
            cs.setInt(3, dto.getEntidad().getIdCategoria());
            cs.executeUpdate();
        }finally{
            if (cs != null) cs.close();
            if (conexion != null) conexion.close();
        }
    }
    public void delete(CategoriaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = conexion.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            cs.executeUpdate();
        }finally{
            if (cs != null) cs.close();
            if (conexion != null) conexion.close();
        }
    }
    public CategoriaDTO read(CategoriaDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = conexion.prepareCall(SQL_READ);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0){
                return (CategoriaDTO) resultados.get(0);
            }else{
                return null;
            }
        }finally{
            if (cs != null) cs.close();
            if (conexion != null) conexion.close();
        }
    }
    public List readAll() throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        try{
            cs = conexion.prepareCall(SQL_READ_ALL);
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0){
                return  resultados;
            }else{
                return null;
            }
        }finally{
            if (cs != null) cs.close();
            if (conexion != null) conexion.close();
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {
            CategoriaDTO dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            dto.getEntidad().setNombreCategoria(rs.getString("nombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(rs.getString("descripcionCategoria"));
            resultados.add(dto);
        }
        return resultados;
    }


    public static void main(String[] args) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        //dto.getEntidad().setNombreCategoria("Línea Blanca");
        //dto.getEntidad().setDescripcionCategoria("Artículos de Línea Blanca");
        dto.getEntidad().setIdCategoria(3);
        try {
            //dao.create(dto);
            //dao.update(dto);
            System.out.println(dao.readAll());
            //System.out.println(dao.read(dto));
            //dao.delete(dto);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}