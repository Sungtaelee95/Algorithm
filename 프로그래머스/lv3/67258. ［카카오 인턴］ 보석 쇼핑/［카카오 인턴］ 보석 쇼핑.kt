class Solution {
    fun solution(gems: Array<String>): IntArray {
        var answer = intArrayOf()
        var map = HashMap<String,Int>()
        gems.forEach {
            map.put(it,0)
        }
        val max = map.size
        map = HashMap<String,Int>()

        var left = 0
        var right = 0
        var min = gems.size

        while(right < gems.size){
            if(map.containsKey(gems[right])){
                map[gems[right]] = map[gems[right]]!! + 1
            } else {
                map[gems[right]] = 1
            }
            right++

            while(map.size == max){
                map[gems[left]] = map[gems[left]]!!-1
                if(map[gems[left]] == 0){
                    map.remove(gems[left])
                }
                left++
                if(right-left < min){
                    min = right - left
                    answer = intArrayOf(left,right)
                }
            }
        }

        return answer
    }
}