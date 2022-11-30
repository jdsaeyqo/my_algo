package com.ssafy.lib.two_pointer

import java.util.StringTokenizer
import kotlin.math.abs

class boj2470 {

    lateinit var arr: LongArray

    fun sol() {

        val N = readLine()!!.toInt()
        arr = LongArray(N)

        val st = StringTokenizer(readLine())
        repeat(N) { i ->
            arr[i] = st.nextToken().toLong()

        }

        arr.sort()

        var s = 0
        var e = arr.size - 1

        var left = arr[s]
        var right = arr[e]

        while (s < e) {

            if (abs(left + right) > abs(arr[s] + arr[e])) {
                left = arr[s]
                right = arr[e]
            }

            if (arr[s] + arr[e] > 0) {
                e--
            } else {
                s++
            }
        }

        println("$left $right")

    }
}

fun main() {
    boj2470().sol()
}