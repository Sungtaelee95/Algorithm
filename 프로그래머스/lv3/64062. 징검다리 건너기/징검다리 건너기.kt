import java.lang.Math.min
import java.util.PriorityQueue


class Solution {
    fun solution(stones: IntArray, k: Int): Int {

        var pq = PriorityQueue<Check> { o1, o2 -> o2.vlu.compareTo(o1.vlu) }

        for(i in 0 .. k-1){
            pq.add(Check(i, stones[i]))
        }

        var answer = pq.peek().vlu

        var now = k
        while(now < stones.size){
            pq.add(Check(now, stones[now]))
            now++
            while(pq.peek().idx < now - k){
                pq.poll()
            }
            answer = min(answer, pq.peek().vlu)
        }


        return answer
    }
}

data class Check(
    val idx: Int,
    val vlu: Int
)