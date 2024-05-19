/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp13_lab1;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author helma
 */
public class TP13_LAB1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //cargamos el Driver
            Class.forName("org.mariadb.jdbc.Driver");
            //establecemos conexion
            Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tp_13", "root", "");
            Statement st = conexion.createStatement();
            //insertar 3 alumnos
//            String sql = "INSERT INTO  alumno (`dni`, `apellido`, `nombre`, `fechaNacimiento`, `estado`) VALUES "
//                    + "(12345678, 'Gomez', 'Juan', '2000-01-15', 1), "
//                    + "(87654321, 'Perez', 'Maria', '1999-05-20', 1), "
//                    + "(45678901, 'Lopez', 'Carlos', '2001-09-30', 1);";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TP13_LAB1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("No se pudo conectar");
        }
    }

}
