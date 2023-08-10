import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionPC {
    private int[] ids;
    private int[] sizes;
    private int[] max;

    public WeightedQuickUnionPC(int n) {
        ids = new int[n];
        sizes = new int[n];
        max = new int[n];

        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1;
            max[i] = i;
        }
    }

    private int root(int i) {
        while (ids[i] != i) {
            ids[i] = ids[ids[i]];
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
            max[j] = Math.max(i, j);
        }
        else {
            ids[j] = i;
            sizes[i] += sizes[j];
            max[i] = Math.max(i, j);
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int find(int i) {
        int root = root(i);
        return max[root];
    }

    public static void main(String[] args) {
        QuickFind nodes = new QuickFind(10);

        nodes.union(5, 6);
        nodes.union(3, 6);
        nodes.union(7, 3);
        StdOut.println("5 and 7 ✅" + nodes.connected(5, 7));
        StdOut.println("0 and 7 ❌" + nodes.connected(0, 7));

        nodes.union(1, 2);
        nodes.union(0, 9);
        StdOut.println("9 and 1 ❌" + nodes.connected(9, 1));
        nodes.union(0, 1);
        StdOut.println("9 and 1 ✅" + nodes.connected(9, 1));
    }
}
