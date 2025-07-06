package GUI;

import Controlador.GestorAnalisisFinanciero;
import Controlador.GestorDatos;
import Modelo.Gasto;
import Modelo.Usuario;
import Vista.Menu;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuAnalisisFinanciero extends MenuPrincipal {
    private final GestorDatos gestorDatos;
    private final GestorAnalisisFinanciero analisis;
    private final Usuario usuario;

    public MenuAnalisisFinanciero(Menu menu, Usuario usuario) {
        super(menu, usuario);
        this.usuario = usuario;
        this.gestorDatos = new GestorDatos(usuario);
        this.analisis = new GestorAnalisisFinanciero(gestorDatos);

        setTitle("Análisis Financiero");
        getContentPane().removeAll();
        repaint();
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(null);

        JButton promedioBtn = new JButton("Ver Promedio de Gastos");
        promedioBtn.setBounds(80, 50, 240, 30);
        promedioBtn.addActionListener(e -> mostrarPromedio());
        add(promedioBtn);

        JButton porcentajeBtn = new JButton("Ver Porcentaje por Categoría");
        porcentajeBtn.setBounds(80, 90, 240, 30);
        porcentajeBtn.addActionListener(e -> mostrarPorcentajes());
        add(porcentajeBtn);

        JButton volver = new JButton("Volver");
        volver.setBounds(80, 130, 240, 30);
        volver.addActionListener(e -> {
            new MenuPrincipal(menu, usuario).setVisible(true);
            dispose();
        });
        add(volver);
    }

    private void mostrarPromedio() {
        double promedio = analisis.calcularPromedio();
        JOptionPane.showMessageDialog(this,
                String.format("El promedio de gastos es: $%.2f", promedio),
                "Promedio de Gastos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarPorcentajes() {
        List<Gasto> gastos = gestorDatos.obtenerHistorial();
        double total = gastos.stream().mapToDouble(Gasto::getMonto).sum();

        if (total == 0) {
            JOptionPane.showMessageDialog(this, "No hay gastos registrados para analizar.");
            return;
        }

        Map<String, Double> porCategoria = new HashMap<>();
        for (Gasto g : gastos) {
            porCategoria.put(
                    g.getCategoria(),
                    porCategoria.getOrDefault(g.getCategoria(), 0.0) + g.getMonto()
            );
        }

        StringBuilder resultado = new StringBuilder();
        resultado.append("--- Porcentaje por Categoría ---\n");

        for (Map.Entry<String, Double> entry : porCategoria.entrySet()) {
            double porcentaje = (entry.getValue() / total) * 100;
            resultado.append(String.format("%s: %.2f%%\n", entry.getKey(), porcentaje));
        }

        JTextArea area = new JTextArea(resultado.toString(), 10, 30);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(this, scroll, "Porcentaje por Categoría", JOptionPane.INFORMATION_MESSAGE);
    }
}
