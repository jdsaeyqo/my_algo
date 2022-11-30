package com.ssafy.lib.number

class boj1038 {
    val list = arrayListOf<Long>()
    fun sol() {
        val n = readLine()!!.toInt()

        if (n <= 10) {
            println(n)
        } else if (n > 1022) {
            println(-1)
        } else {

            for (i in 0L until 10L) {
                getNum(i, 1)
            }
            list.sort()
            println(list[n])
        }
    }

    fun getNum(num: Long, idx: Int) {

        if (idx > 10) {
            return
        }

        list.add(num)

        for (i in 0 until num % 10) {
            getNum(num * 10 + i, idx + 1)
        }

    }
}

fun main() {
    boj1038().sol()
}