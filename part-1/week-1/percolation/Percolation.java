/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int TOP = 0;
    private int BOTTOM;
    private int openCount = 0;
    private int size;
    private boolean[][] openSites;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 2);
        BOTTOM = n * n + 1;
        openSites = new boolean[n][n];
    }

    public void open(int row, int col) {
        checkException(row, col);
        openSites[row - 1][col - 1] = true;
        openCount++;

        // If it's the first row connect with the top virtual node
        if (row == 1) {
            uf.union((row - 1) * size + col, TOP);
            uf2.union((row - 1) * size + col, TOP);
        }
        // If row is bottom connect virtual bottom node
        if (row == size) {
            uf.union((row - 1) * size + col, BOTTOM);
        }

        // connect top node
        if (row - 1 >= 1 && isOpen(row - 1, col)) {
            uf.union((row - 1) * size + col, (row - 2) * size + col);
            uf2.union((row - 1) * size + col, (row - 2) * size + col);
        }
        // connect bottom
        if (row < size && isOpen(row + 1, col)) {
            uf.union((row - 1) * size + col, row * size + col);
            uf2.union((row - 1) * size + col, row * size + col);
        }
        // connect left
        if (col - 1 >= 1 && isOpen(row, col - 1)) {
            uf.union((row - 1) * size + col, (row - 1) * size + (col - 1));
            uf2.union((row - 1) * size + col, (row - 1) * size + (col - 1));
        }
        // connect right
        if (col < size && isOpen(row, col + 1)) {
            uf.union((row - 1) * size + col, (row - 1) * size + (col + 1));
            uf2.union((row - 1) * size + col, (row - 1) * size + (col + 1));
        }
    }

    public boolean isFull(int row, int col) {
        if ((row > 0 && row <= size) && (col > 0 && col <= size)) {
            return uf2.find(TOP) == uf2.find((row - 1) * size + col);
        }
        else throw new IllegalArgumentException();
    }

    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return openSites[row - 1][col - 1];
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return uf.find(TOP) == uf.find(BOTTOM);
    }

    private void checkException(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
    }
}
