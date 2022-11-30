package com.ssafy.backtrackting

class recur {


    val m = 5

    val visit = BooleanArray(m)
    val arr2 = intArrayOf(2, 4, 5, 6, 8)
    val n = arr2.size
    val arr = IntArray(n)

    //m진수 2자리
    fun recur1(cur: Int) {

        if (cur == n) {
            println(arr.contentToString())
            return
        }

        for (i in 0 until m) {
            arr[cur] = i
            recur1(cur + 1)
        }
    }

    //순열
    fun recur2(cur: Int) {

        if (cur == n) {
            println(arr.contentToString())
            return
        }

        for (i in 0 until m) {
            if (visit[i]) {
                continue
            }
            arr[cur] = arr2[i]
            visit[i] = true
            recur2(cur + 1)
            visit[i] = false
        }
    }

    fun recur3(cur: Int, start: Int) {
        if (start == 0) {
            println(arr.contentToString())
            return

        }

        for (i in start until n) {
            arr[cur] = arr2[i]
            recur3(cur + 1, start + 1)


        }
    }

    fun comb(start: Int,r : Int) {
        if (r == 0) {
            for (i in visit.indices) {
                if (visit[i]) {
                    print("${arr2[i]} ")
                }
            }
            println()
            return
        }

        for (i in start until n) {
                visit[i] = true
                comb(i + 1,r - 1)
                visit[i] = false

        }
    }
}


fun main() {

//    recur().recur1(0)
//    recur().recur2(0)
//    recur().recur3(0,0)
    recur().comb(0,3)

}

