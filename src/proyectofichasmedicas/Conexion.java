/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectofichasmedicas;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author USUARIO
 */
public class Conexion {
     Connection cn=null;
     
     public Connection Conectar(){
         try {
             Class.forName("com.mysql.jdbc.Driver");
             cn=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","mysql");
             //JOptionPane.showMessageDialog(null,"Conexión Exitosa");
         } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Error en el Driver!");
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al Conectar a la base de datos!");
         }
      return cn;   
     }
    public Connection getConnection(){
    return cn;
   }
}
