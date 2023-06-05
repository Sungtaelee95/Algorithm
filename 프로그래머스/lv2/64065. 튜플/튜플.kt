class Solution {
    fun solution(s: String): IntArray {
        var answer = intArrayOf()
        val tempString = s.replace("{","").replace("}","").split(",")
        val tempSet = tempString.toSet()
        var tempMap = mutableMapOf<Int,Int>()
        for(temp in tempSet){
            tempMap.put(tempString.count{e-> e == temp},temp.toInt())
        }
        for(i in tempMap.size downTo 1){
            answer += tempMap.get(i)!!
        }
        
        
        return answer
    }
}