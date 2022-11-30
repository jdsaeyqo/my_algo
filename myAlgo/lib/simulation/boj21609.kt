import java.util.StringTokenizer

class boj21609 {

    var N = 0
    var M = 0
    lateinit var map: Array<IntArray>
    lateinit var turnMap: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    val list = arrayListOf<IntArray>()
    val tmpList = arrayListOf<IntArray>()

    var ans = 0
    fun sol() {

        var st = StringTokenizer(readLine())

        N = st.nextToken().toInt()
        M = st.nextToken().toInt()

        map = Array(N) { IntArray(N) }

        repeat(N) { i ->
            st = StringTokenizer(readLine())
            repeat(N) { j ->
                map[i][j] = st.nextToken().toInt()
            }
        }

        if (N == 1) {
            println(0)
            return
        }

        while (true) {

            var max = -1
            val visited = Array(N) { BooleanArray(N) }
            for (i in 0 until N) {
                for (j in 0 until N) {

                    if (!visited[i][j] && map[i][j] != -2 && map[i][j] != -1 && map[i][j] != 0) {
                        visited[i][j] = true
                        tmpList.add(intArrayOf(i, j))
                        val cnt = dfs(i, j, map[i][j], visited)

                        tmpList.forEach {
                            if (map[it[0]][it[1]] == 0) {
                                visited[it[0]][it[1]] = false
                            }
                        }

                        if (cnt < 2) {
                            tmpList.clear()
                            continue
                        }
                        if (cnt > max) {
                            max = cnt
                            list.clear()
                            list.addAll(tmpList)
                        } else if (cnt == max) {
                            checkList()
                        }
                        tmpList.clear()
                    }
                }
            }

            if (max == -1) {
                break
            }

            ans += (max * max)

            list.forEach {
                map[it[0]][it[1]] = -2
            }

            list.clear()

            getGravity()
            turn()
            getGravity()

        }

        println(ans)

    }

    fun turn() {
        turnMap = Array(N) { IntArray(N) }
        for (i in 0 until N) {
            for (j in 0 until N) {
                turnMap[i][j] = map[j][N - 1 - i]
            }
        }

        for (i in 0 until N) {
            for (j in 0 until N) {
                map[i][j] = turnMap[i][j]
            }
        }
    }

    fun getGravity() {
        for (i in 0 until N) {
            var cnt = 0
            for (j in N - 1 downTo 0) {
                if (map[j][i] == -2) {
                    cnt++
                    continue
                }

                if (map[j][i] == -1) {
                    cnt = 0
                    continue
                }

                if (cnt == 0) {
                    continue
                }

                map[j + cnt][i] = map[j][i]
                map[j][i] = -2

            }
        }
    }

    fun checkList() {

        var originZeroCnt = 0
        var newZeroCnt = 0

        list.forEach {
            if (map[it[0]][it[1]] == 0) {
                originZeroCnt++
            }
        }

        tmpList.forEach {
            if (map[it[0]][it[1]] == 0) {
                newZeroCnt++
            }
        }

        if (originZeroCnt > newZeroCnt) {
            return
        }

        if (originZeroCnt < newZeroCnt) {
            list.clear()
            list.addAll(tmpList)
            return
        }

        var originRowIndex = 0
        var originColumnIndex = 0
        var newRowIndex = 0
        var newColumnIndex = 0

        for (i in list.indices) {
            if (map[list[i][0]][list[i][1]] in 1..M) {
                originRowIndex = list[i][0]
                originColumnIndex = list[i][1]
                break
            }
        }

        for (i in tmpList.indices) {
            if (map[tmpList[i][0]][tmpList[i][1]] in 1..M) {
                newRowIndex = tmpList[i][0]
                newColumnIndex = tmpList[i][1]
                break
            }

        }
        if (originRowIndex > newRowIndex) {
            return
        }

        if (originRowIndex < newRowIndex) {
            list.clear()
            list.addAll(tmpList)
            return
        }

        if (originColumnIndex > newColumnIndex) {
            return
        }

        if (originColumnIndex < newColumnIndex) {
            list.clear()
            list.addAll(tmpList)
            return
        }

    }

    fun dfs(x: Int, y: Int, color: Int, visited: Array<BooleanArray>): Int {

        var cnt = 1

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (!(nx in 0 until N && ny in 0 until N)) {
                continue
            }

            if (!visited[nx][ny]) {
                if (map[nx][ny] == color || map[nx][ny] == 0) {
                    visited[nx][ny] = true
                    tmpList.add(intArrayOf(nx, ny))
                    cnt += dfs(nx, ny, color, visited)
                }
            }

        }

        return cnt

    }
}

fun main() {
    boj21609().sol()
}