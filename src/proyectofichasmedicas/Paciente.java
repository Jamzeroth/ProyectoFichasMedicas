/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectofichasmedicas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class Paciente {
        Statement st;
     Conexion conex;
     String idfila="";
    
    public Paciente(){
      conex=new Conexion();
     
  } 
    
    public void InsertarPaciente(String cod,String ced, String nom, String apellpat, String apellmat, String estcivil,  String correo, String direccion, String celular,String fechanac   ){
        String[] fp=fechanac.split("/");
        String fmysql=fp[2]+"-"+fp[1]+"-"+fp[0];
        Connection cn=conex.Conectar();
        String cad="insert into paciente(codigo_pac,cedula_pac,nombre_pac,apellidopat_pac,apellidomat_pac,estadocivil_pac,fechanac_pac,celular_pac,correoelect_pac,direccion_pac) values('"+
                cod+"','"+ced+"','"+nom+"','"+apellpat+"','"+apellmat+"','"+estcivil+"','"+fmysql+"','"+celular+"','"+correo+"','"+direccion+"')";
        try {
            st=cn.createStatement();

            st.executeUpdate(cad);
            JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente");
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EliminarPaciente(String cod){
         Connection cn=conex.Conectar();
       try {                
                PreparedStatement pstm = conex.getConnection().prepareStatement("delete from paciente where codigo_pac = ?");            
                pstm.setString(1, cod);                   
                pstm.execute();
                 JOptionPane.showMessageDialog(null, "Usuario fue eliminado exitosamente");
                pstm.close();            
            }catch(SQLException e){
                 
               System.out.println(e);
            }         
}         
      
  public void ModificarPaciente(String cod, String ced, String nom, String apellpat, String apellmat, String estcivil, String correo, String direccion, String celular,String fechanac ){
         Connection cn=conex.Conectar();
       try {                
                PreparedStatement pstm = conex.getConnection().prepareStatement("update paciente set cedula_pac=?,nombre_pac=?,apellidopat_pac=?,apellidomat_pac=?,estadocivil_pac=?,fechanac_pac=?,celular_pac=?,correoelect_pac=?,direccion_pac=? where codigo_pac= ?");            

                                                           
                pstm.setString(1, ced);
                pstm.setString(2, nom);
                pstm.setString(3, apellpat);
                pstm.setString(4, apellmat);
                pstm.setString(5,estcivil);  
                pstm.setString(6, fechanac); 
               
                pstm.setString(7, celular);
                pstm.setString(8, correo);
                pstm.setString(9,direccion);  
                pstm.setString(10, cod); 

                pstm.execute();
                  JOptionPane.showMessageDialog(null, "Paciente modificado exitosamente");
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
