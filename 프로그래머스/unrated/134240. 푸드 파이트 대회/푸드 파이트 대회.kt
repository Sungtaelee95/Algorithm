class Solution {
    fun solution(food: IntArray): String {
        var answer: String = ""
        for(i in 1 .. food.size-1){
            for(j in 1 .. food[i]/2){
                answer += i.toString()
            }
        }
        answer = answer + "0" + answer.reversed()
        return answer
    }
}