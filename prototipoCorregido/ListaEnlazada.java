package aed;

// Usamos, como se nos fue aconsejado, la ListaEnlazada que realizamos para el taller 3
// que fue modificada para poder utilizarse en el tp

public class ListaEnlazada<T>  {
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int longitud;

    public interface Iterador<T> {
        boolean haySiguiente();
        boolean hayAnterior();
        T siguiente();
        T anterior();
    }

    public static class Nodo<T> {
        private T valor;
        private Nodo<T> last;
        private Nodo<T> next;

        public Nodo (T v){
            valor = v;
            last = null; 
            next = null;

        }

        public T getValor() {
            return valor;
        }

        public Nodo<T> getLast() {
            return last;
        }

        public Nodo<T> getNext() {
            return next;
        }
        
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }


    public Nodo<T> agregarAtras(T elem) {
        Nodo<T> nuevo = new Nodo<>(elem);
        if(longitud == 0){
            primero = nuevo;
            ultimo = nuevo;
        }
        else{
            nuevo.last = ultimo;
            ultimo.next = nuevo;
            ultimo = nuevo;
        }
        longitud ++;
        return nuevo;
    }

    public T obtener(int i) {
        Nodo<T> actual = primero;
        for (int j = 0; j < i; j++){
            actual = actual.next;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        Nodo<T> actual = primero;
        Nodo<T> anterior = null;
        for (int j = 0; j < i; j++){
            anterior = actual;
            actual = actual.next;
        }
        if (longitud == 1){ 
            primero= null;
            ultimo=null;
        }
        else if(actual == primero){ 
            primero = actual.next;
            anterior = null;
        }
        else if(actual == ultimo){ 
            ultimo = anterior;
            actual = null;
        }
        else { 
            anterior.next = actual.next;
            actual.last = anterior;
        }
        longitud --;
    }
	 
    public void eliminarNodo(Nodo<T> nodo) { 
        if (nodo.last != null) {
            nodo.last.next = nodo.next;
        }  
        else {
            primero = nodo.next;
        }
        if (nodo.next != null) {
            nodo.next.last = nodo.last;
        }
        else {
            ultimo = nodo.last;
        }
        longitud --;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo<T> actual = primero;
        for (int j = 0; j<indice; j++){
            actual = actual.next;
        }
        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        this();  
        Nodo<T> actual = lista.primero;
        while(actual != null){
            this.agregarAtras(actual.valor);
            actual = actual.next;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder lista = new StringBuilder();
        lista.append("[");
        Nodo<T> actual = primero;
        while(actual != null){
            lista.append(actual.valor.toString());
            if (actual.next != null){
                lista.append(", ");
            }
            actual = actual.next;
        }
        lista.append("]");
        return lista.toString();
    }

    private class ListaIterador implements Iterador<T> {
    	private Nodo<T> next;
        private Nodo<T> last;

        public ListaIterador(){
            next = primero;
            last = null;
        }

        public boolean haySiguiente() {
	        return next != null;
        }
        
        public boolean hayAnterior() {
	        return last != null;
        }

        public T siguiente() {
            Nodo<T> actual = next;
            last = actual;
            next = actual.next;
            return actual.valor;

        }
        

        public T anterior() {
	        Nodo<T> actual = last;
            next = actual;
            last = actual.last;
            return actual.valor;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
