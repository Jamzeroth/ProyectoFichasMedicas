
package proyectofichasmedicas;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import UpperEssential.UpperEssentialLookAndFeel;

public class ProyectoFichasMedicas {
    public static void main(String[] args){
        try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	} catch (Exception e) {
		e.printStackTrace();
	}
 
        /*VentanaPrincipal inisecion=new  VentanaPrincipal();
        inisecion.setLocation(100, 150);
        inisecion.setVisible(true);*/
        IniciarSesion ingresar = new IniciarSesion();
        ingresar.setLocation(100, 150);
        ingresar.setVisible(true);
    }
}
