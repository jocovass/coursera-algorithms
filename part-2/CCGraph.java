/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class CCGraph {
    private int count = 0;
    private int[] id;
    private boolean[] visited;

    public CCGraph(GGraph G) {
        id = new int[G.V()];
        visited = new boolean[G.V()];

        for (int w = 0; w < G.V(); w++) {
            if (!visited[w]) {
                dfs(G, w);
                count++;
            }
        }
    }

    private void dfs(GGraph G, int s) {
        visited[s] = true;
        id[w] = count;
        for (int w : G.adj(s)) {
            if (!visited[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    // Returns the number of connected components
    public int count() {
        return count;
    }

    // Return the id of the connected component 'v' belongs to
    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {

    }
}
