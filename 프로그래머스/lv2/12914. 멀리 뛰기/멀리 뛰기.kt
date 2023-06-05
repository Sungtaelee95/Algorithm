class Solution {
    fun solution(n: Int): Long {
        var tempList = mutableListOf<Long>(0L,1L)
        
        for(i in 2 .. n+1){
            tempList.add((tempList[i-1]+tempList[i-2])%1234567L)
        }
        
        return tempList[n+1]
    }
}