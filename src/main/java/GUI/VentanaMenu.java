package GUI;

import Modelo.Usuario;
import Vista.Menu;

import javax.swing.*;

public class VentanaMenu extends JFrame {
    protected final Usuario usuario;
    protected final Menu menu;

    public VentanaMenu(Menu menu, Usuario usuario) {
        this.usuario = usuario;
        this.menu = menu;
        setTitle("Bienvenido al sistema de gestión");
        configurarVentana();
    }

    protected void configurarVentana() {
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void inicializarComponentes() {
    }
}