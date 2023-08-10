public class EarliestTimeAllConnected {
    public static int main(int n, int[][] log) {
        int distinct = n;
        WeightedQuickUnionPC uf = new WeightedQuickUnionPC(n);

        for (int i = 0; i < n; i++) {
            int member1 = log[i][0];
            int member2 = log[i][1];
            int timestamp = log[i][0];

            if (!uf.connected(member1, member2)) {
                uf.union(member1, member2);
                distinct -= 1;
            }

            if (distinct == 1) {
                return timestamp;
            }
        }

        return -1; // Not all members are connected
    }
}
