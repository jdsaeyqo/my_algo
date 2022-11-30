package com.ssafy.lib.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17086 {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 1, 1, 0, -1, -1, -1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int cnt = 0;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        bfs();
        System.out.println(cnt-1);

    }

    static void bfs() {

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int[] cur = q.poll();
                int cx = cur[0];
                int cy = cur[1];


                for (int j = 0; j < 8; j++) {

                    int nx = cx + dx[j];
                    int ny = cy + dy[j];

                    if (!(nx >= 0 && nx < n && ny >= 0 && ny < m)) {
                        continue;
                    }

                    if (visited[nx][ny]) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});

                }

            }

            cnt++;
        }
    }
}