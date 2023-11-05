/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BFSGraph {
    private boolean[] visited;
    private int[] edgeTo;
    private int[] distTo;
    private final int s;

    public BFSGraph(GGraph G, int v) {
        this.s = v;
        visited = new boolean[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, v);
    }

    private void bfs(GGraph G, int v) {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(v);
        visited[v] = true;
        while (!q.isEmpty()) {
            int x = q.dequeue();

            for (int w : G.adj(x)) {
                if (!visited[w]) {
                    q.enqueue(w);
                    visited[w] = true;
                    edgeTo[w] = x;
                    distTo[w] = distTo[x] + 1;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return visited[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();

        for (int w = v; w != s; w = edgeTo[w]) {
            path.push(w);
        }

        path.push(s);

        return path;
    }

    // Returns the distance(shortest) from 's'
    public int distToPath(int v) {
        return distTo[v];
    }

    public static void main(String[] args) {

    }
}
