package com.ssafy.lib.binary_search;

import java.util.*;

class pg43236 {
    public long solution(int distance, int[] rocks, int n) {

        long start = 1;
        long end = distance;
        long mid = 0;
        long res = -1;
        int[] nRocks = new int[rocks.length + 2];

        nRocks[0] = 0;
        nRocks[nRocks.length - 1] = distance;
        Arrays.sort(rocks);

        for (int i = 1; i <= nRocks.length - 2; i++) {
            nRocks[i] = rocks[i - 1];
        }


        while (start <= end) {
            mid = (start + end) / 2;
            int sum = 0;
            long pre = 0;

            for (int i = 1; i < nRocks.length; i++) {
                if (nRocks[i] - pre < mid) {
                    sum++;
                } else {
                    pre = nRocks[i];
                }
            }

            if (sum > n) {
                end = mid - 1;
            } else {
                start = mid + 1;
                res = Math.max(res, mid);
            }
        }
        return res;
    }

}
