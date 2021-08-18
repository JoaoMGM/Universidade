import java.text.ParseException;
import java.util.*;
public class App{

    public static class Pedido{
        public int pontos;
        public String Periodo;
        public String IDTrabalhadorPedido;
        public int DiasUteis;
    }

    public static class PedidoAprovado{
        public String Periodo;
        public String IDTrabalhadorPedido;
    }

    public static class Credenciais{
        String ID;
        String Password;
        int Cargo; //1 para Gest, 0 para Colaborador
        int DiasDisponiveis;
        int PontosTrabalhador;
    }

    public static Pedido[] Pedidos = new Pedido[100];
    public static PedidoAprovado[] PedidosApro = new PedidoAprovado[100];
    public static Credenciais[] Trabalhadores = new Credenciais[200];

    public static void Run(Credenciais node, Credenciais[] Trabalhadores, Pedido[] Pedidos, PedidoAprovado[] PedidosApro) throws ParseException {
        int k = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o seu ID");
        String verifI =scan.nextLine();
        while( !verifI.equals(Trabalhadores[k].ID)){
            k++;
            if(Trabalhadores[k] == null){
                System.out.println("ID inexistente");
                Run(node, Trabalhadores, Pedidos, PedidosApro);
            }
        }
        if (verifI.equals(Trabalhadores[k].ID)) {
            System.out.println("Insira a sua Password");
            String verifP = scan.nextLine();
            if (verifP.equals(Trabalhadores[k].Password)) {
                if (Trabalhadores[k].Cargo == 1) {
                    Gestor(Trabalhadores[k], Pedidos, PedidosApro, Trabalhadores);
                }
                if (Trabalhadores[k].Cargo == 0) {
                    Colaborador(Trabalhadores[k], Pedidos, PedidosApro);
                }
            }
            else{
                System.out.println("Password Incorreta");
                Run(node, Trabalhadores, Pedidos, PedidosApro);
            }
        }
    }

    public static void Colaborador(Credenciais node, Pedido[] Pedidos,  PedidoAprovado[] PedidosApro) throws ParseException {
        System.out.println("Bem vindo, " + node.ID);
        System.out.println("Pretende (M)arcar Férias, (C)onsultar Férias ou fazer (L)ogout?");
        Scanner scanner = new Scanner(System.in);
        char ans= scanner.nextLine().charAt(0);

        if(ans=='m' || ans=='M'){
            System.out.println("Marcação de férias: ");
            MarcFerias.MarcarFerias(node,Pedidos);
            System.out.println("-----------------------------------------------------------------------");
            Colaborador(node,Pedidos,PedidosApro);
            //inserir datas
        }
        else if(ans=='c'|| ans=='C'){
            System.out.println("As suas férias: ");
            ConsultFerias.ConsultarFerias(Trabalhadores,node.ID,Pedidos,PedidosApro);
            System.out.println("-----------------------------------------------------------------------");
            Colaborador(node,Pedidos,PedidosApro);
            //if runtime
            //else break quando quiser parar
        }
        else if (ans == 'l' || ans == 'L'){
            System.out.println("Sessão Encerrada.");
            System.out.println("-----------------------------------------------------------------------");
            Run(node, Trabalhadores, Pedidos, PedidosApro);
        }
        else{
            System.out.println("Ação não encontrada");
            System.out.println("-----------------------------------------------------------------------");
            Colaborador(node,Pedidos,PedidosApro);
            //Colaborador(node);
            //loopback
        }
    }

    public static void Gestor(Credenciais node, Pedido[] Pedidos,  PedidoAprovado[] PedidosApro, Credenciais[] Trabalhadores) throws ParseException {
        System.out.println("Bem vindo, Gestor " + node.ID);
        System.out.println("Pretende (A)provar Férias ou fazer (L)ogout?");
        Scanner scanner = new Scanner(System.in);
        char ans= scanner.nextLine().charAt(0);

        if(ans=='a' || ans=='A'){
            System.out.println("Gerindo pedidos de férias: ");
            ValidarMarcFerias.ValidarMarcacaoFerias(Pedidos, PedidosApro, Trabalhadores);
            System.out.println("-----------------------------------------------------------------------");
            Gestor(node,Pedidos,PedidosApro,Trabalhadores);
            //if se houver pedidos printa
            //else nn haja pedidos pra administrar
        }
        else if (ans == 'l' || ans == 'L') {
            System.out.println("Sessão Encerrada.");
            System.out.println("-----------------------------------------------------------------------");
            Run(node, Trabalhadores, Pedidos, PedidosApro);
        }
        else{
            System.out.println("Ação não encontrada");
            System.out.println("-----------------------------------------------------------------------");
            Gestor(node,Pedidos,PedidosApro,Trabalhadores);
            //loopback
        }
    }

    public static void main(String[] args) throws ParseException {
        Credenciais node = new Credenciais();
        Credenciais node2 = new Credenciais();
        node.ID= "Steve" ; node.Password= "Lacy" ; node.Cargo= 1 ; node.DiasDisponiveis = 22; node.PontosTrabalhador = 0;
        node2.ID= "Chester" ; node2.Password= "Benny" ; node2.Cargo= 0 ; node2.DiasDisponiveis = 22; node2.PontosTrabalhador = 0;
        Trabalhadores[0]=node;
        Trabalhadores[1]=node2;



        Run(node, Trabalhadores,Pedidos,PedidosApro);
    }
}

