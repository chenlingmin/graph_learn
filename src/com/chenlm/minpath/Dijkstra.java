package com.chenlm.minpath;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;

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
        dis[s] = 0;

        visited = new boolean[G.V()];

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            int cur = pq.remove().v;
            if (visited[cur]) continue;

            visited[cur] = true;

            for (int w : G.adj(cur)) {
                if (!visited[w]) {
//                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
//                        dis[w] = dis[cur] + G.getWeight(cur, w);
//                        pq.add(new Node(w, dis[w]));
//                    }
                    dis[w] = Math.min(dis[w], dis[cur] + G.getWeight(cur, w));
                    pq.add(new Node(w, dis[w]));
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

    public static void main(String[] args) {

        WeightedGraph g = new WeightedGraph("resources/minpath/g.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        for (int v = 0; v < g.V(); v++)
            System.out.print(dijkstra.distTo(v) + " ");
        System.out.println();
    }


}
