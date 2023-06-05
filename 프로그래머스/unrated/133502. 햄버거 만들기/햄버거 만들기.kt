class Solution {
    fun solution(ingredient: IntArray): Int {
        var answer: Int = 0
        var temp = ingredient.toMutableList()
        var check = 0
        while(check+3 <= temp.size){
           if(temp[check] == 1 && temp[check+1] == 2 && temp[check+2] == 3 && temp[check+3] == 1){
                answer++
                temp.removeAt(check+3)
                temp.removeAt(check+2)
                temp.removeAt(check+1)
                temp.removeAt(check)
                if(check >= 3) {
                    check -= 3
                } else {
                    check = 0
                }
           } else {
                check++
           } 
        }
        return answer
    }
}