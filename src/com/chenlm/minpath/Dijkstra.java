package com.chenlm.minpath;

import java.util.*;

public class Dijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;
    private int[] pre;

    private class Node implements Comparable<Node> {
        public int v, dis;

        Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node other) {
            return dis - other.dis;
        }
    }

    public Dijkstra(WeightedGraph G, int s) {
        this.G = G;

        G.validateVertex(s);
        this.s = s;

        this.dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);

        this.pre = new int[G.V()];
        Arrays.fill(pre, -1);


        dis[s] = 0;
        pre[s] = s;

        visited = new boolean[G.V()];

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            int cur = pq.remove().v;
            if (visited[cur]) continue;

            visited[cur] = true;

            for (int w : G.adj(cur)) {
                if (!visited[w]) {
                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, dis[w]));
                        pre[w] = cur;
                    }
//                    dis[w] = Math.min(dis[w], dis[cur] + G.getWeight(cur, w));
//                    pq.add(new Node(w, dis[w]));
                }
            }
        }
    }

    public boolean isConnectedTo(int v) {
        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v) {
        G.validateVertex(v);
        return dis[v];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) return res;

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

        WeightedGraph g = new WeightedGraph("resources/minpath/g.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        for (int v = 0; v < g.V(); v++)
            System.out.print(dijkstra.distTo(v) + " ");
        System.out.println();

        System.out.println(dijkstra.path(3));


    }


}
