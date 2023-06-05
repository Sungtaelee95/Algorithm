class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        var answer: String = ""
        s.forEach{
            var check = it.toInt()
            var count = 0
            while(count != index){
                count++
                if(check + 1 > 122){
                    check = 97
                } else {
                    check++
                }
                while(skip.contains(check.toChar())){
                    check++
                    if(check > 122){
                        check = 97
                    }
                }
            }
            answer += check.toChar().toString()
        }
        
        return answer
    }
}