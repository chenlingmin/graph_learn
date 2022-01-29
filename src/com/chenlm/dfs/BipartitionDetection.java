package com.chenlm.dfs;

import com.chenlm.Graph;

// 二分图检测
public class BipartitionDetection {

    private Graph G;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G) {

        this.G = G;
        colors = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (colors[v] == 0)
                if (!dfs(v, -1)) {
                    isBipartite = false;
                    break;
                }
        }
    }

    private boolean dfs(int v, int color) {
        colors[v] = color;
        for (Integer w : G.adj(v)) {
            if (colors[w] == 0) {
                if (!dfs(w, -color)) {
                    return false;
                }
            } else if (colors[w] == color) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite());

        Graph g2 = new Graph("g2.txt");
        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite());
    }
}
