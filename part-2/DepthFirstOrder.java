/* *****************************************************************************
 * Topological sort: Redraw DAG(Directed acyclic graph) so all edges point upwards
 *  - Run depth first search
 *  - Return vertices in reverse postorder
 *
 * Note: A digraph has a topological order if and only if there is no directed cycle.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
    private boolean[] visited;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(DGraph G) {
        visited = new boolean[G.V()];
        reversePost = new Stack<Integer>();
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(G, v);
        }
    }

    private void dfs(DGraph G, int v) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w])
                dfs(G, w);
        }
        reversePost.push(v);
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {

    }
}
