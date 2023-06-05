class Solution {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        var answer: Int = 0
        var scoreSort = score.toMutableList().sortedDescending()
        var count = 0
        while(count+m <= scoreSort.size){
            val tempList = mutableListOf<Int>()
            for(i in count .. count+m-1){
                tempList.add(scoreSort[i])
            }
            count += m
            answer += tempList.minOrNull()!! * m
        }
        
        return answer
    }
}