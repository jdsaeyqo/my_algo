package com.ssafy.lib.union_find

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class boj1043 {
    var n = 0
    var m = 0
    var k = 0
    lateinit var known : BooleanArray
    lateinit var parent: IntArray

    val parties = ArrayList<ArrayList<Int>>()

    fun sol() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        st = StringTokenizer(br.readLine())
        k = st.nextToken().toInt()

        parent = IntArray(n + 1) { it }
        known = BooleanArray(n+1)

        repeat(k) {
            known[st.nextToken().toInt()] = true
        }

        repeat(m) {
            st = StringTokenizer(br.readLine())
            val num = st.nextToken().toInt()
            val tmpList = arrayListOf<Int>()

            repeat(num) {
                val person = st.nextToken().toInt()

                tmpList.add(person)
            }

            for (i in 0 until tmpList.size - 1) {
                val first = tmpList[i]
                val second = tmpList[i + 1]

                if (find(first) != find(second)) {
                    union(first, second)
                }

            }
            parties.add(tmpList)
        }

        val visited = BooleanArray(n+1)

        for(i in 1..n){
            if(known[i] && !visited[i]){
                val parent = find(i)
                for(j in 1..n){
                    if(parent == find(j)){
                        known[j] = true
                        visited[j] = true
                    }
                }
            }
        }

        val knownList = arrayListOf<Int>()
        for(i in known.indices){
            if(known[i]){
                knownList.add(i)
            }
        }

        var cnt = m

        repeat(m){ i ->
            for(j in parties[i].indices){
                if(knownList.contains(parties[i][j])){
                    cnt--
                    break
                }
            }
        }


        println(cnt)


    }

    private fun find(person: Int): Int {
        return if (parent[person] == person) {
            person
        } else {
            parent[person] = find(parent[person])
            parent[person]
        }
    }

    private fun union(a: Int, b: Int) {

        val x = find(a)
        val y = find(b)

        if(x != y){
            parent[y] = x
        }



    }
}

fun main() {
    boj1043().sol()
}