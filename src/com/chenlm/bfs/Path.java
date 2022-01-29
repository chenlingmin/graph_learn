package com.chenlm.bfs;

import com.chenlm.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Path {

    private Graph G;
    private int s;
    private int t;
    private boolean[] visited;
    private int[] pre;


    public Path(Graph G, int s, int t) {
        this.G = G;
        this.s = s;
        this.t = t;

        visited = new boolean[G.V()];
        pre = new int[G.V()];

        for (int i = 0; i < pre.length; i++)
            pre[i] = -1;

        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        pre[s] = s;
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
                if (w == t) return;
            }
        }
    }

    public boolean isConnectedTo() {
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo())
            return res;

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }


    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        com.chenlm.dfs.Path sspath = new com.chenlm.dfs.Path(g, 0, 6);
        System.out.println("0 -> 6 : " + sspath.path());

        com.chenlm.dfs.Path sspath2 = new com.chenlm.dfs.Path(g, 0, 1);
        System.out.println("0 -> 1 : " + sspath2.path());

        com.chenlm.dfs.Path sspath3 = new com.chenlm.dfs.Path(g, 0, 5);
        System.out.println("0 -> 5 : " + sspath3.path());
    }

}
