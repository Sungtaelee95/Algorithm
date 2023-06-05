class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""
        var list = mutableListOf<String>("RT", "TR", "CF", "FC", "JM", "MJ", "AN", "NA")
        var score = arrayOf(3,2,1,0,1,2,3)
        var finalScore = arrayOf(0,0,0,0,0,0,0,0)
        for(i in 0 .. survey.size-1){
            var temp = list.indexOf(survey[i]) //list에서 몇번째인지
            if(choices[i] > 4) temp = list.indexOf(survey[i][1].toString() + survey[i][0].toString()) 
            // 5~7번 선택시 변경
            if(choices[i] == 4) continue 
            finalScore[temp] += score[choices[i]-1]
        }
        if(finalScore[0] >= finalScore[1]) answer += "R"
        else answer += "T"
        if(finalScore[2] >= finalScore[3]) answer += "C"
        else answer += "F"
        if(finalScore[4] >= finalScore[5]) answer += "J"
        else answer += "M"
        if(finalScore[6] >= finalScore[7]) answer += "A"
        else answer += "N"
        
        return answer
    }
}