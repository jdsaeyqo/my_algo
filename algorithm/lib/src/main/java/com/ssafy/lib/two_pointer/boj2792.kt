package com.ssafy.lib.two_pointer

import java.util.StringTokenizer

class boj2792 {

    var N = 0L
    var M = 0
    lateinit var arr: LongArray
    fun sol() {

        val st = StringTokenizer(readLine())
        N = st.nextToken().toLong()
        M = st.nextToken().toInt()
        arr = LongArray(M)

        var max = -1L
        repeat(M) { i ->
            val input = readLine()!!
            arr[i] = input.toLong()
            max = arr[i].coerceAtLeast(max)
        }

        var s = 1L
        var e = max
        var mid = 0L
        var res = 0L
        while (s <= e) {
            mid = (s + e) / 2

            var people = 0L

            for (num in arr) {
                people += num / mid
                if (num % mid != 0L) {
                    people++
                }
            }

            if (people > N) {
                s = mid + 1
            } else {
                res = mid
                e = mid - 1
            }
        }

        println(res)

    }
}

fun main() {
    boj2792().sol()
}