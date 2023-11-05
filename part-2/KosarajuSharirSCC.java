/* *****************************************************************************
 * Kosaraju Sharir algorithm: algorithm computes the strong components of digraph
 * in time proportional to E+V.
 *  - run DFS on G^R to compute reverse postorder
 *  - run DFS on G, considering vertices in order given by first DFS
 **************************************************************************** */

public class KosarajuSharirSCC {
    private int count = 0;
    private boolean[] visited;
    private int[] ids;

    public KosarajuSharirSCC(DGraph G) {
        visited = new boolean[G.V()];
        ids = new int[G.V()];
        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
        for (int v : dfs.reversePost()) {
            if (!visited[v])
                dfs(G, v);
            count++;
        }
    }

    private void dfs(DGraph G, int v) {
        visited[v] = true;
        ids[v] = count;
        for (int w : G.adj(v)) {
            if (!visited[w])
                dfs(G, w);
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return ids[v] == ids[w];
    }

    public static void main(String[] args) {

    }
}
