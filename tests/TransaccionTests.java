package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import aed.ListaEnlazada.Nodo;

public class TransaccionTests {

    @Test
    public void nuevaTransaccion() {
        Transaccion t = new Transaccion(5, 2, 3, 100);
        assertEquals(5, t.id());
        assertEquals(2, t.id_comprador());
        assertEquals(3, t.id_vendedor());
        assertEquals(100, t.monto());
        assertEquals(-1, t.obtenerIndiceHeap());
        t.indiceHeap(2);
        assertEquals(2, t.obtenerIndiceHeap());
        assertNull(t.obtenerNodoLista());
    }

    @Test
    public void modificarNodo() {
        Transaccion tx = new Transaccion(1, 0, 1, 10);
        ListaEnlazada<Transaccion> lista = new ListaEnlazada<>();
        Nodo<Transaccion> nodo = lista.agregarAtras(tx);
        tx.modificarNodoLista(nodo);
        assertSame(nodo, tx.obtenerNodoLista());
    }

    @Test
    public void compararMontosDistintos() {
        Transaccion t1 = new Transaccion(1, 0, 1, 50);
        Transaccion t2 = new Transaccion(2, 0, 1, 100);
        assertTrue(t2.compareTo(t1) > 0);
        assertTrue(t1.compareTo(t2) < 0);
    }

    @Test
    public void mismoMontoDesempatePorID() {
        Transaccion tA = new Transaccion(10, 0, 1, 100);
        Transaccion tB = new Transaccion(20, 0, 1, 100);
        assertTrue(tB.compareTo(tA) > 0);
        assertTrue(tA.compareTo(tB) < 0);
    }
}
