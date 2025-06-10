package aed;
 //un usuario tendra id, saldo y ademas un handle para busqueda en O(1)
public class Usuario implements Comparable<Usuario>, HeapHandle { 
    private int id;
    private int saldo;
    private int indiceHeap; 

    // primero inicializamos usuario con su id, sueldo y pos en el heap
    public Usuario(int id, int saldo) { 
        this.id = id;
        this.saldo = saldo;
        this.indiceHeap = -1; 
        // cuando se inserte al usuario dentro del heap,
        //el handle sera modificado por su correspondiente valor
        // mas que nada para evitar usos de null
    }

    // necesitamos poder comparar usuarios según su id (descendente) y saldo (ascendente)
    @Override
    public int compareTo(Usuario otro) {
        if (this.saldo != otro.saldo) {
            return this.saldo - otro.saldo; // mayor saldo primero
        }
        return otro.id - this.id; // menor id en empate
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { // si son iguales en misma pos de memoria y todo son lo mismo asi que true
            return true;
        } 
        else {
            if (o == null || getClass() != o.getClass()) { // si es null o si son de clases ditintas es false
                return false;
            }
            else { // else: es un usuario pero quiero ver si son el mismo usuario(si tienen mismo id)
                 Usuario usuario = (Usuario) o; 
                 return id == usuario.id;
            }
        }
    }

    @Override
    public void indiceHeap(int indice) { // seteamos el indice 
        indiceHeap = indice;
    }

    @Override
    public int obtenerIndiceHeap() { // quiero ver el indice
        return indiceHeap;
    }
    // funciones para obtener los valores de los atributos privados
    public int verId() { 
        return id; 
    }
    public int verSaldo() { 
        return saldo; 
    }
    public void modificarSaldo(int saldo) { 
        this.saldo = saldo; 
    }
}
