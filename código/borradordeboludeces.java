package aed;

// hay que hacer clase heaps en gral 
// clase usuarios una tupla <id, billetera> 
// heap {usuarios}

public class Heap{
    
}






// 
public class HeapUsuarios {
    private Nodo _usuario;
    // modificar 

    private class Nodo{
        int billetera;
        int id;

        Nodo(int id) {
               
        }
    }

    private class HeapUsuarios_Handle implements Handle<T> { 
        private int id;   

        // entrada: id 
        public Nodo obtener(int i){
            
        }
    }

    public Handle<T> handle(){
        return new HeapUsuarios_Handle(); 
    }
}
