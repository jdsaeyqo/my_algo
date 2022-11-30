package com.ssafy.lib.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1753 {
    static int V, E;
    static int start;

    static int[] dist;
    static boolean[] visit;
    static ArrayList<ArrayList<Node>> nodeList;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        start = Integer.parseInt(br.readLine());
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visit = new boolean[V + 1];
        nodeList = new ArrayList<>();


        for (int i = 0; i < V + 1; i++) {
            nodeList.add(new ArrayList<Node>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            nodeList.get(from).add(new Node(to, time));
        }

        dijkstra(start);

        for (int i = 1; i <= V; i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }

    }

    private static void dijkstra(int start) {
        dist[start] = 0;
        Node node = new Node(start, 0);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(node);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int to = cur.to;
            int time = cur.time;

            if (visit[to]) {
                continue;
            }

            visit[to] = true;
            ArrayList<Node> nextList = nodeList.get(to);

            for (int i = 0; i < nextList.size(); i++) {
                Node tmp = nextList.get(i);
                if (dist[tmp.to] > dist[to] + tmp.time) {
                    dist[tmp.to] = dist[to] + tmp.time;
                    pq.add(new Node(tmp.to, dist[tmp.to]));
                }
            }

        }
    }
}

class Node implements Comparable<Node> {
    int to;
    int time;

    Node(int to, int time) {
        this.to = to;
        this.time = time;
    }

    @Override
    public int compareTo(Node n) {
        return this.time - n.time;
    }


}
