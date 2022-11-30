package com.ssafy.lib.dfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class boj14503 {
    val br = BufferedReader(InputStreamReader(System.`in`))
    lateinit var st: StringTokenizer

    var n = 0
    var m = 0

    lateinit var map: Array<IntArray>
    var roboX = 0
    var roboY = 0
    var dir = 0

    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    var ans = 0
    fun sol() {
        st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        st = StringTokenizer(br.readLine())
        roboX = st.nextToken().toInt()
        roboY = st.nextToken().toInt()
        dir = st.nextToken().toInt()

        map = Array(n) { IntArray(m) }

        repeat(n) { i ->
            st = StringTokenizer(br.readLine())
            repeat(m) { j ->
                map[i][j] = st.nextToken().toInt()
            }
        }

        cleanUp(roboX, roboY)

        println(ans)
    }

    private fun cleanUp(x: Int, y: Int) {

        if (map[x][y] != -1) {
            map[x][y] = -1
            ans++
        }

        var check = false

        for (i in 0 until 4) {

            dir--
            if (dir < 0) {
                dir += 4
            }

            val nx = x + dx[dir]
            val ny = y + dy[dir]

            if (nx in 0 until n && ny in 0 until m) {
                if (map[nx][ny] == 0) {
                    cleanUp(nx, ny)
                    check = true
                    return
                }
            }

        }


        var back = dir - 2
        if (back < 0) {
            back += 4
        }

        val backX = x + dx[back]
        val backY = y + dy[back]
        if (backX in 0 until n && backY in 0 until m) {
            if (map[backX][backY] != 1) {
                cleanUp(backX, backY)
            }
        }




    }

}

fun main() {

    boj14503().sol()

}

