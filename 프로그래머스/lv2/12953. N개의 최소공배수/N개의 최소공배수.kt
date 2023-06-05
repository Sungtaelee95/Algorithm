class Solution {
    fun solution(arr: IntArray): Int {
        var answer = arr.minOrNull()!!
        var tempAnswer = answer
        var tempArr = arr.sorted()
        var count = 0
        while(count != tempArr.size){
            answer += tempAnswer
            for( i in 0 .. tempArr.size-1){
                if(answer % tempArr[i] == 0) count++
                else {
                    count = 0
                    break
                }
            }
            
        }
        return answer
    }
}