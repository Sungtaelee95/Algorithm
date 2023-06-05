class Solution {
    fun solution(num: Int): Int {
        var answer = 0
        var num2 = num.toLong()
        while(answer<500) {
            if( num2 == 1L) break;
            else if( num2 % 2L == 0L){
                num2 /= 2
                answer++
            } else {
                num2 *= 3
                num2 += 1
                answer++
            }
        }
        
        if(answer == 500) answer = -1
        
        return answer
    }
}