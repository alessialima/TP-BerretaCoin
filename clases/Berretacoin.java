package aed;

public class Berretacoin {

    public Berretacoin(int n_usuarios){
        throw new UnsupportedOperationException("Implementar!");
    }

    public void agregarBloque(Transaccion[] transacciones){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion txMayorValorUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion[] txUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int maximoTenedor(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int montoMedioUltimoBloque(){
        if  (MontoTotalTx == 0) {
            return 0;
        }
        else {
            res = MontoTotalTx / CantTotalTx;
            return res;
        }
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}
