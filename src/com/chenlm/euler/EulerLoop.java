package com.chenlm.euler;


import java.util.ArrayList;
import java.util.Stack;

public class EulerLoop {
    private Graph G;

    public EulerLoop(Graph G) {
        this.G = G;
    }

    public boolean hasEulerLoop() {
        CC cc = new CC(G);
        if (cc.count() > 1) return false;

        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) % 2 == 1) return false;
        }
        return true;
    }

    // Hierholzer 算法寻找欧拉回路
    public ArrayList<Integer> result() {

        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) return res;

        Graph g = (Graph) G.clone();

        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(curv);

        while (!stack.isEmpty()) {
            if (g.degree(curv) != 0) {
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new EulerLoop(new Graph("resources/euler/g.txt")).result());
        System.out.println(new EulerLoop(new Graph("resources/euler/g2.txt")).result());

    }
}
