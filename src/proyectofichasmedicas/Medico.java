package proyectofichasmedicas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Medico {

    Statement st;
    Conexion conex;
    String idfila = "";

    public Medico() {
        conex = new Conexion();

    }

    public void InsertarDoctor(String ci, String nom, String ape, String tlf, String esp, String usu, String pass) {
        Connection cn = conex.Conectar();
        String cad = "insert into medico(CI,Nombre,Apellido,Titulo,Telefono,Usuario,Pass) values('"
                + ci + "','" + nom + "','" + ape + "','" + tlf + "','" + esp + "','" + usu + "','" + pass + "')";
        try {
            st = cn.createStatement();
            st.executeUpdate(cad);
            JOptionPane.showMessageDialog(null, "Medico registrado exitosamente");
        } catch (SQLException ex) {
            Logger.getLogger(Medico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EliminarDoctor(String ci) {
        Connection cn = conex.Conectar();
        try {
            PreparedStatement pstm = conex.getConnection().prepareStatement("delete from doctor where CI = ?");
            pstm.setString(1, ci);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Medico fue eliminado exitosamente");
            pstm.close();
        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    public void ModificarDoctor(String ci, String nom, String ape, String tlf, String esp) {
        Connection cn = conex.Conectar();
        try {
            PreparedStatement pstm = conex.getConnection().prepareStatement("update doctor set CI=?,Nombre=?,Apellido=?,Telefono=?,Especialidad=? where CI= ?");
            pstm.setString(2, nom);
            pstm.setString(3, ape);
            pstm.setString(4, tlf);
            pstm.setString(5, esp);

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Medico modificado exitosamente");
            pstm.close();
        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    public Object[][] getDatos() {
        int registros = 0;
        Connection con = conex.Conectar();
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = conex.getConnection().prepareStatement("SELECT count(1) as total FROM medico ");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[registros][5];

        try {
            PreparedStatement pstm = conex.getConnection().prepareStatement("SELECT "
                    + " CI,Nombre,Apellido,Telefono,Especialidad"
                    + " FROM medico"
                    + " ORDER BY  CI");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                String estCedula = res.getString("CI");
                String estNombre = res.getString("Nombre");
                String estApellido = res.getString("Apellido");
                String estTelefono = res.getString("Telefono");
                String estEspecialidad = res.getString("Especialidad");

                data[i][0] = estCedula;
                data[i][1] = estNombre;
                data[i][2] = estApellido;
                data[i][3] = estCedula;
                data[i][4] = estEspecialidad;
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }
}
