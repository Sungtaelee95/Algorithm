import java.util.ArrayDeque
import java.util.Stack

class Solution {
    fun solution(number: String, k: Int): String {
        var answer = StringBuilder()
        var dq = ArrayDeque<Int>()
        
        var count = 0
        
        for(i in 0 .. number.length-1){

            // 저장된 수가 없다면 수 저장
            if(dq.isEmpty() ){
                dq.add(number[i].toString().toInt())
                continue
            }

            // 낮은 수 제거
            while(!dq.isEmpty()){
                if(dq.peekLast() < number[i].toString().toInt() && count < k){
                    dq.pollLast()
                    count++
                } else {
                    break
                }
            }
            dq.addLast(number[i].toString().toInt())

        }
        println(count)
        
        
        repeat(number.length-k){
            answer.append(dq.pollFirst())
        }

        return answer.toString()
    }
}