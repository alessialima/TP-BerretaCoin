package aed;
//en ultimo bloque tendremos la copia de la secuencia de transacciones
//del ultimo bloque agregado, asi como tambien
//la suma total de montos y cantidad de transacciones
//(excluyendo de creacion)
public class UltimoBloque {
    private Transaccion[] tx;
    private boolean[] txSinHackear;
    private int montoTotalTx;
    private int cantTotalTx;

    public UltimoBloque(Transaccion[] tx){
        this.tx = tx;
        this.txSinHackear = new boolean[tx.length];
        this.montoTotalTx = 0;
        this.cantTotalTx = 0;

        for (int i = 0; i < tx.length; i++){
            txSinHackear[i] = true; 
            if (tx[i].id_comprador() != 0){
                montoTotalTx += tx[i].monto();
                cantTotalTx ++;
            }
        }
    }
    
}
