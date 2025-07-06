package GUI;

import Controlador.GestorDatos;
import Modelo.Gasto;
import Modelo.Usuario;
import Vista.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuGestorGastos extends MenuPrincipal {
    private final GestorDatos gestorDatos;
    private final Usuario usuario;

    public MenuGestorGastos(Menu menu, Usuario usuario) {
        super(menu,usuario);
        this.usuario = usuario;
        this.gestorDatos = new GestorDatos(usuario);

        setTitle("Gestor de Gastos");
        getContentPane().removeAll();
        repaint();
        inicializarComponentes();
    }


    @Override
    protected void inicializarComponentes() {
        setLayout(null);

        JButton registrarGastoBtn = new JButton("Registrar Gasto");
        registrarGastoBtn.setBounds(100, 50, 200, 30);
        registrarGastoBtn.addActionListener(e -> mostrarFormularioGasto());
        add(registrarGastoBtn);

        JButton verHistorialBtn = new JButton("Ver Historial");
        verHistorialBtn.setBounds(100, 90, 200, 30);
        verHistorialBtn.addActionListener(e -> mostrarHistorial());
        add(verHistorialBtn);

        JButton volver = new JButton("Volver");
        volver.setBounds(100, 130, 200, 30);
        volver.addActionListener(e -> {
            new MenuPrincipal(menu,usuario).setVisible(true);
            dispose();
        });
        add(volver);
    }

    private void mostrarFormularioGasto() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
        String[] categorias = {"Estudios", "Alimentación", "Transporte", "Ocio", "Varios"};

        JComboBox<String> categoriaBox = new JComboBox<>(categorias);
        JTextField montoField = new JTextField();
        JTextField detalleField = new JTextField();

        panel.add(new JLabel("Categoría:"));
        panel.add(categoriaBox);
        panel.add(new JLabel("Monto:"));
        panel.add(montoField);
        panel.add(new JLabel("Detalle:"));
        panel.add(detalleField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Registrar Gasto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String categoria = (String) categoriaBox.getSelectedItem();
                double monto = Double.parseDouble(montoField.getText());
                String detalle = detalleField.getText();

                Gasto gasto = new Gasto(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        categoria, monto, detalle);

                gestorDatos.guardarGasto(gasto);
                JOptionPane.showMessageDialog(null, "Gasto registrado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: El monto debe ser numérico.");
            }
        }
    }

    private void mostrarHistorial() {
        List<Gasto> historial = gestorDatos.obtenerHistorial();
        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay gastos registrados.");
            return;
        }

        JTextArea area = new JTextArea(15, 40);
        area.setEditable(false);
        for (Gasto g : historial) {
            area.append(g.getFecha() + " | " + g.getCategoria() + " | $" + g.getMonto() + " | " + g.getDetalle() + "\n");
        }

        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scroll, "Historial de Gastos", JOptionPane.INFORMATION_MESSAGE);
    }
}
