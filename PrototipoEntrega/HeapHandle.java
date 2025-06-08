package aed;
// interfaz que nos permitira acceso en O(1) a elementos de un heap
public interface HeapHandle {
    void setHeapIndex(int index);
    //establecemos la posicion actual del elemento dentro del heap,
    //se modifica durante heapify
    int getHeapIndex();
    // devolvemos la posicion del elemento dentro del heap
}
