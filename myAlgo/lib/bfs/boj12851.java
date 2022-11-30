package com.ssafy.lib.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.*;

public class boj12851 {

    static int N, K;
    static int min = 100001;
    static int cnt = 0;
    static boolean[] visit = new boolean[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(min);
        System.out.println(cnt);

    }

    private static void bfs() {

        Queue<Node> pq = new LinkedList<>();
        pq.offer(new Node(N, 0));


        while (!pq.isEmpty()) {

            Node cur = pq.poll();
            int num = cur.num;
            visit[num] = true;
            if (num == K) {
                if (cur.time < min) {
                    min = cur.time;
                    cnt = 1;
                } else if (cur.time == min) {
                    cnt++;
                }
                continue;
            }

            if (num * 2 <= 100000 && !visit[num * 2]) {
                pq.offer(new Node(num * 2, cur.time + 1));
            }

            if (num + 1 <= 100000 && !visit[num + 1]) {
                pq.offer(new Node(num + 1, cur.time + 1));
            }

            if (num - 1 >= 0 && !visit[num - 1]) {
                pq.offer(new Node(num - 1, cur.time + 1));
            }


        }

    }

    static class Node {
        int num;
        int time;

        Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}


