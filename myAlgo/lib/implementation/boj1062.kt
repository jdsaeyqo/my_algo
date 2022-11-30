package com.ssafy.lib.implementation

class boj1062 {

    var N = 0
    var K = 0
    lateinit var words: Array<String>
    val alphabet = arrayOf(
        'b', 'd', 'e', 'f', 'g', 'h', 'j',
        'k', 'l', 'm', 'o', 'p', 'q', 'r',
        's', 'u', 'v', 'w', 'x', 'y', 'z'
    )
    lateinit var inputArr: Array<Char>
    val visited = BooleanArray(26)
    var max = -1

    fun sol() {
        val (n, k) = readLine()!!.split(" ").map { it.toInt() }
        N = n
        K = k
        words = Array(N) { "" }

        if (K < 5) {
            println(0)
            return
        } else if (K == 26) {
            println(N)
            return
        }

        repeat(N) { i ->
            val word = readLine()!!
            val newWord = word.replace("a", "").replace("c", "").replace("n", "").replace("t", "")
                .replace("i", "")
            words[i] = newWord
        }
        visited['a' - 'a'] = true
        visited['c' - 'a'] = true
        visited['n' - 'a'] = true
        visited['t' - 'a'] = true
        visited['i' - 'a'] = true

        inputArr = Array(K - 5) { 'A' }

        comb(0, 0)
        println(max)

    }

    fun comb(cur: Int, start: Int) {
        if (cur == K - 5) {

            inputArr.forEach {
                visited[it - 'a'] = true
            }

            var cnt = 0

            for (i in words.indices) {
                var flag = true
                for (j in words[i].indices) {
                    if (!visited[words[i][j] - 'a']) {
                        flag = false
                        break
                    }
                }
                if (flag) {
                    cnt++
                }
            }

            max = max.coerceAtLeast(cnt)
            inputArr.forEach {
                visited[it - 'a'] = false
            }

            return
        }

        for (i in start until alphabet.size) {
            inputArr[cur] = alphabet[i]
            comb(cur + 1, i + 1)
        }
    }

}

fun main() {
    boj1062().sol()
}