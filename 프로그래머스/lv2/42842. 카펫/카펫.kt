class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = mutableListOf<Int>()
        var ylist = mutableListOf<Int>()
        for(i in 1 .. yellow){
            if(yellow % i == 0) ylist.add(i)
        }
        var temp = ylist.size-1
        for(i in 0 .. ylist.size-1){
            if( (ylist[i]+2) * (ylist[temp]+2) == brown+yellow){
                answer.add(ylist[temp]+2)
                answer.add(ylist[i]+2)
                break
            } else temp--
        }
        
        return answer.toIntArray()
    }
}