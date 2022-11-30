package com.ssafy.lib.bfs

import java.util.*

class boj16930 {

    var N = 0
    var M = 0
    var K = 0

    var x1 = 0
    var y1 = 0
    var x2 = 0
    var y2 = 0

    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    lateinit var map: Array<IntArray>

    fun sol() {

        var st = StringTokenizer(readLine())
        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        K = st.nextToken().toInt()

        map = Array(N) { IntArray(M) }

        repeat(N) { i ->
            val input = readLine()!!
            repeat(M) { j ->
                when (input[j]) {
                    '.' -> {
                        map[i][j] = Integer.MAX_VALUE
                    }
                    '#' -> {
                        map[i][j] = -1
                    }
                }
            }
        }

        st = StringTokenizer(readLine())
        x1 = st.nextToken().toInt()
        y1 = st.nextToken().toInt()
        x2 = st.nextToken().toInt()
        y2 = st.nextToken().toInt()

        bfs()
        if (map[x2 - 1][y2 - 1] == Int.MAX_VALUE) {
            println(-1)
        } else {
            println(map[x2 - 1][y2 - 1])
        }
    }

    fun bfs() {

        val q = LinkedList<Point>()
        q.offer(Point(x1 - 1, y1 - 1))
        map[x1 - 1][y1 - 1] = 0

        while (q.isNotEmpty()) {

            val cur = q.poll()

            val x = cur.x
            val y = cur.y

            if (x == x2 - 1 && y == y2 - 1) {
                break
            }

            for (i in 0 until 4) {

                for (k in 1..K) {
                    val nx = x + dx[i] * k
                    val ny = y + dy[i] * k

                    if (!(nx in 0 until N && ny in 0 until M)) {
                        break
                    }

                    if (map[nx][ny] == -1) {
                        break
                    }

                    if (map[nx][ny] > map[x][y] + 1) {
                        map[nx][ny] = map[x][y] + 1
                        q.offer(Point(nx, ny))
                    } else if (map[nx][ny] == map[x][y] + 1) {
                        continue
                    } else {
                        break
                    }

                }
            }
        }
    }

    data class Point(val x: Int, val y: Int)
}

fun main() {
    boj16930().sol()
}