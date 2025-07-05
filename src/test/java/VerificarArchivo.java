import Controlador.GestorUsuario;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class VerificarArchivo {

    @Test
    void testRutaUsuariosCorrecta() {
        boolean autenticado = GestorUsuario.autenticarUsuario("testuserRuta", "1234");
        File archivo = new File("data/usuarios.txt");
        assertTrue(archivo.exists(), "El archivo usuarios.txt debería existir en carpeta 'data'");
        assertTrue(autenticado, "Debería autenticarse correctamente al registrarse nuevo usuario");
    }
}
