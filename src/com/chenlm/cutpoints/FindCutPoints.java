package com.chenlm.cutpoints;


import java.util.HashSet;

public class FindCutPoints {

    private Graph G;
    private boolean[] visited;

    private int ord[];
    private int low[];
    private int cnt;

    private HashSet<Integer> res;

    public FindCutPoints(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];

        res = new HashSet<>();
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v, v);

    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;

        int child = 0;

        for (int w: G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                if (v != parent && low[w] >= ord[v]) {
                    res.add(v);
                }
                child++;
                if (v == parent && child > 1) {
                    res.add(v);
                }
            } else if (w != parent) { // 环
                low[v] = Math.min(low[v], low[w]);
            }
        }

    }

    public HashSet<Integer> result() {
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("resources/cutpoints/g.txt");
        FindCutPoints fb = new FindCutPoints(g);
        System.out.println("CutPoints in g : " + fb.result());

        Graph g2 = new Graph("resources/cutpoints/g2.txt");
        FindCutPoints fb2 = new FindCutPoints(g2);
        System.out.println("CutPoints in g2 : " + fb2.result());

        Graph g3 = new Graph("resources/cutpoints/g3.txt");
        FindCutPoints fb3 = new FindCutPoints(g3);
        System.out.println("CutPoints in g3 : " + fb3.result());

        Graph tree = new Graph("resources/cutpoints/tree.txt");
        FindCutPoints fb_tree = new FindCutPoints(tree);
        System.out.println("CutPoints in tree : " + fb_tree.result());
    }

}
