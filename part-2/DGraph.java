/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;

public class DGraph {
    private Bag<Integer> adj[];
    private int V;

    public DGraph(int capacity) {
        V = capacity;
        adj = (Bag<Integer>[]) new Bag[capacity];
        for (int v = 0; v < capacity; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // Add a directed edge from v to w
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public int V() {
        return adj.length;
    }

    public int E() {
        int count = 0;
        for (int i = 0; i < adj.length; i++) {
            count += adj[i].size();
        }
        return count;
    }

    public DGraph reverse() {
        DGraph reverse = new DGraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public static void main(String[] args) {

    }
}
