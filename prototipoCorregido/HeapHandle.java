package aed;
// interfaz que nos permitira acceso en O(1) a elementos de un heap
public interface HeapHandle {
    void indiceHeap(int indice);
    //establecemos la posicion actual del elemento dentro del heap
    int obtenerIndiceHeap();
    // devolvemos la posicion del elemento dentro del heap
}
 // elegimos que '-1' simbolice que no esta en el heap