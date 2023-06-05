class Solution {
    fun solution(numbers: IntArray): IntArray {
        var result = mutableSetOf<Int>()
        
        for(i in 0 .. numbers.size - 1){
            for(j in numbers.size - 1 downTo 1 ){
                if(j == i) continue
                result.add(numbers[i] + numbers[j])
            }
        }
        
        var answer = result.toIntArray()
        answer.sort()
        return answer
    }
}