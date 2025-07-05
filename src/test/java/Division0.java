import Controlador.GestorAnalisisFinanciero;
import Modelo.Gasto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Division0 {

    @Test
    void testPromedioConListaVacia() {
        GestorAnalisisFinanciero gestor = new GestorAnalisisFinanciero(null);
        List<Gasto> gastos = new ArrayList<>();
        double promedio = new Controlador.AnalizadorFinanciero().calcularPromedio(gastos);
        assertEquals(0, promedio);
    }

    @Test
    void testPorcentajeConGastosVacios() {
        List<Gasto> gastos = new ArrayList<>();
        var porcentajes = new Controlador.AnalizadorFinanciero().calcularPorcentajePorTipo(gastos);
        assertTrue(porcentajes.isEmpty());
    }
}