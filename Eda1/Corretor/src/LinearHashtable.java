public class LinearHashtable<T> extends Hashtable<T>{
    private static final int tamanhoPorDefeito = 200;

    public LinearHashtable(){
        this(tamanhoPorDefeito);
    }

    public LinearHashtable(int tamanho) {
        alocarTabela(tamanho);
        tornarVazia();
    }

    //metodo que implementa o acesso linear
    protected int procPos(T s){
        int colisoes = 1;
        int posActual = hash(s);
        while(table[posActual] != null && !table[posActual].elem.equals(s)) {
            posActual += colisoes; // f(i) = i
            colisoes += 1;
            if(posActual >= table.length)
                posActual = posActual % table.length;
        }

        return posActual;
    }
}
