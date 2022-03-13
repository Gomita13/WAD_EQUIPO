package modelo.dto;

import modelo.entidades.Carrera;

import java.io.Serializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarreraDTO implements Serializable {

    private Carrera entidad;
    private static Connection conexion;

    public CarreraDTO () {
        entidad = new Carrera();
    }

    public Carrera getEntidad(){
        return entidad;
    }

    public void setEntidad(Carrera entidad){
        this.entidad = entidad;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Clave de la carrera: ").append(entidad.getIdCarrera()).append("\n");
        sb.append("Nombre de la carrera: ").append(entidad.getNombreCarrera()).append("\n");
        sb.append("Descripcion de la carrera: ").append(entidad.getDescripcionCarrera()).append("\n");
        return sb.toString();
    }

    public static void main(String args[]){
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.getEntidad().setIdCarrera(1L);
        carreraDTO.getEntidad().setNombreCarrera("Ingenieria Sistemas Computacionales");
        carreraDTO.getEntidad().setDescripcionCarrera("Una carrera bien qlera la verdad mano. Te gustan las monas chinas?");
        System.out.println(carreraDTO.toString());

        obtenerConexion();

        try {
            PreparedStatement pst = conexion.prepareStatement("SELECT * FROM carrera");
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("nombreCarrera"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void obtenerConexion(){
        String user = "root";
        String pass = "";
        String url = "jdbc:mysql://localhost:3306/EscuelaWeb";
        String driverDB = "com.mysql.cj.jdbc.Driver";
        try{
            Class.forName(driverDB);
            conexion = DriverManager.getConnection(url,user,pass);
        }catch (Exception ex){
            Logger.getLogger(CarreraDTO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
