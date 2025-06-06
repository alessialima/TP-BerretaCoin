package aed;

public class HeapTransacciones {
// comparamos primero el monto y luego
// desempatamos por mayor id
    private int compararTransacciones(Transaccion tx1, Transaccion tx2) {
    if (tx1.monto() != tx2.monto()) {
        return tx2.monto() - tx1.monto();
    }
    return tx2.id() - tx1.id(); 
}
    
}
