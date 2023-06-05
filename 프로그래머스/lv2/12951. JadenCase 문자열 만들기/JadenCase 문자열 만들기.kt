class Solution {
    fun solution(s: String): String {
        var answer = ""
        for(i in 0 .. s.length-1){
            if(s[i].toInt() in 0 .. 9 || s[i] == ' '){
                answer += s[i]
                continue
            } else if(i == 0) {
                answer += s[i].toUpperCase()
                continue
            } else if (s[i-1] == ' ') answer += s[i].toUpperCase()
            else answer += s[i].toLowerCase()
        }
        return answer
    }
}