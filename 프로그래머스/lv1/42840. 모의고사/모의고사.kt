class Solution {
    fun solution(answers: IntArray): IntArray {
        var supoza1 = intArrayOf(1, 2, 3, 4, 5)
        var supoza2 = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5)
        var supoza3 = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
        var answer = mutableListOf<Int>()
        var score = mutableListOf(0,0,0)
        for(i in 0 .. answers.size - 1){
            if(supoza1[i % 5] == answers[i]) score[0] += 1
            if(supoza2[i % 8] == answers[i]) score[1] += 1
            if(supoza3[i % 10] == answers[i]) score[2] += 1
        }
        var maxScore = score.maxOrNull()
        for(i in 0 .. 2){
            if(score[i] == maxScore) answer.add(i+1)
        }
        print(score[0])
        return answer.toIntArray()
    }
}