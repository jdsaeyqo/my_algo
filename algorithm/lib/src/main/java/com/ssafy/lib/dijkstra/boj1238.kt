package com.ssafy.lib.dijkstra

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

class boj1238 {
    var N = 0
    var M = 0
    var X = 0
    var max = Integer.MIN_VALUE

    lateinit var visited: BooleanArray
    val INF = 100001

    fun sol() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        X = st.nextToken().toInt()

        val dist = IntArray(N + 1) { INF }
        dist[X] = 0
        val distReverse = IntArray(N + 1) { INF }
        distReverse[X] = 0
        val map = ArrayList<ArrayList<Node>>()
        val reverseMap = ArrayList<ArrayList<Node>>()

        for (i in 0..N) {
            map.add(arrayListOf())
        }

        for (i in 0..N) {
            reverseMap.add(arrayListOf())
        }

        repeat(M) {
            st = StringTokenizer(br.readLine())
            val start = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            val width = st.nextToken().toInt()
            map[start].add(Node(to, width))
            reverseMap[to].add(Node(start,width))
        }

        for (i in 1..N) {
            var d = 0
            d = dijkstra(X, i, map,dist) + dijkstra(X, i, reverseMap,distReverse)
            max = d.coerceAtLeast(max)
        }

        println(max)

    }

    private fun dijkstra(start: Int, end: Int, map : ArrayList<ArrayList<Node>>, dist : IntArray): Int {

        visited = BooleanArray(N + 1)
        val pq = PriorityQueue<Node>()
        pq.offer(Node(start, 0))

        while (pq.isNotEmpty()) {
            val curNode = pq.poll()
            val to = curNode.to
            visited[to] = true
            for (next in map[to]) {
                if(!visited[next.to] && dist[next.to] > dist[to] + next.width){
                    dist[next.to] = dist[to] + next.width
                    pq.offer(Node(next.to,dist[next.to]))
                }
            }

        }

        return dist[end]


    }

    data class Node(val to: Int, val width: Int) : Comparable<Node> {

        override fun compareTo(other: Node): Int {
            return this.width - other.width
        }
    }
}

fun main() {
    boj1238().sol()
}