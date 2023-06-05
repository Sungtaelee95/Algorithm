import java.util.*
class Solution {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = intArrayOf()
        var pro = ArrayDeque<Int>()
        var speed = ArrayDeque<Int>()
        
        //자료입력
        for(i in 0 .. progresses.size-1){
            pro.addLast(progresses[i])
            speed.addLast(speeds[i])
        }
        var count = 0
        while(pro.size != 0){
            if(pro.peekFirst() >= 100){
                for(i in 1 .. pro.size){
                    if(pro.peekFirst() >= 100){
                        pro.removeFirst()
                        speed.removeFirst()
                        count++
                    } else {
                        break
                    }
                }
                answer += count
                count = 0
            } else{
                for(i in 1 .. pro.size){
                    pro.addLast(pro.pollFirst() + speed.peekFirst())
                    speed.addLast(speed.pollFirst())
                }
            }  
        }
        return answer
    }
}