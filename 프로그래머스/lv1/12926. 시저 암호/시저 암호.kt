class Solution {
    fun solution(s: String, n: Int): String {
        var answer = ""
        var result = 0
        for( i in 0 .. s.length-1){
            var result : Int = s[i].toInt() + n
            if(s[i].toInt() in 65 .. 90 ){
                while ( result > 90){
                    result -= 26
                }
                answer += result.toChar().toString()

            } else if(s[i].toInt() in 97 .. 122){
                 while ( result > 122){
                    result -= 26
                }
                answer += result.toChar().toString()

            } else answer += " "
        }
        return answer
    }
}