public class AVL<E extends Comparable<? super E>> {

    NoAVL<E> raiz;


    public class NoAVL<E> {
        int altura;
        E elemento;
        NoAVL<E> esq;
        NoAVL<E> dir;
        boolean flag;

        NoAVL(E e) {
            elemento = e;
            esq = null;
            dir = null;
            altura = 0;
            flag = true;
        }

        NoAVL(E e, NoAVL<E> esq, NoAVL<E> dir) {
            elemento = e;
            this.esq = esq;
            this.dir = dir;
            altura = 0;
            flag = true;
        }

        public String toString() {
            return elemento.toString();
        }

        public void updatealtura(int i) {
            altura = i;
        }
    }

    public boolean isEmpty() {
        if (raiz == null)
            return false;
        return true;
    }

    public boolean contains(E x) {
        return contains(x, raiz);
    }

    private boolean contains(E x, NoAVL<E> n) {
        if (n == null) {
            return false;
        }

        Boolean foundit = (n.elemento.toString()).contains(x.toString()) && n.flag;
        if (foundit == true) {
            System.out.println(n.elemento);
        }
        if (n != null) {
            contains(x, n.esq);
            contains(x, n.dir);
        }
        return true;
    }

    public E findMin() {
        if (isEmpty())
            return null;
        return findMin(raiz);
    }

    private E findMin(NoAVL<E> n) {
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

    private E findMax(NoAVL<E> n) {
        if (n.dir == null)
            return n.elemento;
        else
            return findMax(n.dir);
    }

    public void remove(E x) {
        raiz = remove(x, raiz);
    }


    private NoAVL<E> remove(E x, NoAVL<E> n) {
        if (n == null) {
            return n;
        }
        Boolean foundit = (n.elemento.toString()).contains(x.toString()) && n.flag;
        if (!foundit) {
            n.dir = remove(x, n.dir);
            n.esq = remove(x, n.esq);
        } else {
            n.flag = false;
        }

        return n;
    }

    public void insert(E x) {
        raiz = insert(x, raiz);
    }

    public NoAVL<E> insert(E x, NoAVL<E> n) {
        if (n == null)
            n = new NoAVL<>(x);
        else if (n.elemento.compareTo(x) > 0)
            n.esq = (insert(x, n.esq));
        if (altura(n.esq) - altura(n.dir) == 2) {
            if (x.compareTo(n.esq.elemento) < 0) {
                n = rodaEsq(n);
            } else {
                n = rodaDirEsq(n);
            }
        } else if (n.elemento.compareTo(x) < 0)
            n.dir = (insert(x, n.dir));
        if (altura(n.dir) - altura(n.esq) == 2) {
            if (x.compareTo(n.dir.elemento) > 0) {
                n = rodaDir(n);
            } else {
                n = rodaEsqDir(n);
            }
        }

        n.updatealtura(max(altura(n.esq), altura(n.dir)) + 1);
        return n;
    }

    private NoAVL rodaEsq(NoAVL c2) {
        NoAVL c1 = c2.esq;
        c2.esq = c1.dir;
        c1.dir = c2;
        c2.updatealtura(max(altura(c2.esq), altura(c2.dir)) + 1);
        c1.updatealtura(max(altura(c1.esq), c2.altura) + 1);

        return c1;
    }

    private NoAVL rodaDir(NoAVL c1) {
        NoAVL c2 = c1.dir;
        c1.dir = c2.esq;
        c2.esq = c1;
        c1.updatealtura(max(altura(c1.esq), altura(c1.dir)) + 1);
        c2.updatealtura(max(altura(c2.dir), c1.altura) + 1);

        return c2;
    }

    private NoAVL rodaDirEsq(NoAVL c3) {
        c3.esq = (rodaDir(c3.esq));
        return rodaEsq(c3);
    }

    private NoAVL rodaEsqDir(NoAVL c1) {
        c1.dir = (rodaEsq(c1.dir));
        return rodaDir(c1);
    }

    public int altura(NoAVL n) {
        if (n == null) {
            return -1;
        } else {
            return n.altura;
        }
    }

    public static int max(int lh, int rh) {
        return lh > rh ? lh : rh;
    }

    public void printEmOrdem() {
        printEmOrdem(raiz);
    }

    private void printEmOrdem(NoAVL<E> n) {
        if (n != null) {
            printEmOrdem(n.esq);
            if (n.flag)
                System.out.println(n.elemento + " ");
            printEmOrdem(n.dir);
        }
    }

    public void printPosOrdem() {
        printPosOrdem(raiz);
    }

    private void printPosOrdem(NoAVL<E> n) {
        if (n != null) {
            printPosOrdem(n.esq);
            printPosOrdem(n.dir);
            System.out.println(n.elemento + " ");
        }
    }

    public void printPreOrdem() {
        printPreOrdem(raiz);
    }

    private void printPreOrdem(NoAVL<E> n) {
        if (n != null) {
            System.out.println(n.elemento + " ");
            printPreOrdem(n.esq);
            printPreOrdem(n.dir);
        }
    }
}



