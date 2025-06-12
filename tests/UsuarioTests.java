package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UsuarioTests {

    @Test
    public void nuevoUsuario() {
        Usuario u = new Usuario(42, 100);
        assertEquals(42, u.verId());
        assertEquals(100, u.verSaldo());
        assertEquals(-1, u.obtenerIndiceHeap());
        u.indiceHeap(3);
        assertEquals(3, u.obtenerIndiceHeap());
        u.modificarSaldo(250);
        assertEquals(250, u.verSaldo());
    }

    @Test
    public void compararSaldosDistintos() {
        Usuario u1 = new Usuario(1, 50);
        Usuario u2 = new Usuario(2, 100);
        assertTrue(u2.compareTo(u1) > 0);
        assertTrue(u1.compareTo(u2) < 0);
    }

    @Test
    public void mismoSaldoDesempatePorID() {
        Usuario uA = new Usuario(5, 100);
        Usuario uB = new Usuario(3, 100);
        assertTrue(uB.compareTo(uA) > 0);
        assertTrue(uA.compareTo(uB) < 0);
    }
    
}