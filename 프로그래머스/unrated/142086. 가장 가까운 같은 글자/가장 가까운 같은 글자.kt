class Solution {
    fun solution(s: String): IntArray {
        var answer: IntArray = intArrayOf()
        var checkList = mutableListOf<Char>()
        s.forEach{
            if(checkList.contains(it)){
                for(i in checkList.size-1 downTo 0){
                    if(checkList[i] == it){
                        answer += checkList.size-i
                        checkList.add(it)
                        break
                    }
                }
            } else {
                checkList.add(it)
                answer += -1
            }
        }
        return answer
    }
}