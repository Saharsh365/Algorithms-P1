/* *****************************************************************************
 *  Name:              Saharsh Maloo
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int VIRTUAL_TOP = 0;
    private int virtualBottom;
    private WeightedQuickUnionUF uf;
    private boolean[] siteOpen;
    private int n;
    private int numOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);

        virtualBottom = n * n + 1;
        for (int i = 1; i <= n; i++) {
            uf.union(VIRTUAL_TOP, i);
        }
        for (int i = n * (n - 1) + 1; i <= n * n; i++) {
            uf.union(i, virtualBottom);
        }

        siteOpen = new boolean[n * n + 1];
        this.n = n;
    }

    private void throwErr(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    private int mapIndice(int row, int col) {
        row--;
        return row * n + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        throwErr(row, col);

        int index = mapIndice(row, col);

        if (siteOpen[index]) return;

        siteOpen[index] = true;
        numOpenSites++;
        if (row > 1 && isOpen(row - 1, col)) uf.union(index, mapIndice(row - 1, col));
        if (row < n && isOpen(row + 1, col)) uf.union(index, mapIndice(row + 1, col));
        if (col > 1 && isOpen(row, col - 1)) uf.union(index, mapIndice(row, col - 1));
        if (col < n && isOpen(row, col + 1)) uf.union(index, mapIndice(row, col + 1));
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        throwErr(row, col);
        return siteOpen[mapIndice(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        throwErr(row, col);

        if (!isOpen(row, col)) return false;

        return uf.find(VIRTUAL_TOP) == uf.find(mapIndice(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(VIRTUAL_TOP) == uf.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        
    }
}
