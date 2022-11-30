package com.ssafy.lib.bfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class boj13549 {

    fun sol() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val st = StringTokenizer(br.readLine());

        val N = st.nextToken().toInt()
        val M = st.nextToken().toInt()

        val visited = BooleanArray(100001)

        val q = LinkedList<Point>()
        q.offer(Point(N, 0))

        var ans = 0
        while (q.isNotEmpty()) {

            val cur = q.poll()
            val position = cur.position

            visited[position] = true
            if (position == M) {
                ans = cur.dist
                break
            }

            if (position * 2 <= 100000 && !visited[position*2]) {
                q.offer(Point(position*2,cur.dist))
            }
            if (position-1 >= 0 && !visited[position-1]) {
                q.offer(Point(position-1,cur.dist+1))
            }

            if (position+1 <= 100000 && !visited[position+1] ) {
                q.offer(Point(position+1,cur.dist+1))
            }


        }

        println(ans)
    }
}

data class Point(var position: Int, var dist: Int)

fun main() {
    boj13549().sol()
}