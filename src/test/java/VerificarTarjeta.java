import Controlador.GestorApp;
import Modelo.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorAppTest {

    @Test
    void testTarjetaNula() {
        Usuario usuario = new Usuario("testuser", "clave123");
        GestorApp app = new GestorApp(usuario);
        var tarjeta = app.gestorDatos.getTarjeta();
        assertNull(tarjeta, "No debería haber tarjeta registrada inicialmente");
    }
}
