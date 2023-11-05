import edu.princeton.cs.algs4.Stack;

public class DFSGraph {
    // We use this array to track from which vertex we came to the
    // i-th vertex
    private int[] edgeTo;
    // We set every touched vertex to true, it gives us constant time to check
    // if a vertex is connected to 's'
    private boolean[] visited;
    // The vertex from where we started the DFS
    private final int s;

    public DFSGraph(GGraph G, int v) {
        this.s = v;
        edgeTo = new int[G.V()];
        visited = new boolean[G.V()];
        dfs(G, v);
    }

    // Do the depth first search
    private void dfs(GGraph G, int v) {
        // Mark each vertex to be visited
        visited[v] = true;
        // Loop over all the adjacent vertices of vertex "v"
        for (int w : G.adj(v)) {
            // Only continue if the adjacent vertex wasn't visited before
            if (!visited[w]) {
                // Recursively check adjacent vertices until there are
                // any unvisited and save the vertex we came from
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }

    // Check if vertex "v" has path to "s"
    public boolean hasPathTo(int v) {
        return visited[v];
    }

    // Returns an iterable path from "v" to "s" if there is one
    // Otherwise null
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {

    }
}
