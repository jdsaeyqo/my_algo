package com.ssafy.lib.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17070 {
    static int N;
    static int[][] map;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static int cnt = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(map[N][N] == 1){
            System.out.println(0);
            return;
        }
        bfs();
        System.out.println(cnt);
    }

    static void bfs() {

        Queue<Pipe> q = new LinkedList<>();

        q.add(new Pipe(1,2,0));

        while (!q.isEmpty()) {
            Pipe cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            if (r == N && c == N) {
                cnt++;
                continue;
            }
            switch (cur.dir) {
                case 0:

                    for (int i = 0; i < 3; i+=2) {
                        int nx = r + dx[i];
                        int ny = c + dy[i];

                        if (i == 0) {
                            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                                if (map[nx][ny] != 1) {
                                    q.add(new Pipe(nx,ny,0));
                                }
                            }
                        } else {
                            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                                if (map[r + dx[0]][c + dy[0]] != 1 && map[r + dx[1]][c + dy[1]] != 1) {
                                    if (map[nx][ny] != 1) {
                                        q.add(new Pipe(nx,ny,2));
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 1:
                    int x2 = r;
                    int y2 = c;
                    for (int i = 1; i <= 2; i++) {
                        int nx = x2 + dx[i];
                        int ny = y2 + dy[i];

                        if (i == 1) {
                            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                                if (map[nx][ny] != 1) {
                                    q.add(new Pipe(nx,ny,1));
                                }
                            }
                        } else {
                            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                                if (map[x2 + dx[0]][y2 + dy[0]] != 1 && map[x2 + dx[1]][y2 + dy[1]] != 1) {
                                    if (map[nx][ny] != 1) {
                                        q.add(new Pipe(nx,ny,2));
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    int x3 = r;
                    int y3 = c;
                    int chk3 = 0;
                    for (int i = 0; i < 3; i++) {
                        int nx = x3 + dx[i];
                        int ny = y3 + dy[i];

                        if (i == 0) {
                            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                                if (map[nx][ny] != 1) {
                                    chk3++;
                                    q.add(new Pipe(nx,ny,0));
                                }
                            }
                        } else if (i == 1) {
                            if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                                if (map[nx][ny] != 1) {
                                    chk3++;
                                    q.add(new Pipe(nx,ny,1));
                                }
                            }
                        } else {
                            if (chk3 == 2) {
                                if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                                    if (map[nx][ny] != 1) {
                                        q.add(new Pipe(nx,ny,2));
                                    }

                                }
                            }

                        }
                    }
                    break;
            }
        }

    }



}



class Pipe {
    int r, c;
    int dir;

    Pipe(int r, int c, int dir) {
        this.r = r;
        this.c = c;
        this.dir = dir;
    }


}
