package com.ssafy.lib.two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj14465 {
    static int N, K, B;
    static int[] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new int[N];

        Arrays.fill(arr, 1);

        for (int i = 0; i < B; i++) {
            int index = Integer.parseInt(br.readLine()) - 1;
            arr[index] = 0;
        }

        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }

        int res = K - sum;

        for (int i = 0; i < N - K; i++) {
            sum -= arr[i];
            sum += arr[i + K];

            res = Math.min(res, K - sum);
        }

        System.out.println(res);
    }
}
