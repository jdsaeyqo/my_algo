package com.ssafy.lib.dfs

import java.util.LinkedList

class boj1303 {
    var N = 0
    var M = 0

    var W = 0
    var B = 0
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    lateinit var map: Array<CharArray>
    lateinit var visited: Array<BooleanArray>
    fun sol() {

        val input = readLine()!!.split(" ").map { it.toInt() }

        M = input[0]
        N = input[1]
        map = Array(N) { CharArray(M) }
        visited = Array(N) { BooleanArray(M) }

        repeat(N) { i ->
            val line = readLine()!!
            repeat(M) { j ->
                map[i][j] = line[j]
            }
        }

        repeat(N) { i ->
            repeat(M) { j ->
                if (!visited[i][j]) {
//                    val cnt = dfs(i, j, map[i][j])
                    val cnt = bfs(i, j, map[i][j])
                    println("$cnt ${map[i][j]}")
                    if (map[i][j] == 'W') {
                        W += cnt * cnt
                    } else {
                        B += cnt * cnt
                    }

                }
            }
        }

        println("$W $B")


    }

    fun dfs(x: Int, y: Int, flag: Char): Int {

        var cnt = 1
        visited[x][y] = true

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (!(nx in 0 until N && ny in 0 until M)) {
                continue
            }

            if (visited[nx][ny]) {
                continue
            }

            if (map[nx][ny] == flag) {
                cnt += dfs(nx, ny, flag)

            }
        }

        return cnt
    }

    fun bfs(x: Int, y: Int, flag: Char): Int {
        var cnt = 1
        val q = LinkedList<Point>()
        q.offer(Point(x, y))
        visited[x][y] = true

        while (q.isNotEmpty()) {
            val cur = q.poll()
            val curX = cur.x
            val curY = cur.y

            visited[curX][curY] = true

            for (i in 0 until 4) {
                val nx = curX + dx[i]
                val ny = curY + dy[i]

                if (!(nx in 0 until N && ny in 0 until M)) {
                    continue
                }
                if (visited[nx][ny]) {
                    continue
                }

                if (map[nx][ny] == flag) {
//                    visited[nx][ny] = true
                    cnt++
                    q.offer(Point(nx, ny))

                }
            }


        }
        return cnt
    }

    data class Point(val x: Int, val y: Int)
}

fun main() {
    boj1303().sol()
}