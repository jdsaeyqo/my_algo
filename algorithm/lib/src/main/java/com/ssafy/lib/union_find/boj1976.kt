package com.ssafy.lib.union_find

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class boj1976 {

    lateinit var parent: IntArray
    var N = 0
    var M = 0
    lateinit var map: Array<IntArray>
    lateinit var target: IntArray
    fun sol() {
        val br = BufferedReader(InputStreamReader(System.`in`))

        N = br.readLine().toInt()
        M = br.readLine().toInt()

        parent = IntArray(N + 1) { it }

        map = Array(N) { IntArray(N) }
        target = IntArray(M)

        for (i in 0 until N) {
            val st = StringTokenizer(br.readLine())
            for (j in 0 until N) {
                map[i][j] = st.nextToken().toInt()

                if (map[i][j] == 1) {
                    union(i + 1, j + 1)


                }
            }
        }

        val st = StringTokenizer(br.readLine())

        for (i in 0 until M) {
            target[i] = st.nextToken().toInt()
        }

        var flag = true
        val first = target[0]

        for (i in 1 until target.size) {

            val next = target[i]

            if (!isSameParent(first, next)) {
                flag = false
                break
            }
        }

        if (flag) {
            println("YES")
        } else {
            println("NO")
        }

    }

    fun find(x: Int): Int {
        return if (x == parent[x]) {
            x
        } else {
            parent[x] = find(parent[x])
            parent[x]
        }
    }

    fun union(a: Int, b: Int) {
        val x = find(a)
        val y = find(b)

        if (x != y) {
            if (x < y) {
                parent[y] = x
            } else {
                parent[x] = y
            }
        }
    }

    fun isSameParent(a: Int, b: Int): Boolean {
        val x = find(a)
        val y = find(b)

        return x == y
    }

}

fun main() {
    boj1976().sol()
}