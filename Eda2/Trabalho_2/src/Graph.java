import java.io.*;
import java.util.*;


public class Graph {
    ArrayList<Vertex> vertex = new ArrayList<>();
    ArrayList<Vertex> edge = new ArrayList<>();

    class Vertex{
        int v;
        int i;
        ArrayList<Vertex> adj ;

        public Vertex(int v){
            this.i = 0;
            this.v = v;
            adj = new ArrayList<>();
        }
    }

    void addVertex(int i){
        vertex.add(new Vertex(i));
    }

    void addEdge(int i, int j){
        vertex.get(i).adj.add(vertex.get(j));
        edge.add(vertex.get(j));
    }

    int[] top(int hard){
        Stack<Integer> L = new Stack<>();
        Queue<Vertex> S = new LinkedList<>();
        for(Vertex v: edge)
            v.i = v.i + 1;
        for (Vertex u : vertex)
            if(u.i==0)
                S.add(u);
        int max = S.size();
        int hardweek = 0;
        if(max > hard){
            hardweek = hardweek+1;
        }

        while(!S.isEmpty()){
            Vertex u = S.remove();
            if (!S.isEmpty()){
                for(Vertex v: u.adj){
                    v.i = v.i - 1;
                    if (v.i == 0)
                        S.add(v);
                }
            }
            //remove control queue
            if(S.isEmpty()){
                for(Vertex v: u.adj){
                    v.i = v.i - 1;
                    if (v.i == 0)
                        S.add(v);
                }
                int temp = S.size();
                if( temp > max){
                    max = temp;
                }
                if(temp > hard){
                    hardweek =  hardweek + 1;
                }
            }

            /*
            if se ela tive vazia
                copia a queue atual
            depois de copiar ver tamanho max e ver se e maior que o anterior maior
             e ver se e hard week
             */
            L.push(u.v);
        }
        //em vez de retornar L, retornar o valor max e as hard weeks
        int[] res = {max,hardweek};
        return res;
    }


    public static void main(String args[]) throws IOException {

        InputStreamReader reader= new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String var = br.readLine();
        String[] line = var.split(" ");
        int v = Integer.parseInt(line[0]);
        int rules = Integer.parseInt(line[1]);
        int hard = Integer.parseInt(line[2]);

        Graph g = new Graph();
        for(int i=0; i<v;i++){
            g.addVertex(i);
        }

        for(int i = 0; i<rules; i++){
            String rule = br.readLine();
            String[] edge = rule.split(" ");
            g.addEdge(Integer.parseInt(edge[0]),Integer.parseInt(edge[1]));
        }

        int[] res = g.top(hard);
        System.out.println(res[0]+" "+res[1]);

    }
}
