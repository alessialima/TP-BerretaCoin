package aed;
//en ultimo bloque tendremos la copia de la secuencia de transacciones
//del ultimo bloque agregado, asi como tambien
//la suma total de montos y cantidad de transacciones
//(excluyendo de creacion)
public class UltimoBloque  { // utl bloquye tiene lista de transacciones (tx) monto total y cantidad total 
    private  ListaEnlazada<Transaccion> listaTx;
    private int montoTotalTx;
    private int cantTotalTx;

    public UltimoBloque(ListaEnlazada<Transaccion> tx){
        this.listaTx = tx;
        this.montoTotalTx = 0;
        this.cantTotalTx = 0;
    }
    // iniciamos ultimo bloque con los montos en 0
    
    //funciones para obtener los valores de los atributos privados
    public int MontoTotalTx(){
        return montoTotalTx;
    }

    public int CantTotalTx(){
        return cantTotalTx;
    }

    public ListaEnlazada<Transaccion> obtenerListaTx() {
        return listaTx;
    }  
    // funciones publicas para poder actualizar los valores privados durante hackeo
    public void actualizarMontoTotal(int montoHackeado) {
        this.montoTotalTx -=  montoHackeado;
    }

    public void actualizarCantTotal() {
        this.cantTotalTx --;
    }

    public void agregarTransaccion(Transaccion tx) {
        ListaEnlazada.Nodo<Transaccion> nodo = listaTx.agregarAtras(tx);
        //aprovechamos que llegan en orden de id y las vamos 'encolando' en la lista enlazada 
        tx.modificarNodoLista(nodo);
        if (tx.id_comprador() != 0){
        montoTotalTx += tx.monto();
        cantTotalTx++;
        }
        // si la transaccion no es de creacion la contamos y agregamos su monto
    }       

    public Transaccion[] arrayTransacciones() {
        Transaccion[] seqTx = new Transaccion[listaTx.longitud()];
        ListaEnlazada.Iterador<Transaccion> iterador = listaTx.iterador();

        int i = 0;
        while (iterador.haySiguiente()) {
            seqTx[i++] = iterador.siguiente();
        }
        return seqTx;
        // pasamos la lista enlazada a un array, como ya estan ordenadas por id es solo ir añadiendo una por una
        //lo que tendria complejidad O(n_b)
    }

    
}

