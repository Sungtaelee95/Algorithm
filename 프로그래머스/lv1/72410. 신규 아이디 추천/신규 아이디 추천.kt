class Solution {
    fun solution(new_id: String): String {
        var first : String = new_id.toLowerCase()
        var answer: String = ""
        for(i in 0 .. first.length - 1){
            if(first[i].toChar() in 'a' .. 'z' ) answer +=  first[i] 
            if(first[i].toChar() in '0' .. '9' ) answer +=  first[i]
            if(first[i].toString() == "-" || first[i].toString() =="_" || 
               first[i].toString() ==".") answer +=  first[i]
        } //1~2 단계 실행
        
        var second = answer
        var secondSize = answer.length
        answer = "" // 공백 초기화
        for(i in 0 .. second.length-2 ){
            if(second[i+1] == '.' && second[i] == '.') {
                answer += ""
            } else answer += second[i]
        } 
        answer +=  second[secondSize-1]   
        //3 단계 완료
        
        for(i in 0 .. answer.length -1) {
            when(i){
                0 -> if(answer[i].toChar() == '.') answer = answer.substring(1,answer.length)
                answer.length -1 -> if(answer[i].toChar() == '.') answer = answer.substring(0,answer.length - 1)
                else -> continue
            }
        } 
        //4 단계 완료
        
        if(answer == "") answer += "a"
        //5 단계 완료
          
        if(answer.length >= 16){
            answer = answer.substring(0, 15)
            if(answer.last().toChar() == '.') answer = answer.substring(0, 14)
        }
        //6 단계 완료
        
        while(answer.length <= 2 ) {
            answer += answer.last().toString()
        }
        //7 단계 완료
        return answer
    }
}