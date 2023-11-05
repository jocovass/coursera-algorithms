import edu.princeton.cs.algs4.Bag;

public class GGraph {
    private final int V;
    private Bag<Integer>[] adj;

    // initialize the graph
    public GGraph(int c) {
        this.V = c;
        adj = (Bag<Integer>[]) new Bag[c];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    // save the vertices in for both
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // the bag implementation extends the 'Iterable' class, so it has to implement an iterator
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // returns the number or vertices
    public int V() {
        return V;
    }

    public static void main(String[] args) {

    }
}
