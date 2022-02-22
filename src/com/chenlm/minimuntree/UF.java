package com.chenlm.minimuntree;

public class UF {
    private int[] parent;
    private int[] sz;

    public UF(int n) {
        parent = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = i;
        }
    }

    public int find(int p) {
        if (p != parent[p])
            parent[p] = find(parent[p]);
        return parent[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (sz[pRoot] > sz[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            sz[qRoot] += 1;
        }
    }
}
