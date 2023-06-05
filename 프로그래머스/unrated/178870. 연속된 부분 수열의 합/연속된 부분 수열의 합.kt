class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var answer: IntArray = intArrayOf(0,1000000001)

        var sum = sequence[0]
        var left = 0
        var right = 0

        while(left < sequence.size && right < sequence.size){
            if(sum < k){
                right++
                if(right < sequence.size) sum += sequence[right]
            } else if(sum > k){
                sum -= sequence[left]
                left++
            } else {
                if(answer[1]-answer[0] > right - left){
                    answer = intArrayOf(left,right)
                }
                sum -= sequence[left]
                left++
            }
        }

        return answer
    }
}