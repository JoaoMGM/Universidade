import java.io.*;
import java.util.*;

public class Graph {
    ArrayList<Vertex> vertex = new ArrayList<>();

    static class Vertex{
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
        vertex.get(j).i++;
    }

    int[] topoligicalsort(int hard){
        Queue<Vertex> Q = new LinkedList<>();
        for (Vertex u : vertex) {
            if (u.i == 0) {
                Q.add(u);
            }
        }
        int max = Q.size(); //define maximum tasks done in the first week as the first number of tasks we have
        int hardweek = 0;
        int counter = max;
        if(max > hard){
            hardweek = hardweek+1;
        }

        while(!Q.isEmpty()){
            Vertex u = Q.remove();
            counter--;
            for(Vertex v: u.adj){
                v.i = v.i - 1;
                if (v.i == 0)
                    Q.add(v);
            }
            if(counter == 0){ //when its 0 means  the week is finished
                counter=Q.size(); //new number of tasks in a week
                if( counter > max){ //check if its the bigger week
                    max = counter;
                }
                if(counter > hard){ //to see if it an hard week
                    hardweek =  hardweek + 1;
                }
            }
        }
        return new int[]{max,hardweek};
    }


    public static void main(String[] args) throws IOException {

        InputStreamReader reader= new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String var = br.readLine();
        String[] line = var.split(" ");
        int v = Integer.parseInt(line[0]); //vertex
        int edges = Integer.parseInt(line[1]); //number of edges to be created
        int hard = Integer.parseInt(line[2]); //number used to compare to acknowledge hardweeks

        Graph g = new Graph();
        for(int i=0; i<v;i++){
            g.addVertex(i);
        }

        for(int i = 0; i<edges; i++){
            String rule = br.readLine();
            String[] edge = rule.split(" ");
            g.addEdge(Integer.parseInt(edge[0]),Integer.parseInt(edge[1]));
        }

        int[] res = g.topoligicalsort(hard);
        System.out.println(res[0]+" "+res[1]);

    }
}
