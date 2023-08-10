/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {
    private int[] ids;

    public QuickUnion(int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
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
        ids[i] = j;
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
