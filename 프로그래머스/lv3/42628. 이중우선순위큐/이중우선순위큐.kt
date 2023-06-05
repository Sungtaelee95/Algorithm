class Solution {
    fun solution(operations: Array<String>): IntArray {
        var answer = intArrayOf()
        var tempList = mutableListOf<Int>()
        operations.forEach{
            if(it.split(" ")[0] == "I"){
                tempList.add(it.split(" ")[1].toInt())
            } else if(it.split(" ")[0] == "D" && it.split(" ")[1] == "1" && tempList.isNotEmpty()){
                tempList.remove(tempList.maxOrNull())
            } else if(it.split(" ")[0] == "D" && it.split(" ")[1] == "-1" && tempList.isNotEmpty()) {
                tempList.remove(tempList.minOrNull())
            }
        }
        if(tempList.isEmpty()){
            answer += 0
            answer += 0
        } else {
            answer += tempList.maxOrNull()!!
            answer += tempList.minOrNull()!!
        }
        return answer
    }
}