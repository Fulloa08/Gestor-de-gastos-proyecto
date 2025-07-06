package GUI;

import javax.swing.*;

import Modelo.Usuario;
import Vista.Menu;

public class MenuPrincipal extends VentanaMenu {
    protected final Usuario usuario;
    protected final Menu menu;

    public MenuPrincipal(Menu menu, Usuario usuario) {
        super(menu,usuario);
        this.usuario=usuario;
        this.menu = menu;
        setTitle("Bienvenido al sistema de gestión");
        configurarVentana();
        inicializarComponentes();
    }

    protected void configurarVentana() {
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void inicializarComponentes() {
        JLabel titulo = new JLabel("Seleccione una opción:");
        titulo.setBounds(100, 20, 200, 30);
        add(titulo);

        String[] opciones = {
                "1. Gestor Gastos",
                "2. Meta Mensual",
                "3. Analisis Financiero",
                "4. Buscar Gastos",
                "5. Tarjeta Bancaria",
                "6. Salir"
        };

        for (int i = 0; i < opciones.length; i++) {
            JButton boton = new JButton(opciones[i]);
            boton.setBounds(100, 60 + (i * 40), 200, 30);
            final int opcion = i + 1;
            boton.addActionListener(e -> lanzarOpcion(opcion));
            add(boton);
        }
    }

    protected void lanzarOpcion(int opcion) {
        JFrame siguiente = switch (opcion) {
            case 1 -> new MenuGestorGastos(menu,usuario);
            case 2 -> new MenuMeta(menu,usuario);
            case 3 -> new MenuAnalisisFinanciero(menu,usuario);
            case 4 -> new MenuBuscador(menu,usuario);
            case 5 -> new MenuMostrarTarjeta(menu,usuario);
            default -> null;
        };
        if (opcion == 6) System.exit(0);
        if (siguiente != null) {
            siguiente.setVisible(true);
            this.dispose();
        }
    }
}