package Launcher;

import Controlador.GestorUsuario;
import GUI.MenuPrincipal;
import Modelo.Usuario;

import javax.swing.*;

public class MainInterfaz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            while (true) {
                JTextField usuarioField = new JTextField();
                JPasswordField claveField = new JPasswordField();
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Nombre de usuario:"));
                panel.add(usuarioField);
                panel.add(Box.createVerticalStrut(10));
                panel.add(new JLabel("Contraseña:"));
                panel.add(claveField);
                int result = JOptionPane.showOptionDialog(
                        null,
                        panel,
                        "Inicio de sesión",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new String[]{"Iniciar sesión", "Cancelar"},
                        "Iniciar sesión"
                );

                if (result != JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, "Proceso cancelado.");
                    break;
                }

                String usuario = usuarioField.getText().trim();
                String clave = new String(claveField.getPassword()).trim();

                if (usuario.isEmpty() || clave.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario y contraseña requeridos.");
                    continue;
                }

                if (GestorUsuario.autenticarUsuario(usuario, clave)) {
                    Usuario user = new Usuario(usuario, clave);
                    new MenuPrincipal(user).setVisible(true);
                    break;
                } else {
                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            "Usuario o contraseña incorrectos.\n¿Desea registrarse como un nuevo usuario?",
                            "Registro",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                        boolean exito = GestorUsuario.registrarUsuario(usuario, clave);
                        if (exito) {
                            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                            Usuario user = new Usuario(usuario, clave);
                            new MenuPrincipal(user).setVisible(true);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: El usuario ya existe.");
                        }
                    }
                }
            }
        });
    }
}
