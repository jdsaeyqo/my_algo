package com.ssafy.lib.two_pointer;

class pg43238 {

    public long solution(int n, int[] times) {
        long answer = 0;

        long start = 0;
        long end = Long.MAX_VALUE;
        long mid = 0;
        long result = Long.MAX_VALUE;


        while (start <= end) {
            mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];


            }

            if (sum < n) {
                start = mid + 1;
            } else {
                result = Math.min(result, mid);
                end = mid - 1;
            }

        }

        return result;
    }
}