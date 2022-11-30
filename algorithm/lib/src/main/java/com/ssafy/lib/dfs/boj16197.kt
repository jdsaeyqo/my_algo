package com.ssafy.lib.dfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class boj16197 {

    var N = 0
    var M = 0
    lateinit var map: Array<IntArray>

    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    var min = Integer.MAX_VALUE
    fun sol() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        val st = StringTokenizer(br.readLine())

        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        val coinList = ArrayList<IntArray>()
        map = Array(N + 2) { IntArray(M + 2) }

        for (i in 0 until N) {
            val input = br.readLine()
            for (j in 0 until M) {
                when (input[j]) {
                    'o' -> {
                        map[i + 1][j + 1] = 2
                        coinList.add(intArrayOf(i + 1, j + 1))

                    }

                    '#' -> map[i + 1][j + 1] = 1
                    '.' -> map[i + 1][j + 1] = 0
                }
            }
        }
        val coin1 = coinList[0]
        val coin2 = coinList[1]

        dfs(0, coin1, coin2)

        if(min == Integer.MAX_VALUE){
            min = -1
        }
        println(min)
    }

    fun dfs(cnt: Int, coin1: IntArray, coin2: IntArray) {

        if (min <= cnt || cnt >= 10) {
            return
        }

        val cx1 = coin1[0]
        val cy1 = coin1[1]

        val cx2 = coin2[0]
        val cy2 = coin2[1]


        for (i in 0 until 4) {

            var aFlag = false
            var bFlag = false


            val nx1 = cx1 + dx[i]
            val ny1 = cy1 + dy[i]

            val nx2 = cx2 + dx[i]
            val ny2 = cy2 + dy[i]

            if (isOut(intArrayOf(nx1, ny1))) {
                aFlag = true
            }

            if (isOut(intArrayOf(nx2, ny2))) {
                bFlag = true
            }

            if (aFlag && bFlag) {
                continue
            }

            if (aFlag || bFlag) {
                min = min.coerceAtMost(cnt + 1)
            }

            var newCoin1 = coin1
            var newCoin2 = coin2


            if (map[nx1][ny1] != 1 && !aFlag) {
                newCoin1 = intArrayOf(nx1, ny1)
            }


            if (map[nx2][ny2] != 1 && !bFlag) {
                newCoin2 = intArrayOf(nx2, ny2)
            }



            dfs(cnt + 1, newCoin1, newCoin2)

        }

    }

    fun isOut(coin: IntArray): Boolean {
        val x = coin[0]
        val y = coin[1]

        if (x == 0 || x == N + 1 || y == 0 || y == M + 1) {
            return true
        }
        return false
    }
}

fun main() {
    boj16197().sol()
}