class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        //가로세로의 길이중 가장 긴것과
        //가로세로의 길이중 짧은것들만 비교하여 짧은것중에서 가장 긴거.
        var answer: Int = 0
        var maxList = mutableListOf<Int>()
        var minList = mutableListOf<Int>()
        for(i in 0 .. sizes.size-1){
            maxList.add(sizes[i].maxOrNull()!!)
            minList.add(sizes[i].minOrNull()!!)
        }
        answer = maxList.maxOrNull()!! * minList.maxOrNull()!!
        return answer
    }
}