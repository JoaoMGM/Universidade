public class ABP<E extends Comparable<? super E>> {
    boolean aux = false;
    NoABP<E> raiz;

    public static class NoABP<E> {
        E elemento;
        NoABP<E> esq;
        NoABP<E> dir;

        NoABP(E e) {
            elemento = e;
            esq = null;
            dir = null;

        }

        NoABP(E e, NoABP<E> esq, NoABP<E> dir) {
            elemento = e;
            this.esq = esq;
            this.dir = dir;
        }

        public String toString() {
            return elemento.toString();
        }
    }


    public boolean isEmpty() {
        return raiz == null;
    }


    public boolean contains(E x) {
        return contains(x, raiz);
    }

    public boolean contains(E x, NoABP<E> n) {
        if (n == null) {
            return false;
        }
        Boolean foundit = (n.elemento.toString()).contains(x.toString());
        if (foundit == true) {
            aux = true;
        }
        if (n != null) {
            contains(x, n.esq);
            contains(x, n.dir);
        }
        return aux;
    }


    public E findMin() {
        if (isEmpty())
            return null;
        return findMin(raiz);
    }

    private E findMin(NoABP<E> n) {
        if (n.esq == null)
            return n.elemento;
        else
            return findMin(n.esq);
    }


    public E findMax() {
        if (isEmpty())
            return null;
        return findMax(raiz);
    }

    private E findMax(NoABP<E> n) {
        if (n.dir == null)
            return n.elemento;
        else
            return findMax(n.dir);
    }

    public void insere(E x) {
        raiz = insere(x, raiz);
    }

    private NoABP<E> insere(E x, NoABP<E> n) {
        if (n == null)
            n = new NoABP<E>(x, null, null);
        else if ((n.elemento).compareTo(x) > 0)
            n.esq = insere(x, n.esq);
        else if ((n.elemento).compareTo(x) < 0)
            n.dir = insere(x, n.dir);
        return n;
    }

    public void remove(E x) {
        raiz = remove(x, raiz);

    }

    private NoABP<E> remove(E x, NoABP<E> n) {
        if (n == null)
            return n;
        Boolean foundit = (n.elemento.toString()).contains(x.toString());
        if(foundit != true){
                n.dir = remove(x, n.dir);
                n.esq = remove(x, n.esq);
        }
        else{
            if (n.esq != null && n.dir != null) {
                E min = findMin(n.dir);
                n.elemento = min;
                n.dir = remove(min, n.dir); }
            else if (n.esq == null){
                n = n.dir;}
            else{
                n = n.esq;}
        }
        return n;
    }


    public void printEmOrdem() {
        printEmOrdem(raiz);
    }

    private void printEmOrdem(NoABP<E> n) {
        if (n != null) {
            printEmOrdem(n.esq);
            System.out.println(n.elemento + " ");
            printEmOrdem(n.dir);
        }
    }

    public void printPosOrdem() {
        printPosOrdem(raiz);
    }
    private void printPosOrdem(NoABP<E> n){
        if (n != null){
            printPosOrdem(n.esq);
            printPosOrdem(n.dir);
            System.out.println(n.elemento +" " );
        }
    }

    public void printPreOrdem() {
        printPreOrdem(raiz);
    }
    private void printPreOrdem(NoABP<E> n) {
        if (n != null) {
            System.out.println(n.elemento +" ");
            printPreOrdem(n.esq);
            printPreOrdem(n.dir);
        }
    }
}

