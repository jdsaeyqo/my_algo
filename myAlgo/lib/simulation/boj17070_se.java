package com.ssafy.lib.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17070_se {

    static int N, Cnt = 0;
    static int[][] Arr;

    public static class Point{
        int r, c;
        int status; // 0, 1, 2 -> 가로, 세로, 대각

        public Point(int r, int c, int status){
            this.r = r;
            this.c = c;
            this.status = status;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Arr = new int[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                Arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(Arr[N-1][N-1] == 1){
            System.out.println(0);
            return;
        }

        BFS();

        System.out.println(Cnt);
    }

    static void BFS(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 1, 0));

        while (!q.isEmpty()){
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            int dir = p.status;

            if(r == N-1 && c == N-1){ // n, n 도착
                Cnt++;
                continue;
            }

            if(dir == 0){ // 가로
                if(checkIdx(r, c+1)){
                    q.add(new Point(r, c+1, 0));
                }
                if(checkIdx(r+1, c+1) && checkCross(r+1, c+1)){
                    q.add(new Point(r+1, c+1, 2));
                }
            }
            else if(dir == 1){ // 세로
                if(checkIdx(r+1, c)){
                    q.add(new Point(r+1, c, 1));
                }
                if(checkIdx(r+1, c+1) && checkCross(r+1, c+1)){
                    q.add(new Point(r+1, c+1, 2));
                }
            }
            else if(dir == 2){ // 대각선
                if(checkIdx(r, c+1)){
                    q.add(new Point(r, c+1, 0));
                }
                if(checkIdx(r+1, c)){
                    q.add(new Point(r+1, c, 1));
                }
                if(checkIdx(r+1, c+1) && checkCross(r+1, c+1)){
                    q.add(new Point(r+1, c+1, 2));
                }
            }

        }
    }

    static boolean checkIdx(int r, int c){ // 인덱스 체크
        if(r < 0 || r >= N || c < 0 || c >= N || Arr[r][c] == 1){
            return false;
        }
        return true;
    }

    static boolean checkCross(int r, int c){ // 대각 체크
        if(Arr[r-1][c] == 1 || Arr[r][c-1] == 1){
            return false;
        }
        return true;
    }

}