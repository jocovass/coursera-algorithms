/* *****************************************************************************
 * Prim's algorithm: (lazy implementation)
 * Time: E log E in the worst case
 *  - delete min: frequency E, time per op: log E
 *  - insert: freq. E, tpo: log E
 *
 *
 * Eager implementation:
 * - maintain a PW of vertices (pq has at most one entry per vertex) connected by
 * an edge to T, where priority of vertex v = weight of shortest edge connecting
 * v to T.
 * - delete min vertex v and add its associated edge e to T.
 * - update PQ by considering all edges e incident to v, ignore if x is already
 * in T, add x to PQ if not already on it, decrease priority of x if v-x becomes
 * shortest edge connecting x to T.
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimsAlgorithm {
    private boolean[] marked; // MST vertices
    private Queue<Edge> mst; // MST edges
    private MinPQ<Edge> pq; // PQ of edges

    public PrimsAlgorithm(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        mst = new Queue<Edge>();
        marked = new boolean[G.V()];

        visit(G, 0);

        // MST size has to be total number of vertices-1 otherwise it will
        // be cyclic
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            // Grab the next edge with the smallest wight
            // Check if the edge already connected
            // If not connect and add the edge to the mst
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            // Ignore if both endpoints in T add edge e to tree
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    // public void decreaseKey(int i, Key key) {
    //     validateIndex(i);
    //     if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
    //     if (keys[i].compareTo(key) == 0)
    //         throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
    //     if (keys[i].compareTo(key) < 0)
    //         throw new IllegalArgumentException("Calling decreaseKey() with a key that is strictly greater than the key in the priority queue");
    //     keys[i] = key;
    //     sink(qp[i]);
    // }

    private void visit(EdgeWeightedGraph G, int v) {
        // add v to T
        marked[v] = true;
        // for each edge E = v-w, add to PQ if w not already in T
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) { // Lazy approach
                pq.insert(e);
            }
        }
    }


    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {

    }
}
