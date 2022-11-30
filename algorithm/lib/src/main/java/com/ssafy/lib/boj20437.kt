package com.ssafy.lib

import java.io.BufferedReader
import java.io.InputStreamReader

class boj20437 {
    var str = ""
    var W = 0
    var threeStr = 10001
    var fourStr = -1
    fun sol() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val T = br.readLine().toInt()

        for (i in 0 until T) {

            str = br.readLine()
            W = br.readLine().toInt()

            val alphabet = IntArray(26)

            str.forEach {
                alphabet[it - 'a']++
            }

            var flag = false

            for (j in alphabet.indices) {
                if (alphabet[j] >= W) {
                    flag = true
                    break
                }
            }

            if (!flag) {
                println(-1)

            }

            go(0, str.length)
            println("$threeStr $fourStr")

        }

    }

    fun go(startIdx: Int, endIdx: Int) {

        if (startIdx == endIdx) {
            return
        }

        val s = str.substring(startIdx, endIdx)
        val alphabet = IntArray(26)

        s.forEach {
            alphabet[it - 'a']++
        }

        if (check1(alphabet)) {
            threeStr = threeStr.coerceAtMost(s.length)
        }

        if (check1(alphabet) && check2(s, alphabet)) {
            fourStr = fourStr.coerceAtMost(s.length)
        }

        go(startIdx + 1, endIdx)
        go(startIdx, endIdx - 1)

    }

    fun check1(alphabet: IntArray): Boolean {

        var flag = false

        for (i in alphabet.indices) {
            if (alphabet[i] == 0) {
                continue
            }
            if (alphabet[i] == W) {
                flag = true
                break
            }
        }

        return flag
    }

    fun check2(str: String, alphabet: IntArray): Boolean {

        val start = str[0]
        val end = str[str.length - 1]

        if (start != end) {
            return false
        }

        if (alphabet[start - 'a'] == W) {
            return true
        }

        return false
    }
}

fun main() {
    boj20437().sol()

}

