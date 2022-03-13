package modelo.dao;

import modelo.dto.CarreraDTO;
import utils.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarreraDAO {

    private static final String SQL_INSERT = "INSERT INTO Carrera (nombreCarrera, descripcionCarrera) VALUES (?,?)";
    private static final String SQL_UPDATE = "UPDATE Carrera SET nombreCarrera = ?, descripcionCarrera = ? WHERE idCarrera = ? ";
    private static final String SQL_DELETE = "DELETE FROM Carrera WHERE idCarrera = ?";
    private static final String SQL_SELECT = "SELECT * FROM Carrera WHERE idCarrera = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM Carrera";

    private Connection conexion;

    public CarreraDAO(){}

    public int create(CarreraDTO dto) throws SQLException{
        conexion = Conexion.conectar();
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1,dto.getEntidad().getNombreCarrera());
            ps.setString(2,dto.getEntidad().getDescripcionCarrera());
            return ps.executeUpdate();
        }finally {
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }

    public int update(CarreraDTO dto) throws SQLException{
        PreparedStatement ps = null;
        try{
            conexion = Conexion.conectar();
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1,dto.getEntidad().getNombreCarrera());
            ps.setString(2,dto.getEntidad().getDescripcionCarrera());
            ps.setInt(3,(int)dto.getEntidad().getIdCarrera());
            return ps.executeUpdate();
        }finally {
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }

    public int delete(CarreraDTO dto) throws SQLException{
        PreparedStatement ps = null;
        try{
            conexion = Conexion.conectar();
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1,(int)dto.getEntidad().getIdCarrera());
            return ps.executeUpdate();
        }finally {
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }

    public CarreraDTO read(CarreraDTO dto) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        CarreraDTO res = new CarreraDTO();

        try{
            conexion = Conexion.conectar();
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1,(int)dto.getEntidad().getIdCarrera());
            rs = ps.executeQuery();
            while(rs.next()){
                res.getEntidad().setIdCarrera(rs.getLong("idCarrera"));
                res.getEntidad().setNombreCarrera(rs.getString("nombreCarrera"));
                res.getEntidad().setDescripcionCarrera(rs.getString("descripcionCarrera"));
            }
            return res;
        }finally {
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }

    public ArrayList<CarreraDTO> readAll() throws SQLException {
        ArrayList<CarreraDTO> carreras = new ArrayList<>();
        PreparedStatement ps = null;
        try{
            conexion = Conexion.conectar();
            ps = conexion.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CarreraDTO dto = new CarreraDTO();
                dto.getEntidad().setIdCarrera(rs.getLong("idCarrera"));
                dto.getEntidad().setNombreCarrera(rs.getString("nombreCarrera"));
                dto.getEntidad().setDescripcionCarrera(rs.getString( "descripcionCarrera"));
                carreras.add(dto);
            }

            if(carreras.size() > 0){
                return carreras;
            }else{
                return null;
            }

        }finally {
            if(ps != null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }

}
