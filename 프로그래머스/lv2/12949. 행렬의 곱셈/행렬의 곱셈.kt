class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        var answer = Array(arr1.size,{i -> IntArray(arr2[0].size,{0})})
        
        for(i in 0 .. arr1.size-1){
            for(j in 0 .. arr2.size-1){
                for(k in 0 .. arr2[0].size-1){
                  answer[i][k] += arr1[i][j] * arr2[j][k]
                }
            }
        }
        
        return answer
    }
}