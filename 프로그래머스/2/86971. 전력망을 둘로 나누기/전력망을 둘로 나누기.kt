import kotlin.math.abs

lateinit var links: Array<MutableList<Int>>
lateinit var visit: BooleanArray

class Solution {
    fun solution(n: Int, wires: Array<IntArray>): Int {
        var answer: Int = Int.MAX_VALUE
        
        links = Array(n+1) {mutableListOf<Int>()}
        wires.forEach {
            val s = it[0]
            val e = it[1]
            links[s].add(e)
            links[e].add(s)
        }
        
        for (i in 1 .. n) {
            links[i].forEach { next ->
                visit = BooleanArray(n+1)
                visit[i] = true
                visit[next] = true
                answer = minOf(answer, abs(bfs(i) - bfs(next)))
            }
        }
        return answer
    }
    
    private fun bfs(pos: Int): Int {
        val dq = ArrayDeque<Int>()
        dq.add(pos)
        var cnt = 1
        while(dq.isNotEmpty()) {
            val now = dq.removeFirst()
            links[now].forEach { next ->
                if(!visit[next]) {
                    cnt++
                    visit[next] = true
                    dq.addLast(next)
                }
            }
        }
        return cnt
    }
}