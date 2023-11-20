/* *****************************************************************************
 *  Name: Edge Weighted Graph
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedGraph {
    private int V;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int c) {
        this.V = c;
        adj = (Bag<Edge>[]) new Bag[c];
        for (int i = 0; i < c; i++) {
            adj[i] = new Bag<Edge>();
        }
    }

    // Add edge to both vertices
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }

    // Return an iterable list for 'v' vertex
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public Iterable<Edge> edges() {
        // Create a new bag to store all individual edge
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;

            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
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
