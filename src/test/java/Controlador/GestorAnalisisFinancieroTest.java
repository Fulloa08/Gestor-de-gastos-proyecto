// Archivo: test/Controlador/GestorAnalisisFinancieroTest.java
package Controlador;

import Modelo.Gasto;
import Modelo.Usuario;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorAnalisisFinancieroTest {
    private GestorDatos gestorDatos;
    private GestorAnalisisFinanciero gestorAnalisis;
    private final String usuarioPrueba = "testuser";
    private File gastosFile;

    @BeforeEach
    void setUp() {
        gestorDatos = new GestorDatos(new Usuario(usuarioPrueba));
        gestorAnalisis = new GestorAnalisisFinanciero(gestorDatos);
        gastosFile = new File("data/gastos_" + usuarioPrueba + ".txt");
        gastosFile.delete();
        gestorDatos = new GestorDatos(new Usuario(usuarioPrueba)); // recrear limpio
        gestorAnalisis = new GestorAnalisisFinanciero(gestorDatos);
    }

    @Test
    void testCalcularPromedioConGastos() {
        gestorDatos.guardarGasto(new Gasto("22/06/2025", "Estudios", 10000, "Libro"));
        gestorDatos.guardarGasto(new Gasto("22/06/2025", "Ocio", 20000, "Película"));
        double promedio = gestorAnalisis.calcularPromedio();
        assertEquals(15000.0, promedio);
    }

    @Test
    void testCalcularPromedioSinGastos() {
        double promedio = gestorAnalisis.calcularPromedio();
        assertEquals(0.0, promedio);
    }

    // Archivo: test/Controlador/GestorAnalisisFinancieroTest.java (extensión)
    @Test
    void testCalcularPromedioConGastoCero() {
        gestorDatos.guardarGasto(new Gasto("22/06/2025", "Varios", 0.0, "Cero"));
        double promedio = gestorAnalisis.calcularPromedio();
        assertEquals(0.0, promedio, "El promedio de un gasto de 0 debe ser 0");
    }

    @Test
    void testCalcularPromedioConGastoNegativo() {
        gestorDatos.guardarGasto(new Gasto("22/06/2025", "Error", -1000.0, "Valor negativo"));
        double promedio = gestorAnalisis.calcularPromedio();
        assertEquals(-1000.0, promedio, "El promedio de un único gasto negativo debe ser ese valor negativo");
    }

    @Test
    void testPromedioConMixtos() {
        gestorDatos.guardarGasto(new Gasto("22/06/2025", "Estudios", -2000.0, "Error"));
        gestorDatos.guardarGasto(new Gasto("22/06/2025", "Ocio", 2000.0, "Reembolso"));
        double promedio = gestorAnalisis.calcularPromedio();
        assertEquals(0.0, promedio, "Si hay un gasto de -2000 y otro de 2000, el promedio debe ser 0");
    }


    @AfterEach
    void tearDown() {
        gastosFile.delete();
    }
}
