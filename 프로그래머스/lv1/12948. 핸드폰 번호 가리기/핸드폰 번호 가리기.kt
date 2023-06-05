class Solution {
    fun solution(phone_number: String): String {
        var answer : String
        var star = ""
        for(i in 1..phone_number.length-4) {
            star += "*"
        }
        var number : String = phone_number.substring(star.length .. phone_number.length-1)
        answer = star + number
        return answer
    }
}