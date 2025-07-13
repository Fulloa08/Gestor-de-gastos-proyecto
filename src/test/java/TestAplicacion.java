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

    // 1. Validaciones de parseDouble: aseguramos que si el usuario ingresa un valor no numérico,
    // el sistema lo detecte.
    @Test
    void testParseoInvalidoEnMeta() {
        String entrada = "abc"; // Caso de texto en lugar de número
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(entrada); // Simula el ingreso de meta con valor no numérico
        });
    }

    @Test
    void testParseoInvalidoEnGasto() {
        String entrada = "X.99"; // Caso con formato incorrecto
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(entrada); // Simula el ingreso de gasto con valor no válido
        });
    }

    // 2. División por cero en análisis financiero: aseguramos que si no hay datos, el sistema no se caiga.
    @Test
    void testPromedioConListaVacia() {
        AnalizadorFinanciero analizador = new AnalizadorFinanciero();
        List<Gasto> lista = new ArrayList<>(); // Lista vacía de gastos
        double promedio = analizador.calcularPromedio(lista);
        assertEquals(0.0, promedio, "El promedio de una lista vacía debe ser 0.0");
    }

    @Test
    void testPorcentajeConGastosVacios() {
        AnalizadorFinanciero analizador = new AnalizadorFinanciero();
        List<Gasto> lista = new ArrayList<>(); // Lista vacía de gastos
        Map<String, Double> porcentajes = analizador.calcularPorcentajePorTipo(lista);
        assertTrue(porcentajes.isEmpty(), "La lista de porcentajes debe estar vacía si no hay gastos");
    }

    // 3. Verificación de inicialización correcta de tarjeta predefinida
    // Simula la creación de un nuevo usuario y asegura que siempre
    // haya una tarjeta inicializada para evitar errores null.
    @Test
    void testTarjetaSeInicializaCorrectamente() {
        Usuario usuario = new Usuario("test_tarjeta", "clave");
        GestorDatos gestor = new GestorDatos(usuario);

        assertNotNull(gestor.getTarjeta(), "La tarjeta debería estar inicializada automáticamente al crear usuario");

        String numero = gestor.getTarjeta().getNumero();
        assertNotNull(numero, "El número de tarjeta no debe ser null");
        assertFalse(numero.isEmpty(), "El número de tarjeta no debe estar vacío");

        assertEquals(100000, gestor.getTarjeta().getSaldo(), "El saldo inicial debe ser 100000");
    }

    // 4. Validación de formato de fecha: asegura que el sistema solo acepte fechas con formato dd/MM/yyyy.
    @Test
    void testFechaConFormatoErroneo() {
        String fecha = "2024-12-01"; // Formato incorrecto para el sistema
        assertThrows(DateTimeParseException.class, () -> {
            LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        });
    }

    // 5. Creación y verificación de existencia del archivo de usuarios:
    // Asegura que el archivo usuarios.txt se cree correctamente al registrar un usuario.
    @Test
    void testArchivoUsuariosRutaCorrecta() {
        String nombre = "testusuario";
        String clave = "1234";
        GestorUsuario.registrarUsuario(nombre, clave);
        boolean resultado = GestorUsuario.autenticarUsuario(nombre, clave);
        File archivo = new File("data/usuarios.txt");
        assertTrue(archivo.exists(), "El archivo usuarios.txt debe existir en la carpeta 'data'");
        assertTrue(resultado, "El usuario debería autenticarse o registrarse correctamente");
    }
}
