public class ConsultFerias {

    public static void ConsultarFerias(App.Credenciais[] Trabalhadores, String ID, App.Pedido[] PorApr, App.PedidoAprovado[] Apr){
        int i=0;
        int u=0;
        if(SeExistePedidos(ID,PorApr,Apr)) {
            while (PorApr[i] != null) {
                if (PorApr[i].IDTrabalhadorPedido == ID) {
                    System.out.println(PorApr[i].Periodo + " em Estado de Aprovação.");
                }
                i++;
            }
            while (Apr[u] != null) {
                if (Apr[u].IDTrabalhadorPedido == ID) {
                    System.out.println(Apr[u].Periodo + " em Estado Aprovado.");
                }
                u++;
            }
        }
        else{
            System.out.println("Não foram encontrados pedidos de férias.");
        }
        int search = 0;
        while(Trabalhadores[search].ID != ID){
            search++;
        }
        System.out.println("Numero de férias disponíveis = " + Trabalhadores[search].DiasDisponiveis);
    }

    public static Boolean SeExistePedidos(String ID, App.Pedido[] PorApr, App.PedidoAprovado[] Apr){
        int i=0;
        int u=0;
        while(  PorApr[i] != null){
            if(PorApr[i].IDTrabalhadorPedido == ID){
               return true;
            }
            i++;
        }
        while( Apr[u] != null){
            if(Apr[u].IDTrabalhadorPedido == ID){
                return true;
            }
            u++;
        }
        return false;
    }

}
