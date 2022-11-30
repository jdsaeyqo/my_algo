package com.ssafy.lib.number

import java.util.StringTokenizer

class boj21318 {
    var N = 0
    lateinit var musicArr: LongArray
    lateinit var sum: IntArray
    fun sol() {

        N = readLine()!!.toInt()

        musicArr = LongArray(N + 1)
        sum = IntArray(N + 1)

        var st = StringTokenizer(readLine())
        for (i in 1..N) {
            musicArr[i] = st.nextToken().toLong()
            if (musicArr[i] < musicArr[i - 1]) {
                sum[i] = sum[i - 1] + 1
            } else {
                sum[i] = sum[i - 1]
            }
        }
        val T = readLine()!!.toInt()
        val sb = StringBuilder()

        repeat(T) {
            st = StringTokenizer(readLine())
            val s = st.nextToken().toInt()
            val e = st.nextToken().toInt()

            sb.append(sum[e]-sum[s]).append("\n")

        }
        println(sb.toString())

    }
}

fun main() {
    boj21318().sol()
}