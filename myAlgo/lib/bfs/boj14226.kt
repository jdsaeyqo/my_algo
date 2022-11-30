package com.ssafy.lib.bfs

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

class boj14226 {
    var N = 0
    var depth = 2
    val INF = 2001
    val visited = Array(INF) { BooleanArray(INF) }
    fun sol() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        N = br.readLine().toInt()
        val clipBoard = 1
        val emoji = 2
        bfs(emoji, clipBoard)
        println(depth)
    }

    fun bfs(emoji: Int, clipBoard: Int) {
        val q = LinkedList<Node>()
        q.offer(Node(emoji, clipBoard))
        visited[emoji][clipBoard] = true
        while (q.isNotEmpty()) {

            val size = q.size
            for (i in 0 until size) {
                val cur = q.poll()

                val curEmoji = cur.emoji
                val curClipBoard = cur.clipBoard

                if (curEmoji == N) {
                    return
                }

                if (curEmoji in 0 until INF) {
                    if (!visited[curEmoji][curEmoji]) {
                        visited[curEmoji][curEmoji] = true
                        q.offer(Node(curEmoji, curEmoji))
                    }
                }

                if (curEmoji + curClipBoard in 0 until INF) {
                    if (!visited[curEmoji + curClipBoard][curClipBoard]) {
                        visited[curEmoji + curClipBoard][curClipBoard] = true
                        q.offer(Node(curEmoji + curClipBoard, curClipBoard))
                    }
                }

                if (curEmoji - 1 >= 0) {
                    if (!visited[curEmoji - 1][curClipBoard]) {
                        visited[curEmoji - 1][curClipBoard] = true
                        q.offer(Node(curEmoji - 1, curClipBoard))
                    }
                }

            }

            depth++

        }

    }

    data class Node(val emoji: Int, val clipBoard: Int)

}

fun main() {
    boj14226().sol()
}