package aed;

public class Transaccion implements Comparable<Transaccion> , HeapHandle { //agregamos handle para poder encontrar las transacciones en O(1)
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;
    private int indiceHeap; 
    private ListaEnlazada.Nodo<Transaccion> nodoLista; //referencio al nodo 

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
        this.indiceHeap = -1;
        //cuando se inserte la transaccion dentro del heap, el handle sera modificado por su correspondiente valor
    }

    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto != otro.monto) {
            return this.monto - otro.monto; //devuelve la transaccion de mayor monto
        }
        return this.id - otro.id; //desempata con mayor id
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }    //si ambas transacciones apuntan al mismo lugar, en terminos de memoria seran iguales
        else {
            if (o == null || getClass() != o.getClass()) {
             return false;
            } //si es nula o son de distinta clase, seran distintas
            else {
            Transaccion that = (Transaccion) o;
            return id == that.id;
            //dos transacciones seran iguales si poseen el mismo id
            //asumimos que las transacciones tienen id unico dentro de toda la blockchain,
            //no solo dentro del bloque
            //de todas maneras es indiferente debido a que solo trabajaremos con el ultimo
            //bloque de la blockchain
        }
        }
    }

    @Override
    public void indiceHeap(int indice) { //seteamos el indice
        indiceHeap = indice;
    }

    @Override
    public int obtenerIndiceHeap() { // vemos el indice
        return indiceHeap;
    }

    // funciones para obtener los atributos privados de transaccion
    public int monto() { 
        return monto; 
    }
    public int id_comprador() { 
        return id_comprador; 
    }

    public int id_vendedor() { 
        return id_vendedor; 
    }

    public int id() { 
        return id; 
    }
    public void modificarNodoLista(ListaEnlazada.Nodo<Transaccion> nodo) { 
        this.nodoLista = nodo; 
    }
    
    public ListaEnlazada.Nodo<Transaccion> obtenerNodoLista() { 
        return nodoLista; 
    }
}
