class Solution {
    fun solution(elements: IntArray): Int {
        var answer = mutableSetOf<Int>()
        val tempList = elements.toList() + elements.toList()
        var count = 0
        while(count < elements.size){
            for(i in 0 .. elements.size-1){
                var temp = 0
                for(j in 0 .. count){
                    temp += tempList[i+j]
                }
                answer.add(temp)
            }
            count++
        }
        return answer.size 
    }
}