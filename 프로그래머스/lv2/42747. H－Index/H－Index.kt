class Solution {
    fun solution(citations: IntArray): Int {
        var answer = 0
        val tempCitations = citations.sortedDescending()
        for(i in tempCitations.maxOrNull()!! downTo 1){
            if(tempCitations.count{e -> e >= i} >= i && tempCitations.count{e -> e <= i} <= i ){
                answer = i
                break
            }
        } 
        return answer
    }
}