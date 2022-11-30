package com.ssafy.lib.tree

class boj3584 {

    lateinit var parent: IntArray
    val aList = arrayListOf<Int>()
    val bList = arrayListOf<Int>()

    fun sol() {

        val T = readLine()!!.toInt()

        repeat(T) {

            val N = readLine()!!.toInt()

            parent = IntArray(N + 1)

            for (i in 0 until N) {

                val (a, b) = readLine()!!.split(" ").map { it.toInt() }

                if (i == N - 1) {
                    getLCA(a, b)
                } else {
                    parent[b] = a

                }
            }

            aList.reverse()
            bList.reverse()

            val size = if (aList.size >= bList.size) bList.size else aList.size
            var lca = 0

            for (i in 0 until size) {
                if (aList[i] != bList[i]) {
                    break
                }
                lca = aList[i]
            }

            println(lca)
            aList.clear()
            bList.clear()

        }

    }

    fun getLCA(a: Int, b: Int) {
        var first = a
        while (first != 0) {
            aList.add(first)
            first = parent[first]
        }

        var second = b
        while (second != 0) {
            bList.add(second)
            second = parent[second]
        }
    }

}

fun main() {
    boj3584().sol()
}