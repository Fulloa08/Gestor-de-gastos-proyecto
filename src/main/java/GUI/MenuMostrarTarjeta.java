package GUI;

import Controlador.GestorDatos;
import Modelo.Tarjeta;
import Modelo.Usuario;
import Vista.Menu;

import javax.swing.*;

public class MenuMostrarTarjeta extends MenuPrincipal {
    private final Usuario usuario;
    private final GestorDatos gestorDatos;

    public MenuMostrarTarjeta(Menu menu, Usuario usuario) {
        super(menu, usuario);
        this.usuario = usuario;
        this.gestorDatos = new GestorDatos(usuario);

        setTitle("Gestión de Tarjeta");
        getContentPane().removeAll();
        repaint();
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(null);

        Tarjeta tarjeta = gestorDatos.getTarjeta();

        JLabel lblNumero = new JLabel("Número de tarjeta:");
        lblNumero.setBounds(50, 30, 120, 25);
        add(lblNumero);

        JLabel lblNumeroValor = new JLabel(tarjeta != null ? tarjeta.getNumero() : "No registrada");
        lblNumeroValor.setBounds(180, 30, 200, 25);
        add(lblNumeroValor);

        JLabel lblSaldo = new JLabel("Saldo actual:");
        lblSaldo.setBounds(50, 70, 120, 25);
        add(lblSaldo);

        JLabel lblSaldoValor = new JLabel(tarjeta != null ? String.format("$%.2f", tarjeta.getSaldo()) : "$0.00");
        lblSaldoValor.setBounds(180, 70, 200, 25);
        add(lblSaldoValor);

        JButton btnRecargar = new JButton("Recargar saldo");
        btnRecargar.setBounds(50, 110, 200, 30);
        btnRecargar.addActionListener(e -> {
            if (tarjeta == null) {
                JOptionPane.showMessageDialog(this, "No hay tarjeta registrada. Registre una primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String input = JOptionPane.showInputDialog(this, "Ingrese monto a recargar:", "Recargar Tarjeta", JOptionPane.PLAIN_MESSAGE);
            if (input != null && !input.trim().isEmpty()) {
                try {
                    double monto = Double.parseDouble(input.trim());
                    tarjeta.recargar(monto);
                    gestorDatos.guardarTarjeta(tarjeta);
                    JOptionPane.showMessageDialog(this, "Saldo recargado correctamente.");
                    lblSaldoValor.setText(String.format("$%.2f", tarjeta.getSaldo()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnRecargar);

        JButton btnRegistrar = new JButton("Registrar tarjeta");
        btnRegistrar.setBounds(50, 150, 200, 30);
        btnRegistrar.addActionListener(e -> {
            String numero = JOptionPane.showInputDialog(this, "Ingrese número de tarjeta:");
            if (numero == null || numero.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Número de tarjeta no puede estar vacío.");
                return;
            }
            String saldoStr = JOptionPane.showInputDialog(this, "Ingrese saldo inicial:");
            if (saldoStr == null || saldoStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Saldo inicial no puede estar vacío.");
                return;
            }
            try {
                double saldo = Double.parseDouble(saldoStr.trim());
                Tarjeta nuevaTarjeta = new Tarjeta(numero.trim(), saldo);
                gestorDatos.guardarTarjeta(nuevaTarjeta);
                JOptionPane.showMessageDialog(this, "Tarjeta registrada correctamente.");
                lblNumeroValor.setText(nuevaTarjeta.getNumero());
                lblSaldoValor.setText(String.format("$%.2f", nuevaTarjeta.getSaldo()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Saldo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnRegistrar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 190, 200, 30);
        btnVolver.addActionListener(e -> {
            new MenuPrincipal(menu, usuario).setVisible(true);
            dispose();
        });
        add(btnVolver);
    }
}