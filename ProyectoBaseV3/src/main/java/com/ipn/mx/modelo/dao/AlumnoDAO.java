package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AlumnoDTO;
import com.ipn.mx.modelo.dto.CarreraDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class AlumnoDAO {

    private static final String SQL_INSERT = "insert into alumno (nombreAlumno, paternoAlumno, maternoAlumno, emailAlumno, idCarrera) "
            + " values(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update alumno set nombreAlumno = ?, paternoAlumno = ?, maternoAlumno = ?, emailAlumno = ?, idCarrera = ? "
            + " where idAlumno = ?";
    private static final String SQL_DELETE = "delete from Alumno where idAlumno = ?";

    private static final String SQL_SELECT = "select * from Alumno where idAlumno = ?";
    private static final String SQL_SELECT_ALL = "select * from Alumno";
    private static String SQL_SELECT_NOMBRECARRERA = "select nombreCarrera from Carrera where idCarrera=?";
    private static String SQL_SELECT_IDCARRERA = "select idCarrera from Carrera where nombreCarrera=?";

    private Connection conexion;

    public AlumnoDAO() {
    }

    private void obtenerConexion() {
        //obtener conexion
        String usuario = "root";
        String clave = "";
        String url = "jdbc:mysql://localhost:3307/escuelaweb1?serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
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

    public void create(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreAlumno());
            ps.setString(2, dto.getEntidad().getPaternoAlumno());
            ps.setString(3, dto.getEntidad().getMaternoAlumno());
            ps.setString(4, dto.getEntidad().getEmailAlumno());
            ps.setInt(5, dto.getEntidad().getIdCarrera());
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

    public void update(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombreAlumno());
            ps.setString(2, dto.getEntidad().getPaternoAlumno());
            ps.setString(3, dto.getEntidad().getMaternoAlumno());
            ps.setString(4, dto.getEntidad().getEmailAlumno());
            ps.setInt(5, dto.getEntidad().getIdCarrera());
            ps.setInt(6, dto.getEntidad().getIdAlumno().intValue());
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

    public void delete(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdAlumno().intValue());
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
    public AlumnoDTO read(AlumnoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = null;
        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setLong(1, dto.getEntidad().getIdAlumno());
            rs = ps.executeQuery();
            lista = obtenerResultados(rs);
            if (!lista.isEmpty()){
                return (AlumnoDTO) lista.get(0);
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

    public static void main(String[] args) {
        AlumnoDTO dto = new AlumnoDTO();
        dto.getEntidad().setNombreAlumno("Ingenieria en Inteligemncia Artificial");
        dto.getEntidad().setPaternoAlumno("Cosas de Inteligencia Artificial");
        dto.getEntidad().setIdAlumno(1L);

        AlumnoDAO dao = new AlumnoDAO();
        try {
            System.out.println(dao.readAll());
            //dao.create(dto);
            //dao.delete(dto);
        } catch (SQLException ex) {
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

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while (rs.next()) {
            AlumnoDTO dto = new AlumnoDTO();
            dto.getEntidad().setIdAlumno(rs.getLong("idAlumno"));
            dto.getEntidad().setNombreAlumno(rs.getString("nombreAlumno"));
            dto.getEntidad().setPaternoAlumno(rs.getString("paternoAlumno"));
            dto.getEntidad().setMaternoAlumno(rs.getString("maternoAlumno"));
            dto.getEntidad().setEmailAlumno(rs.getString("emailAlumno"));
            dto.getEntidad().setIdCarrera(rs.getInt("idCarrera"));
            resultados.add(dto);
        }
        return resultados;
    }

}

