class Solution {
    fun solution(k: Int, score: IntArray): IntArray {
        var answer: IntArray = intArrayOf()
        var tempList = mutableListOf<Int>()
        score.forEach{
            tempList.add(it)
            if(tempList.size < k){
                tempList.sortDescending()
                answer += tempList.last()
            } else {
                tempList.sortDescending()
                answer += tempList[k-1]
            }
        }
        return answer
    }
}