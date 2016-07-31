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
public class Usuarios {
     Statement st;
     Conexion conex;
     String idfila="";
    
    public Usuarios (){
      conex=new Conexion();
     
  } 
    
    public void InsertarUsuario(String ced, String nom, String usr, String pwd, String tip){
        Connection cn=conex.Conectar();
        String cad="insert into usuario(cedula_usr,nombre_usr,usuario_usr,contraseña_usr,tipo_usr) values('"+
                ced+"','"+nom+"','"+usr+"','"+pwd+"','"+tip+"')";
        try {
            st=cn.createStatement();
            st.executeUpdate(cad);
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarUsuario(String ced){
         Connection cn=conex.Conectar();
       try {                
                PreparedStatement pstm = conex.getConnection().prepareStatement("delete from usuario where cedula_usr = ?");            
                pstm.setString(1, ced);                   
                pstm.execute();
                 JOptionPane.showMessageDialog(null, "Usuario fue eliminado exitosamente");
                pstm.close();            
            }catch(SQLException e){
                 
               System.out.println(e);
            }         
}         
      
  public void ModificarUsuario(String ced, String nom, String usu, String pass, String tipo){
         Connection cn=conex.Conectar();
       try {                
                PreparedStatement pstm = conex.getConnection().prepareStatement("update usuario set nombre_usr=?,usuario_usr=?,contraseña_usr=?, tipo_usr=? where cedula_usr= ?");            

                
                pstm.setString(1, nom);
                pstm.setString(2, usu);
                pstm.setString(3, pass);
                pstm.setString(4,tipo);  
                pstm.setString(5, ced); 

                pstm.execute();
                  JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente");
                pstm.close();            
            }catch(SQLException e){
                 
               System.out.println(e);
            }         
}  
    
   
    
      
    
     public Object [][] getDatos(){
      int registros = 0;
       Connection con=conex.Conectar();
      //obtenemos la cantidad de registros existentes en la tabla
      try{         
         PreparedStatement pstm =conex.getConnection().prepareStatement("SELECT count(1) as total FROM usuario ");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.out.println(e);
      }
      
    Object[][] data = new String[registros][5];  
    //realizamos la consulta sql y llenamos los datos en "Object"
      try{    
         PreparedStatement pstm = conex.getConnection().prepareStatement("SELECT " +
            " cedula_usr, nombre_usr, usuario_usr, contraseña_usr, tipo_usr " +
            " FROM usuario" +
            " ORDER BY  cedula_usr");
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            String estCedula = res.getString("cedula_usr");
            String estNombre = res.getString("nombre_usr");
            String estUsuario = res.getString("usuario_usr");
            String estContraseña = res.getString("contraseña_usr");
            String estTipo = res.getString("tipo_usr");
            data[i][0] = estCedula;            
            data[i][1] = estNombre;            
            data[i][2] = estUsuario;            
            data[i][3] = estContraseña; 
            data[i][4] = estTipo; 
            i++;
         }
         res.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
 }    
    
    
    
    public ResultSet ListarUsuario(){//resultado de un solo usuario(DEspliega a el usuario)
        ResultSet rs=null;
        return rs;
    }
    
    public ResultSet ListarUsuarios(){//Despliega a todos los usarios
        ResultSet rs=null;
        return rs;
    }

   
}
