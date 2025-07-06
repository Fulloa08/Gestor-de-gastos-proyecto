package GUI;
import Controlador.GestorApp;
import Controlador.GestorAhorro;
import Controlador.GestorDatos;
import Modelo.Usuario;
import Vista.Menu;
import javax.swing.*;
import java.awt.*;

public class MenuMeta extends VentanaMenu {
    private final Usuario usuario;
    private final GestorDatos gestorDatos;
    private final GestorAhorro gestorAhorro;

    public MenuMeta(Menu menu, Usuario usuario) {
        super(menu, usuario);
        this.usuario = usuario;
        this.gestorDatos = new GestorDatos(usuario);
        this.gestorAhorro = new GestorAhorro(gestorDatos);
        setTitle("Meta Mensual");
        getContentPane().removeAll();
        repaint();
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(null);
        JLabel lblMeta = new JLabel("Meta mensual actual:");
        lblMeta.setBounds(50, 40, 200, 25);
        add(lblMeta);
        JLabel lblMetaValor = new JLabel(String.format("$%.2f", gestorAhorro.obtenerMetaActual()));
        lblMetaValor.setBounds(220, 40, 100, 25);
        add(lblMetaValor);
        JLabel lblProgreso = new JLabel("Total gastado:");
        lblProgreso.setBounds(50, 80, 200, 25);
        add(lblProgreso);
        JLabel lblTotalGastado = new JLabel(String.format("$%.2f", gestorDatos.obtenerTotalGastado()));
        lblTotalGastado.setBounds(220, 80, 100, 25);
        add(lblTotalGastado);
        JLabel lblRestante = new JLabel("Monto restante:");
        lblRestante.setBounds(50, 120, 200, 25);
        add(lblRestante);
        double restante = gestorAhorro.obtenerMetaActual() - gestorDatos.obtenerTotalGastado();
        JLabel lblRestanteValor = new JLabel(String.format("$%.2f", restante));
        lblRestanteValor.setBounds(220, 120, 100, 25);
        add(lblRestanteValor);
        JButton btnDefinirMeta = new JButton("Definir nueva meta");
        btnDefinirMeta.setBounds(50, 170, 200, 30);
        btnDefinirMeta.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Ingrese monto de la meta mensual:", "Definir Meta", JOptionPane.PLAIN_MESSAGE);
            if (input != null && !input.trim().isEmpty()) {
                try {
                    double nuevaMeta = Double.parseDouble(input.trim());
                    gestorAhorro.definirMeta(nuevaMeta);
                    JOptionPane.showMessageDialog(this, "Meta guardada correctamente.");
                    lblMetaValor.setText(String.format("$%.2f", nuevaMeta));
                    double totalGastado = gestorDatos.obtenerTotalGastado();
                    lblTotalGastado.setText(String.format("$%.2f", totalGastado));
                    lblRestanteValor.setText(String.format("$%.2f", nuevaMeta - totalGastado));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El monto debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnDefinirMeta);
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 220, 200, 30);
        btnVolver.addActionListener(e -> {
            new MenuPrincipal(menu, usuario).setVisible(true);
            dispose();
        });
        add(btnVolver);
    }
}