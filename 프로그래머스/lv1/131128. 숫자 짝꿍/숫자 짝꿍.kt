class Solution {
    fun solution(X: String, Y: String): String {
        var answer = StringBuilder()
        val xList = X.toList()
        val yList = Y.toList()
        for( check in 9 downTo 0){
            val checkCount = if(xList.count{e -> e.toString() == check.toString()} >= 
                    yList.count{e -> e.toString() == check.toString()}) 
                    yList.count{e -> e.toString() == check.toString()} 
                    else xList.count{e -> e.toString() == check.toString()}
            for(i in 1 .. checkCount){
                answer.append(check.toString())   
            }
        }
        if(answer.toString() == "") return "-1"
        if(answer.length == answer.count{e -> e == '0'}) return "0"
        
        
        return answer.toString()
    }
}