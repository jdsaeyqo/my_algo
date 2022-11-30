package com.ssafy.lib.tree

class boj1991 {

    lateinit var left: CharArray
    lateinit var right: CharArray
    fun sol() {
        val N = readLine()!!.toInt()

        left = CharArray(N)
        right = CharArray(N)

        for (i in 0 until N) {

            val (pa,l,r) = readLine()!!.split(" ")

            if (l[0] != '.') {
                left[pa[0] - 'A'] = l[0]
            }else{
                left[pa[0] - 'A'] = '.'
            }


            if (r[0] != '.') {
                right[pa[0] - 'A'] = r[0]
            }else{
                right[pa[0] - 'A'] = '.'
            }

        }

        printOrder('A', 0)
        println()
        printOrder('A', 1)
        println()
        printOrder('A', 2)

    }

    fun printOrder(cur: Char, option: Int) {

        if (cur == '.') {
            return
        }

        if (option == 0) {
            print(cur)
        }
        printOrder(left[cur - 'A'], option)
        if (option == 1) {
            print(cur)
        }
        printOrder(right[cur - 'A'], option)
        if (option == 2) {
            print(cur)
        }

    }
}

fun main() {
    boj1991().sol()
}