class Solution {
    fun solution(s: String): Int {
        var answer: Int = 0
        var tempMap = mutableMapOf<Char,Int>()
        s.forEach{
            if(tempMap.containsKey(it)){
                val newValue = tempMap.get(it)!!.toInt()+1
                tempMap.put(it,newValue)
            } else {
                tempMap.put(it,1)
            }
            val checkArray = tempMap.values.toIntArray()
            if(checkArray.first() * 2 == checkArray.sum()){
                answer += 1
                tempMap.clear()
            }
        }
        if(tempMap.isNotEmpty()){
            answer += 1
        }
        return answer
    }
}