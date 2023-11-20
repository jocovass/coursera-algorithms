/* *****************************************************************************
 *  Name: Edge Weighted Directed Graph
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedDigraph {
    private int V;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int c) {
        this.V = c;
        this.adj = (Bag<DirectedEdge>[]) new Bag[c];
        for (int i = 0; i < c; i++) {
            adj[i] = new Bag<DirectedEdge>();
        }
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
    }

    public int V() {
        return V;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<>();

        for (int i = 0; i < this.V; i++) {
            int selfLoops = 0;
            for (DirectedEdge e : adj[i]) {
                if (e.to() > i) {
                    list.add(e);
                }
                else if (e.to() == i) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {

    }
}
