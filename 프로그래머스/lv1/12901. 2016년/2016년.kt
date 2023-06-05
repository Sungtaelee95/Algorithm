class Solution {
    fun solution(a: Int, b: Int): String {
        var answer = ""
        val monDayList = listOf<Int>(31,29,31,30,31,30,31,31,30,31,30)
        var days = 0
        if(a != 1) {
            for(i in 1 .. a-1){
                days += monDayList[i-1]
            }
            days += b - 1
        } else days = b -1
        days = (days % 7)
        val result = arrayOf("FRI","SAT","SUN","MON","TUE","WED","THU")
        answer = result[days]
        return answer
    }
}