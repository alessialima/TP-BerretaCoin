package aed;

// hay que hacer clase heaps en gral 
// clase usuarios una tupla <id, billetera> 
// heap {usuarios}

public class Heap{
    private Usuarios usuarios; // crear el tipo usuarios ?? (abajo)



    // cuando armamos el handle: 
    private class HeapUsuarios_Handle implements Handle<T> { 
        private int id;   

        // entrada: id 
        public ??obtener(int i){
            
        }
    }

    public Handle<T> handle(){
        return new HeapUsuarios_Handle(); 
    }


}


// cuando creamos el tipo usuario seria: 

public class Usuarios {
    private int id; 
    private int billetera; 

    public Usuarios(int id, int billetera){
        this.id = id; 
        this.billetera = billetera;
    }
}

