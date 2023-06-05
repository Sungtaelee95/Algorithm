class Solution {
    fun solution(s: String): Boolean {
        var answer = (s.length == 4 || s.length == 6 ) && s.toIntOrNull() != null

        return answer
    }
}