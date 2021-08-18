


public class Agenda {

    ABP arvore = new ABP();

    public class Contato implements Comparable<Contato>{
        String nomeid;
        int numero1 = -1 , numero2 = -1;

        public Contato(String nomeid){
            this.nomeid = nomeid;
        }

        public Contato(String nomeid, int numero1){
            this.nomeid = nomeid;
            this.numero1= numero1;
        }

        public Contato(String nomeid, int numero1, int numero2){
            this.nomeid = nomeid;
            this.numero1= numero1;
            this.numero2= numero2;
        }

        public void setNomeid(String nomeid) {
            this.nomeid = nomeid;
        }

        public void setNumero1(int numero1) {
            this.numero1 = numero1;
        }

        public void setNumero2(int numero2) {
            this.numero2 = numero2;
        }

        public int getNumero1() {
            return numero1;
        }

        public int getNumero2() {
            return numero2;
        }

        public String getNomeid() {
            return nomeid;
        }

        public int compareTo(Contato CompString){
            return getNomeid().compareTo(CompString.getNomeid());
        }

        public String toString(){
            if(numero2 == -1){
                return nomeid + " " + numero1;
            }
            else{
                return nomeid + " " + numero1 + " " + numero2;
            }
        }
    }



    Contato found;
    public Contato find(ABP.NoABP<Contato> n, int x){
        if( n != null){
            find(n.esq, x);
            if (n.elemento.numero1 == x || n.elemento.numero2 == x){
                found = n.elemento;
            }
            find(n.dir, x);
        }
        return found;
    }

    public Contato find(int x){
        return find(arvore.raiz, x);
    }

    public Contato findname(ABP.NoABP<Contato> n, String name) {
        if (n != null) {
            findname(n.esq, name);
            if (n.elemento.nomeid == name){
                found = n.elemento;
            }
            findname(n.dir, name);
        }
        return found;
    }

    public Contato findname(String name){
        return findname(arvore.raiz , name);
    }


    public void adicionar(String nomeid, int x){
        Contato newcont = new Contato(nomeid, x);
        newcont.setNomeid(nomeid);
        newcont.setNumero1(x);
        if(!arvore.contains(newcont)) {
            arvore.insere(newcont);
        }
        else{
            System.out.println("O contato inserido já existe.");
        }
    }

    public void remover(String nomeid){
        Contato z = findname(nomeid);
        arvore.remove(z);
    }

    public void remover(int numero){
        if(find(numero) != null) {
            Contato n = find(numero);
            arvore.remove(n);
            found = null;
        }
        else{
            System.out.println("Contato não encontrado.");
        }
    }


    public void listar(){
        arvore.printEmOrdem();
    }

    public void chamador(int numero){
        if(find(numero) != null){
            Contato n = find(numero);
            System.out.println(n.nomeid);
        }
        if(find(numero) == null){
            System.out.println("DESCONHECIDO");
        }
    }

    public void editar(int numero, int subs){
        if(find(numero) != null){
            Contato n = find(numero);
            if(numero == n.numero1) {
                n.numero1 = subs;
            }
            else{
                n.numero2 = subs;
            }
        }
        else{
            System.out.println("Contato não encontrado.");
        }
    }

    public void editar(String nome, int newnum){
        if(arvore.contains(nome)){
            Contato n = findname(nome);
            n.numero2 = newnum;
        }
        else{
            System.out.println("Contato não encontrado.");
        }
    }

    public void editar(String nome, String nomesubs){
        if(arvore.contains(nome)){
            Contato n = findname(nome);
            arvore.remove(n);
            n.nomeid = nomesubs;
            arvore.insere(n);

        }
        else{
            System.out.println("Contato não encontrado.");
        }
    }


    public static void main(String[] args) {
        Agenda Agenda = new Agenda();
        Agenda.adicionar("Joao",123);
        /*/Agenda.editar("Joao",934);/*/
        Agenda.adicionar("Joao 2",456);
        Agenda.listar();
        Agenda.chamador(456);
        Agenda.remover(456);
        Agenda.listar();
        System.out.println("_______________");
        Agenda.adicionar("Ana",987);
        Agenda.adicionar("Raul",965);
        Agenda.listar();
        Agenda.chamador(456);
        Agenda.editar("Ana","Zeca");
        Agenda.editar("Zeca",579);
        Agenda.adicionar("Petersen",333);
        System.out.println("_______________");
        Agenda.listar();
    }
}


