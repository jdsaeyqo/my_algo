package com.ssafy.lib.bfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class boj1261 {
    var N = 0
    var M = 0
    val dx = intArrayOf(0, 1, 0, -1)
    val dy = intArrayOf(1, 0, -1, 0)
    lateinit var visited: Array<BooleanArray>
    lateinit var map: Array<IntArray>
    lateinit var dist: Array<IntArray>
    fun sol() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val st = StringTokenizer(br.readLine())

        M = st.nextToken().toInt()
        N = st.nextToken().toInt()

        map = Array(N) { IntArray(M) }
        dist = Array(N) { IntArray(M) { Int.MAX_VALUE } }
        visited = Array(N) { BooleanArray(M) }

        for (i in 0 until N) {
            val input = br.readLine()
            for (j in 0 until M) {
                map[i][j] = input[j] - '0'
            }
        }

        bfs()

    }

    fun bfs() {

        val q = PriorityQueue(Comparator<Node>{ o1, o2 ->
            dist[o1.x][o1.y] - dist[o2.x][o2.y]
        })

        q.offer(Node(0, 0))
        dist[0][0] = 0

        while (q.isNotEmpty()) {
            val cur = q.poll()
            val cx = cur.x
            val cy = cur.y

            if (cx == N - 1 && cy == M - 1) {
                println(dist[N - 1][M - 1])
                break
            }

            for (i in 0 until 4) {
                val nx = cx + dx[i]
                val ny = cy + dy[i]

                if (nx in 0 until N && ny in 0 until M) {

                    if (map[nx][ny] == 0) {
                        if (dist[nx][ny] > dist[cx][cy]) {
                            dist[nx][ny] = dist[cx][cy]
                            q.offer(Node(nx,ny))

                        }
                    } else {
                        if (dist[nx][ny] > dist[cx][cy] + 1) {
                            dist[nx][ny] = dist[cx][cy] + 1
                            q.offer(Node(nx,ny))

                        }
                    }


                }
            }

        }
    }

    data class Node(val x: Int, val y: Int)

}

fun main() {
    boj1261().sol()
}