import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidacionesTest {

    @Test
    void testParseoInvalidoEnMeta() {
        String entrada = "abc";
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(entrada);
        });
    }

    @Test
    void testParseoInvalidoEnTarjeta() {
        String entrada = "cien";
        assertThrows(NumberFormatException.class, () -> {
            Double.parseDouble(entrada);
        });
    }

    @Test
    void testFechaInvalidaFormato() {
        String fecha = "2024-10-12"; // formato inválido
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            java.time.LocalDate.parse(fecha, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        });
    }
}
