package GUI;

import Controlador.GestorDatos;
import Modelo.Tarjeta;
import Modelo.Usuario;

import javax.swing.*;

public class MenuMostrarTarjeta extends VentanaMenu {
    private final Usuario usuario;
    private final GestorDatos gestorDatos;

    public MenuMostrarTarjeta(Usuario usuario) {
        super(usuario);
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

        JLabel lblNumeroValor = new JLabel();
        lblNumeroValor.setBounds(180, 30, 200, 25);
        add(lblNumeroValor);

        JLabel lblSaldo = new JLabel("Saldo actual:");
        lblSaldo.setBounds(50, 70, 120, 25);
        add(lblSaldo);

        JLabel lblSaldoValor = new JLabel();
        lblSaldoValor.setBounds(180, 70, 200, 25);
        add(lblSaldoValor);

        if (tarjeta != null) {
            lblNumeroValor.setText(tarjeta.getNumero());
            lblSaldoValor.setText(String.format("$%.2f", tarjeta.getSaldo()));
        } else {
            lblNumeroValor.setText("No registrada");
            lblSaldoValor.setText("$0.00");
            JOptionPane.showMessageDialog(this,
                    "No hay tarjeta registrada. Por favor registre una.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 150, 200, 30);
        btnVolver.addActionListener(e -> {
            new MenuPrincipal(usuario).setVisible(true);
            dispose();
        });
        add(btnVolver);
    }
}
