package com.ssafy.lib.dfs

import java.util.LinkedList

class boj16953 {

    var A = 0L
    var B = 0L
    var cnt = 0
    var flag = false
    fun sol() {
        val (a, b) = readLine()!!.split(" ").map { it.toLong() }
        A = a
        B = b
        dfs(A, 0)
        if (!flag) {
            println(-1)
        }

    }

    fun dfs(num: Long, cnt: Int) {

        if (num == B) {
            println(cnt+1)
            flag = true
            return
        }

        if (num > B) {
            return
        }

        dfs(num * 2, cnt + 1)
        dfs((num.toString() + "1").toLong(), cnt + 1)

    }

}

fun main() {
    boj16953().sol()
}