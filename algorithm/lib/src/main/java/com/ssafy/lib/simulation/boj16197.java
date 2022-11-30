package com.ssafy.lib.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj16197 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
//    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<int[]> coinsList = new ArrayList<>();
        map = new int[N + 2][M + 2];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = input.charAt(j);
                switch (ch) {
                    case '.':
                        map[i + 1][j + 1] = 0;
                        break;
                    case '#':
                        map[i + 1][j + 1] = 1;
                        break;
                    case 'o':
                        map[i + 1][j + 1] = 2;
//                        q.offer(new int[]{i,j});
                        coinsList.add(new int[]{i+1, j+1});
                        break;
                }

            }
        }


        int[] coin1 = coinsList.get(0);
        int[] coin2 = coinsList.get(1);

        dfs(0, coin1, coin2);
        System.out.println(min);

    }

    private static void dfs(int cnt, int[] coin1, int[] coin2) {

        if (cnt > min) {
            return;
        }
        if (check(coin1, coin2)) {
            if (cnt < min) {
                min = cnt;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int coin1nx = coin1[0] + dx[i];
            int coin1ny = coin1[1] + dy[i];
            int coin2nx = coin2[0] + dx[i];
            int coin2ny = coin2[1] + dy[i];

            if (coin1nx >= 0 && coin1nx < N + 2 && coin1ny >= 0 && coin1ny < M + 2 && coin2nx >= 0 && coin2nx < N + 2 && coin2ny >= 0 && coin2ny < M + 2){
                if (map[coin1nx][coin1ny] != 1 && map[coin2nx][coin2ny] != 1) {
                    coin1 = new int[]{coin1nx, coin1ny};
                    coin2 = new int[]{coin2nx, coin2ny};
                    dfs(cnt + 1, coin1, coin2);
                }
                if(map[coin1nx][coin1ny] == 1 && map[coin2nx][coin2ny] == 1){
                    break;
                }

                if(i == 0){
                        if(coin1nx == coin2nx && Math.abs(coin1ny - coin2ny) == 1){
                            break;
                        }else{
                            if(map[coin1nx][coin1ny] == 1){
                                coin2 = new int[]{coin2nx, coin2ny};
                                dfs(cnt + 1, coin1, coin2);
                            }else{
                                coin1 = new int[]{coin1nx, coin1ny};
                                dfs(cnt + 1, coin1, coin2);
                            }
                        }

                }else if(i == 1){
                    if(coin1ny == coin2ny && Math.abs(coin1nx - coin2nx) == 1){
                        break;
                    }else{
                        if(map[coin1nx][coin1ny] == 1){
                            coin2 = new int[]{coin2nx, coin2ny};
                            dfs(cnt + 1, coin1, coin2);
                        }else{
                            coin1 = new int[]{coin1nx, coin1ny};
                            dfs(cnt + 1, coin1, coin2);
                        }
                    }
                }else if(i==2){
                    if(coin1nx == coin2nx && Math.abs(coin1ny - coin2ny) == 1){
                        break;
                    }else{
                        if(map[coin1nx][coin1ny] == 1){
                            coin2 = new int[]{coin2nx, coin2ny};
                            dfs(cnt + 1, coin1, coin2);
                        }else{
                            coin1 = new int[]{coin1nx, coin1ny};
                            dfs(cnt + 1, coin1, coin2);
                        }
                    }
                }else{
                    if(coin1ny == coin2ny && Math.abs(coin1nx - coin2nx) == 1){
                        break;
                    }else{
                        if(map[coin1nx][coin1ny] == 1){
                            coin2 = new int[]{coin2nx, coin2ny};
                            dfs(cnt + 1, coin1, coin2);
                        }else{
                            coin1 = new int[]{coin1nx, coin1ny};
                            dfs(cnt + 1, coin1, coin2);
                        }
                    }
                }
            }



        }

    }

    private static boolean check(int[] coin1, int[] coin2) {

        if (isOut(coin1) && !isOut(coin2)) {
            return true;
        }

        if (isOut(coin2) && !isOut(coin1)) {
            return true;
        }

        return false;
    }

    private static boolean isOut(int[] coin) {

        int x = coin[0];
        int y = coin[1];

        if (x < 1 || x > N || y < 1 || y > M) {
            return true;
        }

        return false;

    }
}
