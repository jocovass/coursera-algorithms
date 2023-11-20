/* *****************************************************************************
 *  Name: Minimum spanning tree
 *  Def: A spanning tree of G is a subgraph 'T' that is both a tree (connected
 *  and acyclic) and spanning (includes all of the vertices).
 *
 *  Cut: a 'cut' in a graph is a partition of its vertices into two (nonempty) sets.
 *  A crossing edge connects a vertex in one set with a vertex in the other.
 *
 *  Correctness: find cut with no black crossing edges, color its min-weight edge black,
 *  repeat until V - 1 edges are colored black.
 *
 ******************************************************************************
 * Kruskal's algorithm:
 * Time: E log E in the worst case
 *  - build pq: frequency 1, time per op: E log E
 *  - delete min: freq. E, tpo: log E
 *  - union: freq. V, tpo log V
 *  - connected: freq. E,  tpo log V
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalsAlgorithm {
    private Queue<Edge> mst = new Queue<Edge>();

    public KruskalsAlgorithm(EdgeWeightedGraph G) {
        // We want to build a min priority queue from the weighted graph passed in
        // to the constructor because we always want to add the edge that has
        // the smallest weight (distance)
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        // Use union find to track the connected sets
        UF uf = new UF(G.V());
        // MST size has to be total number of vertices minus 1 otherwise it will
        // be cyclic
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            // Grab the next edge with the smallest wight
            // Check if the edge already connected
            // If not connect and add the edge to the mst
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            // This check ensures that we won't create a cycle
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {

    }
}
