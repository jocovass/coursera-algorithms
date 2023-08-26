class RegionBySlashes {
    private int n;

    public int getRegionIndex(int r, int c, int i) {
        // 4 * because we split each grid cell into 4 region N,E,S,W
        // r - row
        // c - col
        // i - index of the four cell region 0 to 3
        return 4 * (r * n + c) + i;
    }

    public int regionsBySlashes(String[] grid) {
        n = grid.length;
        Dsu dsu = new Dsu(4 * n * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String c = String.valueOf(grid[i].toCharArray()[j]);
                if (c.equals("/") || c.equals(" ")) {
                    dsu.union(getRegionIndex(i, j, 0), getRegionIndex(i, j, 1));
                    dsu.union(getRegionIndex(i, j, 2), getRegionIndex(i, j, 3));
                }

                if (c.equals("\\") || c.equals(" ")) {
                    dsu.union(getRegionIndex(i, j, 0), getRegionIndex(i, j, 2));
                    dsu.union(getRegionIndex(i, j, 1), getRegionIndex(i, j, 3));
                }

                // check right neighbour
                if (j + 1 < n) {
                    dsu.union(getRegionIndex(i, j, 2), getRegionIndex(i, j + 1, 1));
                }
                // check left neighbour
                if (j - 1 >= 0) {
                    dsu.union(getRegionIndex(i, j, 1), getRegionIndex(i, j - 1, 2));
                }
                // check left up
                if (i - 1 >= 0) {
                    dsu.union(getRegionIndex(i, j, 0), getRegionIndex(i - 1, j, 3));
                }
                // check left down
                if (i + 1 < n) {
                    dsu.union(getRegionIndex(i, j, 3), getRegionIndex(i + 1, j, 0));
                }
            }
        }

        return dsu.count();
    }

    private class Dsu {
        // Single dimension array representing the grid
        private int[] parent;
        // Size of connected objects at parent[i] position
        private int[] size;
        // Number of connected objects
        private int count;

        public Dsu(int n) {
            parent = new int[n];
            size = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int iRoot = i;
            while (parent[iRoot] != iRoot) {
                iRoot = parent[iRoot];
                parent[iRoot] = parent[parent[iRoot]];
            }
            return iRoot;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot != qRoot) {
                count--;
            }

            if (pRoot < qRoot) {
                parent[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }
            else {
                parent[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }
        }

        public int count() {
            return count;
        }
    }
}
