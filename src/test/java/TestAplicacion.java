import Modelo.*;
import Controlador.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestAplicacion {

    // 1. Validaciones de parseDouble
    @Test
    void testParseoInvalidoEnMeta() {
        String entrada = "abc";
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(entrada);
        });
    }

    @Test
    void testParseoInvalidoEnRecarga() {
        String entrada = "mil";
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(entrada);
        });
    }

    @Test
    void testParseoInvalidoEnGasto() {
        String entrada = "X.99";
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(entrada);
        });
    }

    // 2. División por cero en análisis financiero
    @Test
    void testPromedioConListaVacia() {
        AnalizadorFinanciero analizador = new AnalizadorFinanciero();
        List<Gasto> lista = new ArrayList<>();
        double promedio = analizador.calcularPromedio(lista);
        assertEquals(0.0, promedio, "El promedio de una lista vacía debe ser 0.0");
    }

    @Test
    void testPorcentajeConGastosVacios() {
        AnalizadorFinanciero analizador = new AnalizadorFinanciero();
        List<Gasto> lista = new ArrayList<>();
        Map<String, Double> porcentajes = analizador.calcularPorcentajePorTipo(lista);
        assertTrue(porcentajes.isEmpty(), "La lista de porcentajes debe estar vacía si no hay gastos");
    }

    // 3. Verificación de comportamiento si la tarjeta es null
    @Test
    void testTarjetaInicialEsNull() {
        Usuario usuario = new Usuario("test_tarjeta", "clave");
        GestorDatos gestor = new GestorDatos(usuario);
        assertNull(gestor.getTarjeta(), "No debería haber tarjeta registrada inicialmente");
    }

    // 4. Formato de fecha inválido
    @Test
    void testFechaConFormatoErroneo() {
        String fecha = "2024-12-01"; // formato incorrecto
        assertThrows(DateTimeParseException.class, () -> {
            LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        });
    }

    // 5. Creación y ruta correcta de usuarios.txt
    @Test
    void testArchivoUsuariosRutaCorrecta() {
        String nombre = "testusuario";
        String clave = "1234";
        boolean resultado = GestorUsuario.autenticarUsuario(nombre, clave);
        File archivo = new File("data/usuarios.txt");
        assertTrue(archivo.exists(), "El archivo usuarios.txt debe existir en la carpeta 'data'");
        assertTrue(resultado, "El usuario debería autenticarse o registrarse correctamente");
    }
}
