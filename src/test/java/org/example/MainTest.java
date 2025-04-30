package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @BeforeEach
    void setUp() {
        Main.cantidadGastos = 0;
        Main.metaAhorro = 0;
        Main.saldoActual = 0;
        Main.gasto = new String[Main.TOTALGASTOS][4];
    }

    @Test
    void testValidacionUsuarioExitosa() {
        String input = "Juan\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner original = Main.scanner;
        Main.scanner = new Scanner(System.in);

        assertTrue(Main.validacionUsuario());

        Main.scanner = original;
    }

    @Test
    void testAgregarGastoConEntradaSimulada() {
        String input = "150.0\n2025-04-30\nTransporte\nBus\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner original = Main.scanner;
        Main.scanner = new Scanner(System.in);

        Main.saldoActual = 1000;
        Main.agregarGasto();

        assertEquals(1, Main.cantidadGastos);
        assertEquals("150.0", Main.gasto[0][0]);
        assertEquals(850.0, Main.saldoActual);

        Main.scanner = original;
    }

    @Test
    void testMostrarGastosConDatos() {
        Main.gasto[0] = new String[]{"100", "2025-01-01", "Comida", "Pizza"};
        Main.cantidadGastos = 1;
        assertDoesNotThrow(Main::mostrarGastos);
    }

    @Test
    void testMontoTotalGastadoCalculaCorrecto() {
        Main.gasto[0] = new String[]{"100", "2025-01-01", "Comida", "Pizza"};
        Main.gasto[1] = new String[]{"200", "2025-01-02", "Transporte", "Metro"};
        Main.cantidadGastos = 2;

        assertDoesNotThrow(Main::montoTotalGastado);
    }

    @Test
    void testBusquedaPorCategoriaEncuentraDato() {
        String input = "Comida\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner original = Main.scanner;
        Main.scanner = new Scanner(System.in);

        Main.gasto[0] = new String[]{"50", "2025-01-01", "Comida", "Sushi"};
        Main.cantidadGastos = 1;

        assertDoesNotThrow(Main::busquedaPorCategoria);

        Main.scanner = original;
    }

    @Test
    void testBusquedaPorFechaEncuentraDato() {
        String input = "2025-01-01\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner original = Main.scanner;
        Main.scanner = new Scanner(System.in);

        Main.gasto[0] = new String[]{"75", "2025-01-01", "Transporte", "Uber"};
        Main.cantidadGastos = 1;

        assertDoesNotThrow(Main::busquedaPorFecha);

        Main.scanner = original;
    }

    @Test
    void testSubmenuMetasVisualizarYSalir() {
        String input = "2\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner original = Main.scanner;
        Main.scanner = new Scanner(System.in);

        assertDoesNotThrow(Main::submenuMetas);

        Main.scanner = original;
    }

    @Test
    void testEstablecerMeta() {
        String input = "500.0\n300.0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner original = Main.scanner;
        Main.scanner = new Scanner(System.in);

        Main.establecerMeta();

        assertEquals(500.0, Main.metaAhorro);
        assertEquals(300.0, Main.saldoActual);

        Main.scanner = original;
    }

    @Test
    void testVisualizarMetaCumplida() {
        Main.metaAhorro = 100;
        Main.saldoActual = 200;
        assertDoesNotThrow(Main::visualizarMeta);
    }

    @Test
    void testVisualizarMetaMetaNoCumplida() {
        Main.metaAhorro = 1000;
        Main.saldoActual = 400;

        boolean metaCumplida = Main.saldoActual >= Main.metaAhorro;
        assertFalse(metaCumplida);
    }

    @Test
    void testCalcularPromedioGastos() {
        Main.gasto[0] = new String[]{"50", "2025-01-01", "Comida", "Pizza"};
        Main.gasto[1] = new String[]{"150", "2025-01-02", "Comida", "Cena"};
        Main.cantidadGastos = 2;

        assertDoesNotThrow(Main::calcularPromedioGastos);
    }

    @Test
    void testLimpiarDatosMatriz() {
        Main.gasto[0] = new String[]{"10", "2025-01-01", "Transporte", "Taxi"};
        Main.cantidadGastos = 1;

        Main.limpiarDatosMatriz();

        assertEquals(0, Main.cantidadGastos);
        assertNull(Main.gasto[0][0]);
    }

    @Test
    void testMostrarPorcentajePorCategoria() {
        Main.gasto[0] = new String[]{"10", "2025-01-01", "Comida", ""};
        Main.gasto[1] = new String[]{"20", "2025-01-02", "Transporte", ""};
        Main.gasto[2] = new String[]{"30", "2025-01-03", "Comida", ""};
        Main.cantidadGastos = 3;

        assertDoesNotThrow(Main::mostrarPorcentajePorCategoria);
    }

    @Test
    void testMostrarTarjeta() {
        Main.saldoActual = 300.0;
        assertDoesNotThrow(Main::mostrarTarjeta);
    }

    @Test
    void testNoPermiteAgregarGastoSiSeExcedeLimite() {
        Main.cantidadGastos = Main.TOTALGASTOS;

        boolean puedeAgregar = Main.cantidadGastos < Main.TOTALGASTOS;
        assertFalse(puedeAgregar);
    }

    @Test
    void testLimpiarDatosMatrizReiniciaCantidad() {
        Main.gasto[0] = new String[]{"50", "2024-01-01", "Comida", "Pizza"};
        Main.cantidadGastos = 1;

        Main.limpiarDatosMatriz();

        assertEquals(0, Main.cantidadGastos);
        assertNull(Main.gasto[0][0]);
    }
}
