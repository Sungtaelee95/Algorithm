class Solution {
    fun solution(absolutes: IntArray, signs: BooleanArray): Int {
        var answer: Int = 0
        var k: Int
        for (i in 0..absolutes.size-1){
            if (signs[i]==true) k=1 else k= -1
            answer += absolutes[i] * k
        }

        return answer
    }
}