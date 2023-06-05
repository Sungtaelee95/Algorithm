class Solution {
    fun solution(n: Long): IntArray {
        var answer = n.toString()
        var reverseN = IntArray(answer.length,{0})
        for(i in 0 .. reverseN.size-1){
            reverseN[reverseN.size-1-i] = answer[i].toInt()-48
        }
        
        return reverseN
    }
}