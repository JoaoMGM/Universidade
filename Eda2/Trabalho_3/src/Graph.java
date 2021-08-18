import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Vertex{
    int v;
    int d = 5000000; // valor maximo

    public Vertex(int v){
        this.v = v;
    }
}

class Edge {
    int s = 0, d = 0, w = 0;    // s = vertice origem ; d = vertice destino ; w = custo entre s e d
}


class Graph {
    int V, E;

    Vertex[] vertex;
    Edge[] edges;

    // Construtor
    Graph(int v, int e) {
        V = v;
        E = e;
        vertex = new Vertex[v];
        for(int i = 0; i < v;i++){
            vertex[i] = new Vertex(i);
        }
        edges = new Edge[e];
        for (int i = 0; i < e; i++){
            edges[i] = new Edge();
        }
    }


    // Metodos
    void addEdgedata(int s, int d, int w, int i){
        edges[i].s = s;
        edges[i].d = d;
        edges[i].w = w;
    }

    void relax(Vertex u,Vertex v, int w){
        if (u.d + w < v.d){
            v.d = u.d + w;
        }
    }


    void BellmanFord() {
        vertex[0].d = 0; //o vertice 0 é sempre o start

        for (int i = 1; i < V; i++) {
            for (Edge e: edges) {
                relax(vertex[e.s],vertex[e.d],e.w);
                if (e.d == V - 1 && vertex[e.d].d < 0){ // se achar um caminho para o exit com valor negativo
                    System.out.println("yes");
                    return;
                }
            }
        }

        //detetar os ciclos negativo
        for (Edge e: edges) {
            if (vertex[e.s].d + e.w < vertex[e.d].d) {
                System.out.println("yes");
                return;
            }
        }
        System.out.println("no"); // senao achar nenhum caminho negativo para o Exit(V-1) nem ciclos negativos
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader reader= new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String var = br.readLine();
        String[] line = var.split(" ");
        int V = Integer.parseInt(line[0]); //vertex
        int E = Integer.parseInt(line[1]); //number of edges to be created


        Graph graph = new Graph(V, E); // criar grafos com V vertex e E edges


        for(int i = 0; i<E;i++){
            String rule = br.readLine();
            String[] edge = rule.split(" "); // [origem,destino, B ou C,w]
            if(edge[2].equals("B")){ //gold bag
                graph.addEdgedata(Integer.parseInt(edge[0]),Integer.parseInt(edge[1]),Integer.parseInt(edge[3]),i);
            }
            if(edge[2].equals("C")){ //bridge
                graph.addEdgedata(Integer.parseInt(edge[0]),Integer.parseInt(edge[1]),(Integer.parseInt(edge[3])*(-1)),i);
            }

        }
        graph.BellmanFord();

    }
}