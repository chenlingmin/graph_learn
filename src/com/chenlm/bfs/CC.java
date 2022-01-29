package com.chenlm.bfs;

import com.chenlm.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CC {

    private Graph G;
    private int[] visited;
    private int cccount;

    private ArrayList<Integer> order = new ArrayList<>();

    public CC(Graph G) {
        this.G = G;
        visited = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            visited[i] = -1;
        }

        for (int v = 0; v < G.V(); v++)
            if (visited[v] == -1) {
                bfs(v);
                cccount++;
            }

    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = cccount;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);
            for (int w : G.adj(v))
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = cccount;
                }
        }
    }

    public int cccount() {
//        for (int e : visited)
//            System.out.print(e + " ");
//        System.out.println();
        return cccount;
    }

    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        com.chenlm.dfs.CC cc = new com.chenlm.dfs.CC(g);
        System.out.println(cc.cccount());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(0, 5));

        ArrayList<Integer>[] comp = cc.components();

        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + " : ");
            for (Integer w : comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }

}
