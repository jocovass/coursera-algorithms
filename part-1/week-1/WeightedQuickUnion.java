import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnion {
    private int[] ids;
    private int[] sizes;

    public WeightedQuickUnion(int n) {
        ids = new int[n];
        sizes = new int[n];

        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }
    }

    private int root(int i) {
        while (ids[i] != i) {
            i = ids[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;

        if (sizes[i] < sizes[j]) {
            ids[i] = j;
            sizes[j] += sizes[i];
        }
        else {
            ids[j] = i;
            sizes[i] += sizes[j];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        QuickFind nodes = new QuickFind(10);

        nodes.union(5, 6);
        nodes.union(3, 6);
        nodes.union(7, 3);
        StdOut.println("5 and 7 ✅" + nodes.connected(5, 7));
        StdOut.println("0 and 7 ❌" + nodes.connected(0, 7));
    }
}
