package GUI;

import Vista.Menu;

import javax.swing.*;

public class MenuMostrarTarjeta extends MenuPrincipal {

    public MenuMostrarTarjeta(Menu menu){
        super(menu);
        setTitle("Gestor de Gastos");
        getContentPane().removeAll();
        repaint();
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes(){
        menu.menuTarjeta();
        JButton volver = new JButton("Volver");
        volver.setBounds(140, 200, 100, 30);
        volver.addActionListener(e -> {
            new MenuPrincipal(menu).setVisible(true);
            dispose();
        });
        add(volver);
    }
}