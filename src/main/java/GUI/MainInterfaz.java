package GUI;
import Controlador.GestorUsuario;
import Modelo.Usuario;
import Vista.Menu;
import Controlador.GestorApp;
import javax.swing.*;

public class MainInterfaz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorUsuario gestor=new GestorUsuario();
            String usuario = JOptionPane.showInputDialog(null, "Ingrese su nombre de usuario:");
            String clave = JOptionPane.showInputDialog(null, "Ingrese su contraseña:");
            if (usuario != null && !usuario.trim().isEmpty() && clave != null && clave.trim().isEmpty()) {
                if (gestor.autenticarUsuario(usuario,clave)){
                    Usuario user=new Usuario(usuario, clave);
                    GestorApp app=new GestorApp(user);
                    Menu menu=new Menu(app);
                    new MenuPrincipal(menu).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null,"Error: Usuario o contraseña no encontrado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nombre de usuario y contraseña requeridas.");
            }
        });
    }
}
