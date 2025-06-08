package aed;

// hay que hacer clase heaps en gral 
// clase usuarios una tupla <id, billetera> 
// heap {usuarios}

public class Heap<T>{ 
    private Usuarios usuarios; // crear el tipo usuarios ?? (abajo)

    public Heap() {
        usuarios = new Usuarios(); 
    }

    // faltan mil cosas para cambiar en el heap pq me fui directo al handle chau
        
    // cuando armamos el handle: 
    private class HeapUsuarios_Handle implements Handle<T> { // de tipo T pq puede apuntar a usuario o transacciones ?
        private Usuarios usuarios; // tipo usuario y posicion ? o solo usuario 

        public HeapUsuarios_Handle(Usuarios usuario){
            this.usuarios = usuario; // escribi mucho usuarios
        }
        
        public obtener(int id){ 
          return usuarios[id-1]; // pos en el array con usuarios ordenados por id?
        }
    }

    public Handle<T> handle(){ // lo mismo con el tipo T de aca 
        return new HeapUsuarios_Handle(); 
    }
}
// cuando creamos el tipo usuario seria: 

// ??????

// esta mal hecho lo que sigue 

public class Usuarios { // array con usuarios ordenados por id i guess 
    private Usuario usuario; // faltan cosas bue

}

public class Usuario { // armo la tupla 
    private int id; 
    private int billetera; 

    public Usuario(int id, int billetera){
        this.id = id; 
        this.billetera = billetera;
    }
    
    public int id(){
        return id;
    }

    public int billetera(){
        return billetera;
    }

    // y si aca ponemos una operacion que cambie dinerito?? 

    public void cambiarPlatita(int billetera) {
        // implementar algo ni idea
    }

}

