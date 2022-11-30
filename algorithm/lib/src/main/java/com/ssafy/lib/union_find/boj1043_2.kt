package com.ssafy.lib.union_find

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

class boj1043_2 {

    var n = 0
    var m = 0
    var k = 0
    val parties = ArrayList<ArrayList<Int>>()
    val known = hashSetOf<Int>()
    lateinit var parent: IntArray

    lateinit var info: Array<IntArray>

//    3 3
//    1 1
//    1 2 3
//    1 3
//    1 1 3

    fun sol() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
        st = StringTokenizer(br.readLine())
        k = st.nextToken().toInt()

        parent = IntArray(n + 1) { it }
        if (k == 0) {
            println(m)
            return
        }

        repeat(k) {
            known.add(st.nextToken().toInt())
        }

        repeat(m) {
            var flag = false
            st = StringTokenizer(br.readLine())

            val num = st.nextToken().toInt()
            val list = arrayListOf<Int>()
            repeat(num) {
                val person = st.nextToken().toInt()
                if (known.contains(person)) {
                    flag = true
                }
                list.add(person)
            }

            parties.add(list)
            if (flag) {
                known.addAll(list)
            }
        }
        var cnt = 0
        for (i in parties.indices) {
            var flag = true
          for(j in parties[i].indices){
              if(known.contains(parties[i][j])){
                  flag = false
                  break
              }
          }

            if(flag){
                cnt++
            }
        }

        println(cnt)

    }
}

fun main() {
    boj1043_2().sol()
}