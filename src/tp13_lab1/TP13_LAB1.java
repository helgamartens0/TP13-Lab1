/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp13_lab1;


import java.sql.*;
import javax.swing.JOptionPane;

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
            //Statement st = conexion.createStatement();
            //insertar 3 alumnos
            String sql = "INSERT INTO  alumno (`dni`, `apellido`, `nombre`, `fechaNacimiento`, `estado`) VALUES "
                    + "(12345678, 'Gomez', 'Juan', '2000-01-15', 1), "
                    + "(87654321, 'Perez', 'Maria', '1999-05-20', 1), "
                    + "(45678901, 'Lopez', 'Carlos', '2001-09-30', 1);";

            PreparedStatement ps = conexion.prepareStatement(sql);
            int res = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, res + " filas afectadas.");
            
            //insertar 4 materias
            sql = "INSERT INTO `materia`(`nombre`, `anio`, `estado`) VALUES "
                    + "('Matemáticas', 1, true), "
                    + "('Historia', 2, true), "
                    + "('Física', 3, true), "
                    + "('Literatura', 4, true);";
            
            ps = conexion.prepareStatement(sql);
            res = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, res + " filas afectadas.");
            
            //inscribir a los 3 alumnos en 2 materias cada uno
            sql = "INSERT INTO `inscripcion`(`id_inscripto`, `nota`, `id_alumno`, `id_materia`) VALUES "
                    + "(1,8,1,1), (2,5,1,2), "
                    +"(3,6,2,3), (4,8,2,4), "
                    +"(5,9,3,1),(6,4,3,3) ";
            
            ps = conexion.prepareStatement(sql);
             res = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, res + " filas afectadas");
            
            //          LISTAR ALUMNOS CON NOTAS MAYOR A 8
            sql="SELECT DISTINCT alumno.nombre, alumno.apellido,  materia.nombre as materia,inscripcion.nota\n "
                    + "FROM inscripcion\n"
                    + "JOIN alumno ON inscripcion.id_Alumno = alumno.id_Alumno\n"
                    + "JOIN materia ON inscripcion.id_Materia = materia.id_Materia\n"
                    + "WHERE inscripcion.nota > 8";
            ps= conexion.prepareStatement(sql);
            ResultSet resultado=ps.executeQuery();
            while(resultado.next()){
                System.out.println("Nombre: "+ resultado.getString("nombre"));
                System.out.println("Apellido: "+ resultado.getString("apellido"));
                System.out.println("Materia: "+ resultado.getString("materia"));
                System.out.println("Nota: "+ resultado.getInt("nota"));
            }
 
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver. " + ex);
        } catch (SQLException ex) {
            System.out.println("No se pudo conectar. " + ex);
        }
    }

}
