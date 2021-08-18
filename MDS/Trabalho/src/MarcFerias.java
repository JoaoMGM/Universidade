import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class MarcFerias {


    //Janeiro =1 Feveireiro=2 etc...
    //dase split no input das datas e o id=0 do array e o dia e o id=1 e o mês
    //mes de feveiro tem 28 dias sendo que o programa e feito para 2019

    public static void MarcarFerias(App.Credenciais node, App.Pedido[] B) throws ParseException {
        Scanner DataInicio = new Scanner(System.in);
        System.out.println("Data de início (formato DD/MM)");
        String DataIni = DataInicio.nextLine();
        String[] arrayI = DataIni.split("/");
        if(DataInv(arrayI)) {
            Scanner DataFim = new Scanner(System.in);
            System.out.println("Data de fim(formato DD/MM)");
            String DataF = DataFim.nextLine();
            String[] arrayF = DataF.split("/");
            if(DataInv(arrayF)){
                int aux = DiasPeriodoNum(arrayI, arrayF, node.DiasDisponiveis);
                int uteis = DiasUteis(arrayI,arrayF,aux);
                if (!IncoroenciaData(arrayI, arrayF)) {
                        if(uteis <= node.DiasDisponiveis) {
                            int aux2 = (Pontos(arrayI, arrayF, aux));
                            App.Pedido aux3 = CriarPedido(B, DataIni, DataF, aux2, node, uteis);
                            System.out.println("O Periodo de férias de " + DataIni + " a " + DataF + " tem " + DiasUteis(arrayI, arrayF, aux) + " dias uteis e a sua pontuação é " + aux3.pontos);
                            int DiasFeriasDisponiveis = node.DiasDisponiveis;
                            System.out.println("O numero de dias de férias disponiveis para marcação é " + DiasFeriasDisponiveis);
                        }
                        else{
                            System.out.println("Nao tem dias de férias suficientes para poder marcar o período indicado");
                            System.out.println("-----------------------------------------------------------------------");
                            MarcarFerias(node,B);
                        }
                }
                else{
                    System.out.println("A data de fim é posterior á data de início");
                    System.out.println("-----------------------------------------------------------------------");
                    MarcarFerias(node,B);
                }
            }
            else{
                System.out.println("Data Invalida");
                System.out.println("-----------------------------------------------------------------------");
                MarcarFerias(node,B);
            }
        }
        else{
            System.out.println("Data Invalida");
            System.out.println("-----------------------------------------------------------------------");
            MarcarFerias(node,B);
        }
    }

    public static boolean DataInv(String[] data){
        if(Integer.parseInt(data[1]) == 2 && Integer.parseInt(data[0]) < 29){
            return true;
        }
        else if(Integer.parseInt(data[1]) == 1 || Integer.parseInt(data[1]) == 3 || Integer.parseInt(data[1]) == 5 || Integer.parseInt(data[1]) == 7 || Integer.parseInt(data[1]) == 8 ||Integer.parseInt(data[1]) == 10 || Integer.parseInt(data[1]) == 12){
            if(Integer.parseInt(data[0]) < 32){
                return true;
            }
            else{
                return false;
            }
        }
         else if(Integer.parseInt(data[1]) == 4 || Integer.parseInt(data[1]) == 6 || Integer.parseInt(data[1]) == 9 || Integer.parseInt(data[1]) == 11){
             if((Integer.parseInt(data[0]) < 31)) {
                 return true;
             }
             else{
                 return false;
             }
        }
        else{
            return false;
        }
    }

    public static boolean IncoroenciaData(String[] Ini, String[]Fim){
        if (Integer.parseInt(Ini[1]) > Integer.parseInt(Fim[1])){
            return true;
        }
        else if(Integer.parseInt(Ini[1]) == Integer.parseInt(Fim[1])) {
            if (Integer.parseInt(Ini[0]) > Integer.parseInt(Fim[0])) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
            }
    }


    public static int DiasUteis(String[] Ini, String[] Fim, int DiasPeriodo) throws ParseException {
        int DiasUteis = DiasPeriodo;
        int c=1;
        Calendar c1= Calendar.getInstance();
        int a = Integer.parseInt(Ini[0]);
        int b = Integer.parseInt(Fim[0]);
        if (Integer.parseInt(Ini[1]) == Integer.parseInt(Fim[1])){
            while(a < b+1){
                String inputDateStr = String.format(a +"/"+ Ini[1] + "/2019");
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                c1.setTime(inputDate);
                int dow = c1.get(Calendar.DAY_OF_WEEK);
                if(dow == 7 || dow == 1){
                    DiasUteis--;
                }
                a++;
            }
        }
        if(Integer.parseInt(Ini[1]) != Integer.parseInt(Fim[1])){
            if(Integer.parseInt(Ini[1]) == 2){
                while(a < 29){
                    String inputDateStr = String.format(a +"/"+ Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if(dow == 1 || dow == 7){
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1){
                    String inputDateStr = String.format(c +"/"+ Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if(dow == 7 || dow == 1){
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 1) {
                while (a < 32) {
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }

                while (c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 3){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 4){
                while(a < 31){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 5){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 6){
                while(a < 30){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 7){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 8){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 9){
                while(a < 31){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 10){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 11){
                while(a < 31){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }
            if(Integer.parseInt(Ini[1]) == 12){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    c++;
                }
            }

        }
        return DiasUteis;
    }


    public static int DiasPeriodoNum(String[] Ini, String[]Fim, int DiasDisp ) {
        int DiasPeriodo = 0;
        if(Integer.parseInt(Ini[1]) < 13 || (Integer.parseInt(Fim[1])) < 13){
            if (Integer.parseInt(Ini[1]) == Integer.parseInt(Fim[1])) {
                DiasPeriodo = ((Integer.parseInt(Fim[0]))+1) - (Integer.parseInt(Ini[0]));
                    return DiasPeriodo;
            }
            else if (Integer.parseInt(Ini[1]) == 1 || Integer.parseInt(Ini[1]) == 3 || Integer.parseInt(Ini[1]) == 5 || Integer.parseInt(Ini[1]) == 7 || Integer.parseInt(Ini[1]) == 8 || Integer.parseInt(Ini[1]) == 10 || Integer.parseInt(Ini[1]) == 12){
                if (Integer.parseInt(Fim[1]) == 2) {
                    DiasPeriodo = (31 - Integer.parseInt(Ini[0])) + (Integer.parseInt(Fim[0]));
                        return DiasPeriodo;
                }
                else {
                    DiasPeriodo = (31 - Integer.parseInt(Ini[0])) + (Integer.parseInt(Fim[0]));
                        return DiasPeriodo;
                    }
                }
            }
            else if (Integer.parseInt(Ini[1]) == 2) {
                DiasPeriodo = (28 - Integer.parseInt(Ini[0])) + (Integer.parseInt(Fim[0]));
                    return DiasPeriodo;
            }
            else if (Integer.parseInt(Ini[1]) == 4 || Integer.parseInt(Ini[1]) == 6 || Integer.parseInt(Ini[1]) == 9 || Integer.parseInt(Ini[1]) == 11) {
                DiasPeriodo = (30 - Integer.parseInt(Ini[0])) + (Integer.parseInt(Fim[0]));
                    return DiasPeriodo;
            }
        System.out.println(DiasPeriodo);
        return DiasPeriodo;
    }


    public static App.Pedido CriarPedido(App.Pedido[] B , String Ini, String Fim, int Points, App.Credenciais node, int Uteis){
        int i = 0;
        while (B[i] != null) {
            i++;
        }
        if (B[i] == null) {
            App.Pedido Pedido = new App.Pedido();
            Pedido.pontos = Points;
            Pedido.IDTrabalhadorPedido = node.ID;
            Pedido.Periodo = "Periodo de Ferias de " + Ini + " a " + Fim;
            Pedido.DiasUteis = Uteis;
            node.PontosTrabalhador = node.PontosTrabalhador + Pedido.pontos;
            node.DiasDisponiveis = node.DiasDisponiveis - Uteis;
            B[i] = Pedido;
        }
        return B[i];
    }


    public static int Pontos(String[] Ini, String[] Fim, int DiasPeriodo) throws ParseException {
        int PontosPeriodo = 0;
        int DiasUteis = DiasPeriodo;
        int DiasUteis2 = 0;
        int DiasUMesSeguin = 0;
        int c=1;
        Calendar c1= Calendar.getInstance();
        int a = Integer.parseInt(Ini[0]);
        int b = Integer.parseInt(Fim[0]);
        if (Integer.parseInt(Ini[1]) == Integer.parseInt(Fim[1])){
            while(a < b+1){
                String inputDateStr = String.format(a +"/"+ Ini[1] + "/2019");
                Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                c1.setTime(inputDate);
                int dow = c1.get(Calendar.DAY_OF_WEEK);
                if(dow == 7 || dow == 1){
                    DiasUteis--;
                }
                a++;
            }
            if(Integer.parseInt(Ini[1]) == 1 || Integer.parseInt(Ini[1]) == 2 || Integer.parseInt(Ini[1]) == 11){
                PontosPeriodo = DiasUteis*1;
            }
            if(Integer.parseInt(Ini[1]) == 3 || Integer.parseInt(Ini[1]) == 4 || Integer.parseInt(Ini[1]) == 5 || Integer.parseInt(Ini[1]) == 10){
                PontosPeriodo = DiasUteis*2;
            }
            if(Integer.parseInt(Ini[1]) == 6 || Integer.parseInt(Ini[1]) == 9) {
                PontosPeriodo = DiasUteis*3;
            }
            if(Integer.parseInt(Ini[1]) == 7 || Integer.parseInt(Ini[1]) == 8 || Integer.parseInt(Ini[1]) == 12 ){
                PontosPeriodo = DiasUteis*4;
            }
        }
        if(Integer.parseInt(Ini[1]) != Integer.parseInt(Fim[1])){
            if(Integer.parseInt(Ini[1]) == 2){
                while(a < 29){
                    String inputDateStr = String.format(a +"/"+ Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if(dow == 1 || dow == 7){
                        DiasUteis--;
                    }
                    if(dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6){
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1){
                    String inputDateStr = String.format(c +"/"+ Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if(dow == 7 || dow == 1){
                        DiasUteis--;
                    }
                    if(dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6){
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis*1 + DiasUMesSeguin*2;
            }
            if(Integer.parseInt(Ini[1]) == 1) {
                while (a < 32) {
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }

                while (c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;

                }
                PontosPeriodo = DiasUteis*1 + DiasUMesSeguin*1;
            }
            if(Integer.parseInt(Ini[1]) == 3){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*2 + DiasUMesSeguin*2;
            }
            if(Integer.parseInt(Ini[1]) == 4){
                while(a < 31){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*2 + DiasUMesSeguin*2;
            }
            if(Integer.parseInt(Ini[1]) == 5){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 1 || dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*2 + DiasUMesSeguin*3;
            }
            if(Integer.parseInt(Ini[1]) == 6){
                while(a < 30){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*3 + DiasUMesSeguin*4;
            }
            if(Integer.parseInt(Ini[1]) == 7){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*4 + DiasUMesSeguin*4;
            }
            if(Integer.parseInt(Ini[1]) == 8){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*4 + DiasUMesSeguin*3;
            }
            if(Integer.parseInt(Ini[1]) == 9){
                while(a < 31){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*3 + DiasUMesSeguin*2;
            }
            if(Integer.parseInt(Ini[1]) == 10){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*2 + DiasUMesSeguin*1;
            }
            if(Integer.parseInt(Ini[1]) == 11){
                while(a < 31){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*1 + DiasUMesSeguin*4;
            }
            if(Integer.parseInt(Ini[1]) == 12){
                while(a < 32){
                    String inputDateStr = String.format(a + "/" + Ini[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUteis2++;
                    }
                    a++;
                }
                while(c < b+1) {
                    String inputDateStr = String.format(c + "/" + Fim[1] + "/2019");
                    Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputDateStr);
                    c1.setTime(inputDate);
                    int dow = c1.get(Calendar.DAY_OF_WEEK);
                    if (dow == 7 || dow == 1) {
                        DiasUteis--;
                    }
                    if (dow == 2 || dow == 3 || dow == 4 || dow == 5 || dow == 6) {
                        DiasUMesSeguin++;
                    }
                    c++;
                }
                PontosPeriodo = DiasUteis2*4 + DiasUMesSeguin*1;
            }

        }
        return PontosPeriodo;
    }

}
