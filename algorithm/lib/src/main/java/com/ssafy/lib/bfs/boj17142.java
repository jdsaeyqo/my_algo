package com.ssafy.lib.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17142 {

    static ArrayList<Virus> virusList = new ArrayList<>();
    static Virus[] activeVirus;
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int zeroCnt = 0;
    static int depth = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        activeVirus = new Virus[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new Virus(i, j));
                } else if (map[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }

        if (zeroCnt == 0) {
            System.out.println(zeroCnt);
            return;
        }

        comb(0, 0);
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    static void comb(int cur, int start) {
        if (cur == M) {
            spreadVirus();
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            activeVirus[cur] = virusList.get(i);
            comb(cur + 1, i + 1);
        }

    }

    private static void spreadVirus() {
        depth = 0;

        int tmpZeroCnt = zeroCnt;
        Queue<Virus> q = new LinkedList<>();
        visit = new boolean[N][N];

        for (int i = 0; i < activeVirus.length; i++) {
            visit[activeVirus[i].x][activeVirus[i].y] = true;
            q.offer(activeVirus[i]);
        }

        while (!q.isEmpty()) {
            int size = q.size();
            depth++;
            for (int i = 0; i < size; i++) {
                Virus v = q.poll();

                int cx = v.x;
                int cy = v.y;

                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];

                    if (!(nx >= 0 && nx < N && ny >= 0 && ny < N)) {
                        continue;
                    }

                    if (visit[nx][ny]) {
                        continue;
                    }

                    if (map[nx][ny] == 1) {
                        continue;
                    }

                    if (map[nx][ny] == 0) {
                        tmpZeroCnt--;
                    }

                    if (tmpZeroCnt == 0) {
                        if (depth < min) {
                            min = depth;
                        }
                        return;
                    }
                    q.offer(new Virus(nx, ny));
                    visit[nx][ny] = true;

                }
            }

        }

    }


    static class Virus {
        int x;
        int y;

        Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
