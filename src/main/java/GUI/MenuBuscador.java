package GUI;

import Controlador.GestorBuscador;
import Controlador.GestorDatos;
import Modelo.Gasto;
import Modelo.Usuario;
import Vista.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuBuscador extends VentanaMenu {
    private final GestorBuscador gestorBuscador;
    private final GestorDatos gestorDatos;
    private final Usuario usuario;

    public MenuBuscador(Menu menu, Usuario usuario) {
        super(menu, usuario);
        this.usuario = usuario;
        this.gestorDatos = new GestorDatos(usuario);
        this.gestorBuscador = new GestorBuscador(gestorDatos);

        setTitle("Buscador de Gastos");
        getContentPane().removeAll();
        repaint();
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(null);

        JButton buscarPorCategoriaBtn = new JButton("Buscar por Categoría");
        buscarPorCategoriaBtn.setBounds(100, 50, 200, 30);
        buscarPorCategoriaBtn.addActionListener(e -> buscarPorCategoria());
        add(buscarPorCategoriaBtn);

        JButton buscarPorFechaBtn = new JButton("Buscar por Fecha");
        buscarPorFechaBtn.setBounds(100, 90, 200, 30);
        buscarPorFechaBtn.addActionListener(e -> buscarPorFecha());
        add(buscarPorFechaBtn);

        JButton volver = new JButton("Volver");
        volver.setBounds(100, 130, 200, 30);
        volver.addActionListener(e -> {
            new MenuPrincipal(menu, usuario).setVisible(true);
            dispose();
        });
        add(volver);
    }

    private void buscarPorCategoria() {
        String categoria = JOptionPane.showInputDialog(this, "Ingrese la categoría para buscar:");

        if (categoria == null || categoria.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Categoría no puede estar vacía.");
            return;
        }

        List<Gasto> resultados = gestorBuscador.buscarPorCategoria(categoria.trim());
        mostrarResultados(resultados, "Gastos en categoría: " + categoria);
    }

    private void buscarPorFecha() {
        String fecha = JOptionPane.showInputDialog(this, "Ingrese la fecha (dd/MM/yyyy) para buscar:");

        if (fecha == null || fecha.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fecha no puede estar vacía.");
            return;
        }

        List<Gasto> resultados = gestorBuscador.buscarPorFecha(fecha.trim());
        mostrarResultados(resultados, "Gastos en fecha: " + fecha);
    }

    private void mostrarResultados(List<Gasto> gastos, String titulo) {
        if (gastos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron gastos para la búsqueda indicada.");
            return;
        }

        JTextArea area = new JTextArea(15, 40);
        area.setEditable(false);
        for (Gasto g : gastos) {
            area.append(g.getFecha() + " | " + g.getCategoria() + " | $" + g.getMonto() + " | " + g.getDetalle() + "\n");
        }

        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(this, scroll, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
