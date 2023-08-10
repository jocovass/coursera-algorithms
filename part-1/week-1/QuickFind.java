/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class QuickFind {
    private int[] ids;

    public QuickFind(int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public void union(int p, int q) {
        int pid = ids[p];
        int qid = ids[q];

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == pid) {
                ids[i] = qid;
            }
        }
    }

    public boolean connected(int p, int q) {
        return ids[p] == ids[q];
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
