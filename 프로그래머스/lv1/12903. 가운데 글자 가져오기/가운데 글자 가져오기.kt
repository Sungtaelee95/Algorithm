class Solution {
    fun solution(s: String): String {
        var answer = ""
        if(s.length % 2 == 1) answer += s[s.length / 2]
        else {
            answer = s[s.length / 2 - 1].toString() + s[s.length / 2].toString()  
        }
        return answer
    }
}