package utils;

import modelo.dto.CarreraDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    public static Connection conectar() {
        Connection con = null;

        String user = "root";
        String pass = "";
        String url = "jdbc:mysql://localhost:3306/EscuelaWeb";
        String driverDB = "com.mysql.cj.jdbc.Driver";
        try{
            Class.forName(driverDB);
            con = DriverManager.getConnection(url,user,pass);
        }catch (Exception ex){
            Logger.getLogger(CarreraDTO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return con;
    }
}