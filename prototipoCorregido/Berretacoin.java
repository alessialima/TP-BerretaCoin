package aed;
import java.util.ArrayList;

public class Berretacoin {
    private Usuario[] usuarios;
     //lista fija donde tendremos handles que apunten a la ubicación del usuario dentro de heapUsuarios
    private HeapGenerico<Usuario> heapUsuarios;
    //array heap donde mantemos la informacion de los usuarios(ordenados segun mayor saldo) mas un handle para mantener actualizada su posicion
    //dentro del heap y asi encontrarlos en O(1)
    private UltimoBloque ultimoBloque;
    // clase que contiene una lista enlazada donde cada nodo es una transaccion ordenada por id tal cual es recibida en
    //agregarBloque, en nuestra implementacion solo nos interesa mantener la informacion del ultimo bloque de la blockchain
    //(salvo modificaciones del saldo usuarios realizadas por anteriores bloques), agregar un nuevo bloque elimina la LE anterior
    //y crea una nueva con las nuevas transacciones, ademas mantenemos dos variables int, una que sea total transacciones y otra
    // monto total de creaciones (excluyendo posible transaccion de creacion)
    private HeapGenerico<Transaccion> heapTransacciones;
    // array heap(ordenados segun mayor monto transaccion) donde mantenemos handles que apuntan a los nodos de la lista enlazada 
    
    public Berretacoin(int n_usuarios){  
        usuarios = new Usuario[n_usuarios];
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    
        for (int i = 0; i < n_usuarios; i++){
            usuarios[i] = new Usuario(i+1,0);
            listaUsuarios.add(usuarios[i]);
        }
        heapUsuarios = new HeapGenerico<>(listaUsuarios);
        ultimoBloque = null;
        heapTransacciones = null;
    }
    //iniciamos BerretaCoin con la creacion de los usuarios, como el parametro es un entero que define solo la cantidad de usuarios
    // asumimos que sus ids van de 1 en 1 comenzando por el id 1 (id 0 reservado para creacion) e iniciamos a todos con saldo 0
    //cada usuario estara en el indice (suID -1) para que acceder a su informacion sea O(1), tambien iniciamos el heap de usuarios
    // que al tener todos usuarios con monto 0 desempatara mediante menor id
    //luego, agregar n_usuarios tiene costo O(n_usuarios)
    // iniciamos ultimoBloque y heapTransacciones vacio

    public void agregarBloque(Transaccion[] transacciones){
        UltimoBloque nuevoBloque = new UltimoBloque(new ListaEnlazada<>());
        ArrayList<Transaccion> transaccionesLista = new ArrayList<>();

        for (Transaccion tx: transacciones) {
            if (tx.id_comprador() != 0) {
                Usuario comprador = usuarios[tx.id_comprador() - 1]; 
                comprador.modificarSaldo(comprador.verSaldo() - tx.monto());
                actualizarHeap(comprador);
            }
            //evitamos que intente acceder al usuario 0 dentro del heap para compradores
            Usuario vendedor = usuarios[tx.id_vendedor() - 1];
            vendedor.modificarSaldo(vendedor.verSaldo() + tx.monto());
            actualizarHeap(vendedor);
            //como creacion nunca es vendedor ignoramos esto en vendedores

            nuevoBloque.agregarTransaccion(tx);
            transaccionesLista.add(tx);
            //agregamos las transacciones a medida que son recibidas a la lista enlazada
        }
        heapTransacciones = new HeapGenerico<>(transaccionesLista);
        ultimoBloque = nuevoBloque;
        //creamos un heap con las transacciones y las ordenamos por mayor monto
    }
        //por cada transaccion agregada se tendra complejidad 1/2 O(log P) ya que se actualizan en el heap usuarios
        //1 o 2 usuarios por transaccion, complejidad total O(n_b * logP )

    private void actualizarHeap(Usuario usuario) {
        int indice = usuario.obtenerIndiceHeap();
        if(indice >= 0) {
            heapUsuarios.actualizarElemento(indice);
        }
        //accedemos al handle del usuario con su id y realizamos el proceso de heapify
        //para mantener el invariante del heap
    }

    public Transaccion txMayorValorUltimoBloque(){
        return heapTransacciones.verMaximo();
        //accedemos a la raiz del heap transacciones, complejidad O(1)
    }

    public Transaccion[] txUltimoBloque(){
        return ultimoBloque.arrayTransacciones();
        //convertimos la lista enlazada de transacciones en un arreglo, como las guardamos por orden de id
        //y eliminar las transacciones hackeadas es O(1) (contamos con handles que nos indican el nodo de cada transaccion,
        // encontrar y eliminar es O(1)) esta funcion tendra complejidad O(n_b)
    }

    public int maximoTenedor(){
        return heapUsuarios.verMaximo().verId();
        //devolvemos el id del usuario que se encuentra en la raiz del heap usuario, complejidad O(1)
    }

    public int montoMedioUltimoBloque(){
        if (ultimoBloque.CantTotalTx() == 0) {
            return 0;
            //en caso de bloque sin transacciones o con solo transaccion de creacion devolvemos 0
        }
        return ultimoBloque.MontoTotalTx() / ultimoBloque.CantTotalTx();
        // para el resto de caso devolvemos la division de nuestras dos variables almacenadas, complejidad O(1)
    }

    public void hackearTx(){
        Transaccion hackeada = heapTransacciones.extraerMaximo(); //robamos la raiz del heap transacciones 
        ListaEnlazada.Nodo<Transaccion> nodo = hackeada.obtenerNodoLista(); 
        if (heapTransacciones == null) {
            return;
        } //verificamos que el heap no este vacio para evitar posibles errores
        if (hackeada.id_comprador() != 0) { 
            Usuario comprador = usuarios[hackeada.id_comprador() - 1];
            comprador.modificarSaldo(comprador.verSaldo() + hackeada.monto());
            actualizarHeap(comprador); // O(logP)
        }
        
        Usuario vendedor = usuarios[hackeada.id_vendedor() - 1];
        vendedor.modificarSaldo(vendedor.verSaldo() - hackeada.monto());
        actualizarHeap(vendedor); // O(logP)
        //si es de creacion solo actualizamos vendedor, caso contrario a ambos, complejidad total 1/2 O(logP)
        ultimoBloque.obtenerListaTx().eliminarNodo(nodo); // O(1)
        
        if (hackeada.id_comprador() != 0) {
            ultimoBloque.actualizarMontoTotal(hackeada.monto());
            ultimoBloque.actualizarCantTotal();
        }
        //actualizamos el bloque eliminando el nodo de la transaccion hackeada, restando su monto y su cantidad en caso
        //de no ser creacion
        //tambien se actualizara el heap de transacciones mediante heapify, esto tiene complejidad O(log n_b),
        //como a priori no sabemos si P > n_b o viceversa, la complejidad en peor caso estara acotada superiormente
        // por O(logP + log n_b)
    }
}