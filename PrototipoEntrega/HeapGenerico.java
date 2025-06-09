package aed;

import java.util.ArrayList;
// implementacion de un heap sobre un arrayList generico, el mismo sera utilizado
// para los usuarios y para las transacciones
// implementa comparable y heapHandle para rapida ubicacion de elementos
public class HeapGenerico<T extends Comparable<T> & HeapHandle> {
    private ArrayList<T> heapArray; 
    //sera luego sobreescrito por las clases Usuario y Transaccion
    private int compare(T a, T b) {
        return a.compareTo(b);
    }

    public HeapGenerico() { 
        this.heapArray = new ArrayList<>();
        //iniciamos el heap como un arreglo
    }
    // constructor del heap, setea los valores de los handles
    public HeapGenerico(ArrayList<T> array){
        heapArray = new ArrayList<>(array);
        for (int i = 0; i < heapArray.size(); i++){
            heapArray.get(i).setHeapIndex(i);
        }
        buildHeap();    
    }
    // Aplicamos el algoritmo de floyd para crear el heap, reduciendo la complejidad
    private void buildHeap() {
        int n = heapArray.size();
        for (int i = padre(n-1); i >= 0; i--) {
            heapifyDown(i);
        } //comienza desde el ultimo padre y termina en la raiz
    }
    //aprovechamos todo el arreglo, para evitar dejar la posicion 0 vacia
    private int padre(int indice){
        return (indice -1)/2;
    }
    private int hijoIzq(int indice){
        return 2*indice +1;
    }
    private int hijoDer(int indice){
        return 2*indice +2;
    }
   // intercambia dos elementos en el heap y actualiza sus handles
    private void swap(int padre, int hijo) {
        T temp = heapArray.get(padre);
        heapArray.set(padre, heapArray.get(hijo));
        heapArray.set(hijo, temp);
        heapArray.get(padre).setHeapIndex(padre);
        heapArray.get(hijo).setHeapIndex(hijo);
    }
    // reordena el heap 'subiendo' en el array
    private void heapifyUp(int indice) {
        while (indice > 0) {
            int indicePadre = padre(indice);
            if(compare(heapArray.get(indice), heapArray.get(indicePadre)) >0) {
                swap(indice, indicePadre);
                indice = indicePadre;
            }
            else {
                break; // si es mayor que su padre sube, en caso contrario
                       // usamos break para finalizar el heapify
            }
        }
    }
    // reordena el heap 'bajando' en el array
    private void heapifyDown(int indice) {
        int actual = indice;
        int largo = heapArray.size();
        boolean necesitaCambio = true;
        // usamos un booleano para cortar el ciclo

        while(necesitaCambio) {
            int izq = hijoIzq(actual);
            int der = hijoDer(actual);
            int mayor = actual;
            // comparamos con el hijo izquierdo y luego con el derecho
            if (izq < largo && compare(heapArray.get(izq), heapArray.get(mayor)) >0) {
                mayor = izq;
            }
            if (der < largo && compare(heapArray.get(der), heapArray.get(mayor)) >0) {
                mayor = der;
            }      
            if (mayor != actual) {
                swap(actual, mayor);
                actual = mayor;
            }  // en caso de que alguno de los hijos sea mayor a su padre realizamos
               // el swap, caso contrario usamos break para finalizar el heapify
            else {
                necesitaCambio = false;
            }
        }
    }
    // funcion publica para actualizar la posicion de un elemento
    //dentro del heap
    public void actualizarElemento(int indice) {
        if (indice >= 0 && indice < heapArray.size()) {
            heapifyUp(indice);
            heapifyDown(indice);
        }
    }
    //extraemos la raiz del heap
    //esta es reemplazada por el ultimo elemento del array y luego realizamos heapify
    //para mantener el invariante
    public T extraerMaximo() {
        T maximo = heapArray.get(0);
        T ultimo = heapArray.remove(heapArray.size() -1);
        maximo.setHeapIndex(-1);

        if (!heapArray.isEmpty()) {
            heapArray.set(0, ultimo);
            ultimo.setHeapIndex(0);
            heapifyDown(0);
        }
        return maximo;
    }
    // obtenemos la raiz del heap sin extraerla
    public T verMaximo() {
        return heapArray.get(0);
    }

}
