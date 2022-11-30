package com.ssafy.lib.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class boj3085 {
    static int n;
    static Character[][] map;
    static int max = -1;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new Character[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                Character pre = map[i][j];
                Character next = map[i][j + 1];

                map[i][j] = next;
                map[i][j + 1] = pre;
                check();
                map[i][j] = pre;
                map[i][j + 1] = next;

            }
        }

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n - 1 ; j++) {
                Character pre = map[j][i];
                Character next = map[j+1][i];

                map[j][i] = next;
                map[j+1][i] = pre;
                check();
                map[j][i] = pre;
                map[j+1][i] = next;

            }
        }

        System.out.println(max);

    }

    static void check() {

        int cnt = 0;
        Character pre = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!pre.equals(map[i][j])) {
                    pre = map[i][j];
                    max = Math.max(max, cnt);
                    cnt = 1;

                } else {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
            cnt = 0;
            pre = 'A';

        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!pre.equals(map[j][i])) {
                    pre = map[j][i];
                    max = Math.max(max, cnt);
                    cnt = 1;

                } else {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
            cnt = 0;
            pre = 'A';

        }
    }
}
