package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

 // Tomamos como prioridad principal la transaccion de mayor monto
 //y tomamos a la transaccion de mayor id para desempatar casos donde
 //ambas transacciones sean de igual monto
    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto != otro.monto){
            return this.monto - otro.monto;
        }
        return this.id - otro.id;
    }
// dos transacciones seran iguales si tienen el mismo id
    @Override
    public boolean equals(Object otro){
        if (this == otro){
            return true;
        }
        if (otro == null || getClass() != otro.getClass()){
            return false;
        }
        Transaccion that = (Transaccion) otro;
        return id == that.id;
    }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }
//agregamos este metodo para poder acceder al id de una transaccion
    public int id() {
        return id;
    }
}
