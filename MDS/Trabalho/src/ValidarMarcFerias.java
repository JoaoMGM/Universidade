import java.util.Scanner;

public class ValidarMarcFerias {
    public static void ValidarMarcacaoFerias(App.Pedido[] PorApr, App.PedidoAprovado[] Apr, App.Credenciais[] Trabalhadores){
        if( PorApr[0] == null){
            System.out.println("Não existem pedidos por aprovar.");
        }
        else {
            int u = 0;
            int i = 0;
            int Idpedido = 0;
            while (PorApr[i] != null) {
                while (Trabalhadores[u].ID != PorApr[i].IDTrabalhadorPedido) {
                    u++;
                }
                if (Trabalhadores[u].ID == PorApr[i].IDTrabalhadorPedido) {
                    System.out.println("Pedido ID = " + Idpedido + " : " + PorApr[i].Periodo + " -- ID: " + PorApr[i].IDTrabalhadorPedido + " -- Pontos: " + (Trabalhadores[u].PontosTrabalhador));
                }
                i++;
                Idpedido++;
            }
            System.out.println("Qual pedido deseja deseja Aprovar ou Rejeitar?");
            Scanner IDPed = new Scanner(System.in);
            String IDP = IDPed.nextLine();
            if(isStringInt(IDP)) {
                int IdP = Integer.parseInt(IDP);
                if (IdP <= Idpedido - 1) {
                    System.out.println("Deseja (A)provar ou (R)ejeitar o Pedido " + IdP + " ?");
                    Scanner Ans = new Scanner(System.in);
                    String Answer = Ans.nextLine();
                    if (Answer.equals("A") || Answer.equals("a") || Answer.equals("Aprovar") || Answer.equals("aprovar")) {
                        int k = 0;
                        while (Apr[k] != null) {
                            k++;
                        }
                        App.PedidoAprovado PedidoAprovado = new App.PedidoAprovado();
                        PedidoAprovado.Periodo = PorApr[IdP].Periodo;
                        PedidoAprovado.IDTrabalhadorPedido = PorApr[IdP].IDTrabalhadorPedido;
                        Apr[k] = PedidoAprovado;
                        if (IdP == 0) {
                            if (PorApr[IdP + 1] == null) {
                                PorApr[IdP] = null;
                            } else {
                                PorApr[IdP] = PorApr[IdP + 1];
                                IdP++;
                                while (PorApr[IdP + 1] != null) {
                                    PorApr[IdP] = PorApr[IdP+1];
                                    IdP++;
                                }
                            }
                        }
                        else if (PorApr[IdP + 1] == null) {
                            PorApr[IdP] = null;
                        }
                        else if (PorApr[IdP+1] != null) {
                            while (PorApr[IdP + 1] != null) {
                                PorApr[IdP] = PorApr[IdP+1];
                                IdP++;
                            }
                        }
                        PorApr[IdP] = null;
                        System.out.println("Pedido de férias aprovado com sucesso.");
                    }
                    else if (Answer.equals("R") || Answer.equals("r") || Answer.equals("Rejeitar") || Answer.equals("rejeitar")) {
                        int search = 0;
                        while (PorApr[IdP].IDTrabalhadorPedido != Trabalhadores[search].ID) {
                            search++;
                        }
                        Trabalhadores[search].PontosTrabalhador = Trabalhadores[search].PontosTrabalhador - PorApr[IdP].pontos;
                        Trabalhadores[search].DiasDisponiveis = Trabalhadores[search].DiasDisponiveis - PorApr[IdP].DiasUteis;
                        if (IdP == 0) {
                            if (PorApr[IdP + 1] == null) {
                                PorApr[IdP] = null;
                            } else {
                                PorApr[IdP] = PorApr[IdP + 1];
                                IdP++;
                                while (PorApr[IdP + 1] != null) {
                                    PorApr[IdP] = PorApr[IdP+1];
                                    IdP++;
                                }
                            }
                        }
                        else if (PorApr[IdP + 1] == null) {
                            PorApr[IdP] = null;
                        }
                        else if (PorApr[IdP+1] != null) {
                            while (PorApr[IdP + 1] != null) {
                                PorApr[IdP] = PorApr[IdP+1];
                                IdP++;
                            }
                        }
                        PorApr[IdP] = null;
                        System.out.println("O Pedido de férias foi rejeitado.");
                    } else {
                        System.out.println("Ação não reconhecida. Volte a indicar o ID do Pedido e a ação a executar.");
                        ValidarMarcacaoFerias(PorApr, Apr, Trabalhadores);
                    }
                } else {
                    System.out.println("ID Inexistente");
                    ValidarMarcacaoFerias(PorApr, Apr, Trabalhadores);
                }
            }
            else {
                System.out.println("ID inválido, por favor insira um número.");
                ValidarMarcacaoFerias(PorApr,Apr,Trabalhadores);
            }
        }
    }

    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

}
