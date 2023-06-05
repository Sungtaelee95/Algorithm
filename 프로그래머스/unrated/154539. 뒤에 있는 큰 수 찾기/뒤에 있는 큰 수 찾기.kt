import java.util.*
class Solution {
    fun solution(numbers: IntArray): IntArray {
        var result = ArrayDeque<Int>()
        var stack = ArrayDeque<Int>()
        for(i in numbers.size-1 downTo 0){
            while(!stack.isEmpty()){
                if(stack.peekLast() > numbers[i]){
                    result.add(stack.peekLast())
                    stack.add(numbers[i])
                    break
                } else {
                    stack.removeLast()
                }
            }
            if(stack.isEmpty()){
                result.add(-1)
                stack.add(numbers[i])
            }
        }
        var answer = IntArray(numbers.size)
        for(i in 0 .. answer.size-1){
            answer[i] = result.pollLast()
        }
        return answer
    }
}