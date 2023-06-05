class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        var answer: Int = 0
        var checkMap = mutableMapOf<Int,Int>()
        tangerine.forEach{
            if(checkMap.containsKey(it)){
                checkMap.put(it, checkMap.get(it)!!+1)
            } else {
                checkMap.put(it,1)
            }
        }
        
        val countList = checkMap.values.toList().sorted().reversed()
        var count = 0
        for(i in 0 .. countList.size-1){
            if(count < k){
                count += countList[i]
                answer ++
            } else {
                break
            }
        }
        
        return answer
    }
}