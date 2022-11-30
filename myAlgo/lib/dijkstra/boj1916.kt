package com.ssafy.lib.dijkstra

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

class boj1916 {
    var n = 0
    var m = 0

    lateinit var dist: IntArray
    lateinit var visit: BooleanArray
    lateinit var list: ArrayList<ArrayList<Node>>

    fun sol() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st: StringTokenizer
        n = br.readLine().toInt()
        m = br.readLine().toInt()

        dist = IntArray(n + 1) { Integer.MAX_VALUE }
        visit = BooleanArray(n + 1)

        list = arrayListOf()

        for (i in 0..n) {
            list.add(arrayListOf())
        }

        for (i in 0 until m) {
            st = StringTokenizer(br.readLine())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            val time = st.nextToken().toInt()

            list[from].add(Node(to, time))

        }
        st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val destination = st.nextToken().toInt()


        dijkstra(start)
        println(dist[destination])

    }

    private fun dijkstra(start: Int) {

        dist[start] = 0
        val node = Node(start, 0)
        val pq = PriorityQueue<Node>()
        pq.offer(node)

        while (pq.isNotEmpty()) {
            val currentTo = pq.poll().to

            if (visit[currentTo]) {
                continue
            }
            visit[currentTo] = true

            val nextList = list[currentTo]

            for (i in nextList.indices) {

                val tmp = nextList[i]

                if (dist[tmp.to] > dist[currentTo] + tmp.time) {
                    dist[tmp.to] = dist[currentTo] + tmp.time
                    pq.offer(Node(tmp.to, dist[tmp.to]))
                }

            }

        }

    }
}


data class Node(val to: Int, val time: Int) : Comparable<Node> {

    override fun compareTo(other: Node): Int {
        return this.time - other.time
    }
}

fun main() {
    boj1916().sol()
}