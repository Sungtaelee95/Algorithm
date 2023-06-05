import java.util.*
class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        var waitTruck = ArrayDeque<Int>()
        truck_weights.forEach{
            waitTruck.add(it)
        }
        var time = ArrayDeque<Int>()
        var pass = ArrayDeque<Int>()
        while(true){
            if(waitTruck.isNotEmpty()){
                if(pass.sum() + waitTruck.peekFirst() <= weight && pass.size < bridge_length){
                    pass.add(waitTruck.pollFirst())
                    time.add(answer)
                }
            }
            if(answer - time.first() >= bridge_length){
                pass.removeFirst()
                time.removeFirst()
            }
            if(waitTruck.isNotEmpty()){
                if(pass.sum() + waitTruck.peekFirst() <= weight && pass.size < bridge_length){
                    pass.add(waitTruck.pollFirst())
                    time.add(answer)
                }
            }
            answer++
            if(waitTruck.isEmpty() && pass.isEmpty()) break
        }

        return answer
    }
}