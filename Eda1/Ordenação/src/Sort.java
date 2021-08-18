import java.util.*;

public class Sort {

    public static void main(String[] args) {
        Comparable[] z = le_array();
        Comparable[] x = z;
        method1(z);
        System.out.println("Heapsort:");
        printArray(z);
        method2(x);
        System.out.println("Quicksort:");
        printArray(x);
    }



        public static Comparable[] le_array(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite os numeros: ");
        String numeros = input.nextLine();
        String[] fst =  numeros.split(" ");
        Comparable[] used = new Comparable[fst.length];
        for (int i= 0; i < fst.length; i++){
            String a = fst[i];
            int b = Integer.parseInt(a);
            used[i] = b;
        }
        return used;
    }

    public static void printArray(Object[] x){
        for(int i = 0 ; i <x.length; i++){
            System.out.print(x[i] + "; ");
        }
        System.out.print("\n");
    }

    public static void method1(Comparable[] A){
        heapsort(A);
    }

    public static void method2(Comparable[] A){
        qsort(A);
    }





    private static void troca(Comparable[] a ,int j,int i){
        Comparable tmp=a[i];
        a[i]=a[j];
        a[j]=tmp;
    }



    private static void heapify(Comparable[] h, int i, int size){
        int j=2*i+1;//fe
        if ((j< size) && (h[j].compareTo(h[j+1])<0)){
            j++;}
        if ((j<=size) && h[i].compareTo(h[j])<0){
            troca (h,i,j);
            heapify(h,j,size);
        }
    }


    public static void heapsort(Comparable[] A){
        build_heap(A);


        for (int i=A.length-1;i>0;i--){
            troca(A,0,i);
            heapify(A,0,i-1);
        }
    }

    private static void build_heap(Comparable[] A){
        for (int i= A.length /2 ; i>=0; i--){
            heapify(A,i,A.length-1);
        }
    }

    public static void qsort(Comparable[] a){
        qsort(a, 0, a.length-1);
    }

    private static void qsort(Comparable[] a, int esq, int dir){
        if (esq<dir){
            int k=particao(a,esq,dir);

            qsort(a,esq,k);
            qsort(a,k+1,dir);
        }
    }

    private static int particao(Comparable[] a, int esq, int dir){
        int i=esq;
        int j=dir;
        Comparable pivot=a[(esq+dir)/2];
        boolean achou=false;
        while (!achou){
            while (a[i].compareTo(pivot)<0) i++;
            while (a[j].compareTo(pivot)>0) j--;
            if (i<j){
                troca(a,i,j);
                i++;
                j--;
            }
            else
                achou=true;

        }
        return j;
    }
}



