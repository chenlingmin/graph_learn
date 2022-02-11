package com.chenlm.hamilton;


import java.util.ArrayList;
import java.util.Collections;

public class HamiltonPath {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;
    private int s;

    public HamiltonPath(Graph G, int s) {
        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        this.s = s;
        end = -1;
        dfs(s, s, G.V());
    }

    private boolean dfs(int v, int parent, int left) {
        visited[v] = true;
        pre[v] = parent;
        left--;

        if (left == 0 ) {
            end = v;
            return true;
        }

        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left)) return true;
            }
        }
        visited[v] = false;
        return false;
    }

//    private boolean allVisited() {
//        for (int v = 0; v < G.V(); v++) {
//            if (!visited[v]) return false;
//        }
//        return true;
//    }

    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1) return res;

        int cur = end;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("resources/hamilton/g3.txt");
        HamiltonPath hamiltonLoop = new HamiltonPath(g, 1);
        System.out.println(hamiltonLoop.result());

        Graph g1 = new Graph("resources/hamilton/g3.txt");
        HamiltonPath hamiltonLoop1 = new HamiltonPath(g1, 2);
        System.out.println(hamiltonLoop1.result());

        Graph g2 = new Graph("resources/hamilton/g3.txt");
        HamiltonPath hamiltonLoop2 = new HamiltonPath(g2, 0);
        System.out.println(hamiltonLoop2.result());
    }
}
