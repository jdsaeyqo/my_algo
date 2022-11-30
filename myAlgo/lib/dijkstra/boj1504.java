package com.ssafy.lib.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj1504 {
    static int N, E, V1, V2;
    static ArrayList<ArrayList<Node>> map;
    static int[] dist;
    static boolean[] visited;
    static final int INF = 200000000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();

        dist = new int[N + 1];

        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<Node>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            map.get(start).add(new Node(end, weight));
            map.get(end).add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        // 1 -> V1 -> V2 -> N
        int firstCase = dijkstra(1, V1) + dijkstra(V1, V2) + dijkstra(V2, N);

        // 1 -> V2 -> V1 -> N
        int secondCase = dijkstra(1, V2) + dijkstra(V2, V1) + dijkstra(V1, N);

        int ans = 0;
        if (firstCase >= INF && secondCase >= INF) {
            ans = -1;
        } else {
            ans = Math.min(firstCase, secondCase);
        }

        System.out.println(ans);
    }

    static int dijkstra(int start, int end) {
        Arrays.fill(dist, INF);
        visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int to = curNode.to;

            if (!visited[to]) {
                visited[to] = true;

                for (Node node : map.get(to)) {
                    if (!visited[node.to] && dist[node.to] > dist[to] + node.weight) {
                        dist[node.to] = dist[to] + node.weight;
                        pq.offer(new Node(node.to, dist[node.to]));
                    }
                }

            }
        }
        return dist[end];

    }


    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
