class Solution {
    fun solution(n: Int): Int {
        var answer = 0
        var count = n
        while(count > 0){
            answer += count % 10
            count /= 10
        }

        return answer
    }
}