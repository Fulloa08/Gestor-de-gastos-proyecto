package GUI;

import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {
    protected final Usuario usuario;

    public VentanaMenu(Usuario usuario) {
        this.usuario = usuario;
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