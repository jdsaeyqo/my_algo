package com.ssafy.lib.simulation

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.StringTokenizer

class boj16235 {

    var N = 0
    var M = 0
    var K = 0

    lateinit var map: Array<IntArray>
    lateinit var feed: Array<IntArray>
    val trees = PriorityQueue<Tree>()
    val deadTrees = LinkedList<Tree>()
//    val dx = intArrayOf(-1,-1,-1,0,1,1,1,0)
//    val dy = intArrayOf(-1,0,1,1,1,0,-1,-1)
//
    val dx = intArrayOf(-1,-1,-1,0,1,1,1,0)
    val dy = intArrayOf(-1,0,1,1,1,0,-1,-1)


    fun sol() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        N = st.nextToken().toInt()
        M = st.nextToken().toInt()
        K = st.nextToken().toInt()

        map = Array(N + 1) { IntArray(N + 1) { 5 } }
        feed = Array(N + 1) { IntArray(N + 1) }


        for (i in 1..N) {
            st = StringTokenizer(br.readLine())
            for (j in 1..N) {
                feed[i][j] = st.nextToken().toInt()
            }
        }

        for (i in 0 until M) {
            st = StringTokenizer(br.readLine())
            val r = st.nextToken().toInt()
            val c = st.nextToken().toInt()
            val age = st.nextToken().toInt()
            trees.offer(
                Tree(r, c, age)
            )
        }

        for (i in 0 until K) {
            spring()
            summer()
            fall()
            winter()

            println(i+1)

            for(tree in trees){
                println(tree)
            }
        }

        println(trees.size)

    }

    private fun spring() {

        val q = LinkedList<Tree>()

        while (trees.isNotEmpty()) {

            val tree = trees.poll()

            val x = tree.x
            val y = tree.y
            val age = tree.age

            if (map[x][y] < age) {
                deadTrees.add(tree)
                continue
            }

            tree.age++
            map[x][y] -= age
            q.offer(tree)

        }
        while (q.isNotEmpty()) {
            val tree = q.poll()
            trees.offer(tree)
        }

    }

    private fun summer() {

        while (deadTrees.isNotEmpty()) {

            val tree = deadTrees.poll()
            val age = tree.age

            map[tree.x][tree.y] += (age / 2)

        }
    }

    private fun fall() {
        val q = LinkedList<Tree>()

        while (trees.isNotEmpty()) {

            val tree = trees.poll()

            if (tree.age % 5 != 0) {
                q.offer(tree)
                continue
            }
            val x = tree.x
            val y = tree.y
            for (i in 0 until 8) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx in 1..N && ny in 1..N) {
                    q.offer(Tree(nx, ny, 1))
                }
            }
            q.offer(tree)

        }

        while (q.isNotEmpty()) {
            val tree = q.poll()
            trees.offer(tree)
        }
    }

    private fun winter() {

        for (i in 1..N) {
            for (j in 1..N) {
                map[i][j] += feed[i][j]
            }
        }

    }

    data class Tree(
        var x: Int,
        var y: Int,
        var age: Int
    ) : Comparable<Tree> {
        override fun compareTo(other: Tree): Int {
           return this.age - other.age

        }
    }
}

fun main() {
    boj16235().sol()
}