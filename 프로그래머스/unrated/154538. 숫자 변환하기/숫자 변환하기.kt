import java.util.ArrayDeque

data class Info(val value: Int, val count: Int)
class Solution {
    fun solution(x: Int, y: Int, n: Int): Int {
        var answer: Int = 0

        val dq = ArrayDeque<Info>()
        dq.add(Info(x,0))

        val visit = BooleanArray(y + 1)

        while(!dq.isEmpty()){
            val now = dq.pollFirst()
            if(now.value == y){
                visit[y] = true
                answer = now.count
                break
            }
            val xpn = now.value + n
            if(xpn < y + 1 && !visit[xpn]){
                visit[xpn] = true
                dq.addLast(Info(xpn, now.count+1))
            }
            val x2 = now.value*2
            if(x2 < y + 1 && !visit[x2]){
                visit[x2] = true
                dq.addLast(Info(x2, now.count+1))
            }
            val x3 = now.value*3
            if(x3 < y + 1 && !visit[x3]){
                visit[x3] = true
                dq.addLast(Info(x3, now.count+1))
            }
        }

        if(answer == 0 && !visit[y]) answer = -1

        return answer
    }
}