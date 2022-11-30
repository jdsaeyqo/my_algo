package com.ssafy.lib.dfs

class pg43164 {

    val answers = mutableListOf<String>()
    lateinit var visit: BooleanArray

    fun solution(tickets: Array<Array<String>>): Array<String> {

        visit = BooleanArray(tickets.size)

        dfs(0, "ICN", "ICN", tickets)

        val ans = answers.sorted()[0].split(" ").toTypedArray()

        return ans
    }

    fun dfs(count: Int, depart: String, course: String, tickets: Array<Array<String>>) {

        if (count == tickets.size) {
            answers.add(course)
            return
        }

        for (i in tickets.indices) {
            if (!visit[i] && tickets[i][0] == depart) {
                visit[i] = true

                dfs(count + 1, tickets[i][1], course + " " + tickets[i][1], tickets)
                visit[i] = false
            }
        }

    }

}
