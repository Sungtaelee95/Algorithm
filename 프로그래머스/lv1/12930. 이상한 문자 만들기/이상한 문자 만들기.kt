class Solution {
    fun solution(s: String): String {
        var crazyString = s.split(" ")
        var answer = ""
        for( i in 0 .. crazyString.size - 1){
            for( j in 0 .. crazyString[i].length - 1){
                if( j % 2 == 0) answer += crazyString[i][j].uppercase()
                else answer += crazyString[i][j].lowercase()
            }
            if( i < crazyString.size - 1 ) answer += " "
        }

        return answer
    }
}
