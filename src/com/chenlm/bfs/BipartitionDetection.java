package com.chenlm.bfs;

import com.chenlm.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class BipartitionDetection {

    private Graph G;
    private int[] colors;
    private boolean isBipartite = true;


    public BipartitionDetection(Graph G) {
        this.G = G;
        colors = new int[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (colors[v] == 0)
                if (!bfs(v)) {
                    isBipartite = false;
                    break;
                }
    }

    private boolean bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        colors[s] = -1;

        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v))
                if (colors[w] == 0) {
                    queue.add(w);
                    colors[w] = -colors[v];
                } else if (colors[w] == colors[v]) {
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
