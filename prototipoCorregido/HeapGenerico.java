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
            heapArray.get(i).indiceHeap(i);
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
        heapArray.get(padre).indiceHeap(padre);
        heapArray.get(hijo).indiceHeap(hijo);
    }
    // reordena el heap 'subiendo' en el array
    private void heapifyUp(int indice) {
        // pedimos en la guarda que el indice sea valido (mayor a la raiz) y que el elemento actual
        // sea mayor que su padre
        while (indice > 0 && compare(heapArray.get(indice), heapArray.get(padre(indice))) > 0) {
            int indicePadre = padre(indice);
            swap(indice, indicePadre);
            indice = indicePadre;
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

        if (!heapArray.isEmpty()) {
            heapArray.set(0, ultimo);
            ultimo.indiceHeap(0);
            heapifyDown(0);
        }
        return maximo;
    }
    // obtenemos la raiz del heap sin extraerla
    public T verMaximo() {
        return heapArray.get(0);
    }
 // no implementamos la funcion agregar elemento al heap ya que
 // en ninguna de las implementaciones del heap generico usamos esa funcion,
 // heap usuarios mantiene siempre los mismos elementos y
 // heap transacciones solo puede llegar a necesitar extraer raiz
}
