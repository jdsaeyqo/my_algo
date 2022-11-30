package com.ssafy.lib.dfs

class pg92343 {

    val map = ArrayList<ArrayList<Int>>()

    var N = 0
    var max = 0
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        var answer: Int = 0

        N = info.size

        repeat(N) {
            map.add(arrayListOf())
        }

        for (i in edges.indices) {
            map[edges[i][0]].add(edges[i][1])

        }

        dfs(0, 1, 0, arrayListOf(0), info)

        println(max)
        return max
    }

    fun dfs(
        cur: Int,
        sheepCnt: Int,
        wolfCnt: Int,
        list: ArrayList<Int>,
        info: IntArray
    ) {

        if (sheepCnt <= wolfCnt) {
            return
        }
        max = max.coerceAtLeast(sheepCnt)

        val tmp = arrayListOf<Int>()
        tmp.addAll(list)
        tmp.remove(cur)
        tmp.addAll(map[cur])

        for (i in tmp.indices) {
            if (info[tmp[i]] == 0) {
                dfs(tmp[i], sheepCnt + 1, wolfCnt, tmp, info)
            } else {
                dfs(tmp[i], sheepCnt, wolfCnt + 1,tmp, info)
            }

        }

    }
}