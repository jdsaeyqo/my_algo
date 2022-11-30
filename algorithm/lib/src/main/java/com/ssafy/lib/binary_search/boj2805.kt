package com.ssafy.lib.binary_search

import java.util.StringTokenizer

class boj2805 {
    fun sol() {
        val (N, M) = readLine()!!.split(" ").map { it.toInt() }
        val heightArr = LongArray(N)
        val st = StringTokenizer(readLine())

        var max = -1L
        for (i in 0 until N) {
            heightArr[i] = st.nextToken().toLong()
            max = max.coerceAtLeast(heightArr[i])
        }

        var start = 0L
        var end = max
        var mid = 0L
        var res = 0L

        while (start <= end) {

            mid = (start + end) / 2

            var sum = 0L
            heightArr.forEach {
                if (it > mid) {
                    sum += it - mid
                }
            }

            if (sum < M) {
                end = mid - 1
            } else {
                res = Math.max(res, mid)
                start = mid + 1
            }
        }

        println(res)

    }
}

fun main() {

    boj2805().sol()
}