import java.io.*;


public class Corretor {

    LinearHashtable<String> LinearHashtable = new LinearHashtable<String>(1000000);

    public void createdic() throws Exception {
        File dic = new File ("C:\\Users\\jmmar\\OneDrive\\Ambiente de Trabalho\\wordlist-ao-20101027.txt");
        BufferedReader reader = new BufferedReader(new FileReader(dic));
        String dicword;
        while ((dicword = reader.readLine()) != null){
            LinearHashtable.insere(dicword);
        }
    }

    public void readinput() throws Exception{
        File input = new File("C:\\Users\\jmmar\\OneDrive\\Ambiente de Trabalho\\input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String inputword;
        while((inputword = reader.readLine()) != null){

        }
    }

    public void removeletter(String word){
        String aux = "";
        for(int i = 0; i<word.length(); i++){
            }
        }
    }


}
