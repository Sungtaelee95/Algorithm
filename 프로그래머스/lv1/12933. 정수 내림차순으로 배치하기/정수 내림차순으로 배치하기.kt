class Solution {
    fun solution(n: Long): Long {
        var answer: String = ""
        var stringN : String = n.toString()
        var sortN = IntArray(stringN.length,{0})

        for(i in 0 .. stringN.length-1){
            sortN[i] = stringN[i].toInt()-48
        }
        sortN.sortDescending()
        for(i in 0 .. sortN.size-1){
            answer += sortN[i].toString()
        }

        return answer.toLong()
    }
}