package com.ssafy.lib.binary_search

import java.util.StringTokenizer

class boj1637 {

    lateinit var arr: Array<IntArray>

    fun sol() {
        val N = readLine()!!.toInt()
        arr = Array(3) { IntArray(N) }

        var max = -1L
        var min = Long.MAX_VALUE
        for (i in 0 until N) {
            val st = StringTokenizer(readLine())
            arr[0][i] = st.nextToken().toInt()
            min = Math.min(arr[0][i].toLong(), min)
            arr[1][i] = st.nextToken().toInt()
            max = Math.max(arr[1][i].toLong(), max)
            arr[2][i] = st.nextToken().toInt()
        }

        var s: Long = min
        var e: Long = max

        var ans = 0L

        while (s <= e) {
            val mid = (s + e) / 2

            if (check(mid) % 2 == 0L) {
                s = mid + 1
            } else {
                ans = mid
                e = mid - 1
            }
        }

        if (ans == 0L) {
            println("NOTHING")
        } else {
            println("$s ${check(s) - check(s - 1)}")
        }
    }

    fun check(mid: Long): Long {
        var sum = 0L
        for (i in arr[0].indices) {
            val a = arr[0][i].toLong()
            val c = arr[1][i].toLong()
            val b = arr[2][i].toLong()

            if (mid < a) {
                continue
            }

            sum += (Math.min(mid, c) - a) / b
            sum++
        }

        return sum


    }
}

fun main() {
    boj1637().sol()
}