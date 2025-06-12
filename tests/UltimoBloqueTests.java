package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UltimoBloqueTests {

    private UltimoBloque bloque;
    private ListaEnlazada<Transaccion> listaVacia;

    @BeforeEach
    public void setUp() {
        listaVacia = new ListaEnlazada<>();
        bloque = new UltimoBloque(listaVacia);
    }

    @Test
    public void inicializacion() {
        assertEquals(0, bloque.MontoTotalTx());
        assertEquals(0, bloque.CantTotalTx());
        Transaccion[] transacciones = bloque.arrayTransacciones();
        assertNotNull(transacciones);
        assertEquals(0, transacciones.length);
    }

    @Test
    public void insertarCreacion() {
        Transaccion creacion = new Transaccion(0, 0, 5, 50);
        bloque.agregarTransaccion(creacion);
        Transaccion[] transacciones = bloque.arrayTransacciones();
        assertEquals(1, transacciones.length);
        assertEquals(creacion, transacciones[0]);
        assertEquals(0, bloque.MontoTotalTx());
        assertEquals(0, bloque.CantTotalTx());
    }

    @Test
    public void insertarTransaccion() {
        Transaccion tx1 = new Transaccion(1, 2, 3, 20); 
        bloque.agregarTransaccion(tx1);
        Transaccion[] transacciones = bloque.arrayTransacciones();
        assertEquals(1, transacciones.length);
        assertEquals(tx1, transacciones[0]);
        assertEquals(20, bloque.MontoTotalTx());
        assertEquals(1, bloque.CantTotalTx());
    }

    @Test
    public void insertarVariasTransacciones() {
        Transaccion creacion = new Transaccion(0, 0, 1, 5);
        Transaccion tx1 = new Transaccion(1, 1, 2, 10);
        Transaccion tx2 = new Transaccion(2, 2, 3, 15);
        bloque.agregarTransaccion(creacion);
        bloque.agregarTransaccion(tx1);
        bloque.agregarTransaccion(tx2);
        Transaccion[] transacciones = bloque.arrayTransacciones();
        assertEquals(3, transacciones.length);
        assertArrayEquals(new Transaccion[]{creacion, tx1, tx2}, transacciones);
        assertEquals(25, bloque.MontoTotalTx());
        assertEquals(2, bloque.CantTotalTx());
    }
}
