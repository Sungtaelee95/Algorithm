class Solution {
    fun solution(t: String, p: String): Int {
        var answer: Int = 0
        var tempString = ""
        t.forEach{
            tempString += it.toString()
            if(tempString.length == p.length){

                if(tempString.toLong() <= p.toLong()) answer++

                tempString = tempString.slice(1 .. tempString.length-1)   
            }
        }
        return answer
    }
}