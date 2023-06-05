class Solution {
    fun solution(s: String): String {
        val temp = s.split(" ")
        var temp2 = mutableListOf<Int>()
        for(i in temp){
            temp2.add(i.toInt())
        }
        val answer = temp2.minOrNull().toString() + " " + temp2.maxOrNull().toString()
        return answer
    }
}