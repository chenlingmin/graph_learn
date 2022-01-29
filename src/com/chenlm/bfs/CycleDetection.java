package com.chenlm.bfs;

import com.chenlm.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle;

    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
    }

    private boolean bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            int vparent = queue.remove();
            for (int w : G.adj(v))
                if (!visited[w]) {
                    queue.add(w);
                    queue.add(v);
                    visited[w] = true;
                } else if (w != vparent) {
                    return true;
                }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }

}
