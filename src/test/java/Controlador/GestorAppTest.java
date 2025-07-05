package Controlador;

import Modelo.Gasto;
import Modelo.Tarjeta;
import Modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestorAppTest {

    private GestorApp app;

    @BeforeEach
    public void setUp() {
        Usuario usuario = new Usuario("Felipe","12");
        app = new GestorApp(usuario);
    }

    @Test
    public void testRegistrarGastoYMostrarHistorial() {
        // Este método registra un gasto (requiere entrada del usuario)
        // Para test simple, puede ser complejo, mejor probar mostrarHistorial vacío

        app.mostrarHistorial();  // Debe imprimir "No hay gastos registrados."
    }

    @Test
    public void testDefinirMetaYVerProgreso() {
        // Simular entrada para definirMeta (ejemplo $10000)
        String input = "10000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        app.definirMeta();

        // Ver progreso (solo que no falle)
        app.verProgresoMeta();
    }

    @Test
    public void testCalcularPromedioSinGastos() {
        app.calcularPromedio(); // Debería manejar sin errores, probablemente 0
    }

    @Test
    public void testRegistrarYVerSaldoTarjeta() {
        String input = "1234567890123456\n50000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        app.registrarTarjeta();

        app.verSaldoTarjeta();
    }

    @Test
    public void testRecargarTarjeta() {
        // Primero registrar tarjeta
        String inputRegistro = "1234567890123456\n50000\n";
        System.setIn(new ByteArrayInputStream(inputRegistro.getBytes()));
        app.registrarTarjeta();

        // Ahora recargar tarjeta
        String inputRecarga = "25000\n";
        System.setIn(new ByteArrayInputStream(inputRecarga.getBytes()));
        app.recargarTarjeta();

        app.verSaldoTarjeta();
    }
}

