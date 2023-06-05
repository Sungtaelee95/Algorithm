class Solution {
    fun solution(arr: IntArray, query: IntArray): IntArray {
        var answer = arr.toList()

        for(i in 0 .. query.size-1){
            if(i % 2 == 0){
                answer = answer.subList(0,query[i]+1)
            } else {
                answer = answer.subList(query[i], answer.size)
            }
        }

        return answer.toIntArray()
    }
}