package aed;

// hay que hacer clase heaps en gral 
// clase usuarios una tupla <id, billetera> 
// heap {usuarios}

public class Heap{
    private Usuarios usuarios; // crear el tipo usuarios ?? (abajo)



    // cuando armamos el handle: 
    private class HeapUsuarios_Handle implements Handle<T> { 
        private Usuarios usuarios; // tipo usuario y posicion ? o solo usuario 
        
        public obtener(int id){ 
          return usuarios[id-1]; 
        }
    }

    public Handle<T> handle(){
        return new HeapUsuarios_Handle(); 
    }
}
// cuando creamos el tipo usuario seria: 

// ??????

public class Usuarios { // array con usuarios ordenados por id i guess 
    private Usuario usuario;

}

public class Usuario {
    private int id; 
    private int billetera; 

    public Usuarios(int id, int billetera){
        this.id = id; 
        this.billetera = billetera;
    }

}

