class Solution {
    fun solution(numbers: LongArray): LongArray {
        var answer: LongArray = longArrayOf()
        for(num in numbers){
            var number = "0" + num.toString(2)
            var count = 1L
            for(i in number.length-1 downTo 0){
                if(number[i] != '0'){
                    count *= 2L
                } else {
                    if(count > 1L){
                        answer += num + count/2L
                    } else answer += num + count 
                    break
                }
            }
        }
        return answer
    }
}