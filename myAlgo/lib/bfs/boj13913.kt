package com.ssafy.lib.bfs

import java.util.*

class boj13913 {

    var N = 0
    var M = 0
    val INF = 100001
    val visited = BooleanArray(INF)
    val parent = IntArray(INF)
    var count = 0
    fun sol() {

        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        N = n
        M = m

        val sb = StringBuilder()

        if (N == M) {
            sb.append(0).append("\n")
            sb.append(N).append("\n")
        } else {
            bfs()

            val stk = Stack<Int>()

            var index = M
            stk.push(index)
            while (index != N) {
                stk.push(parent[index])
                index = parent[index]
            }

            while (stk.isNotEmpty()) {
                sb.append(stk.pop()).append(" ")
            }

            println(count)
        }

        println(sb.toString())
    }

    fun bfs() {

        val q = LinkedList<Int>()
        q.offer(N)

        while (q.isNotEmpty()) {

            val size = q.size
            for (i in 0 until size) {
                val cur = q.poll()

                if (cur == M) {
                    return
                }

                if (cur * 2 < INF && !visited[cur * 2]) {
                    parent[cur * 2] = cur
                    visited[cur * 2] = true
                    q.offer(cur * 2)

                }

                if (cur + 1 < INF && !visited[cur + 1]) {
                    parent[cur + 1] = cur
                    visited[cur + 1] = true
                    q.offer(cur + 1)
                }

                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    parent[cur - 1] = cur
                    visited[cur - 1] = true
                    q.offer(cur - 1)
                }


            }
            count++
        }
    }


}

fun main() {
    boj13913().sol()
}