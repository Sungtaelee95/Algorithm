import java.util.*
class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0
        var sum1 = queue1.sum().toLong()
        var sum2 = queue2.sum().toLong()
        var que1 = ArrayDeque<Long>()
        var que2 = ArrayDeque<Long>()
        for(i in 0 .. queue1.size-1){ 
            que1.add(queue1[i].toLong())
            que2.add(queue2[i].toLong())
        }
        val stopCheck = (que1.size+1) * 2
        while(true){
            if(sum1 > sum2){
                sum2 += que1.peekFirst()
                sum1 -= que1.peekFirst()
                que2.add(que1.pollFirst())
                answer++
            } else if(sum1 < sum2){
                sum1 += que2.peekFirst()
                sum2 -= que2.peekFirst()
                que1.add(que2.pollFirst())
                answer++
            } else {
                break
            }
            if(answer > stopCheck){
                answer = -1
                break
            }
        }
        return answer
    }
}