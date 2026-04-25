class Solution {
    fun solution(gems: Array<String>): IntArray {
        var answer = intArrayOf(1,1_000_000)
        val size = gems.toSet().size

        val map = HashMap<String, Int>()
        
        var min = gems.size+1
        
        var start = 0
        var end = 0

        var left = 0
        var right = 0

        while (right < gems.size) {
            if (map.containsKey(gems[right])) {
                map[gems[right]] = map[gems[right]]!! + 1
            } else {
                map[gems[right]] = 1
            }
            right++
            while (map.size == size) {
                if (right - left < min) {
                    min = right - left
                    start = left + 1
                    end = right
                }
                val cnt = map[gems[left]]!! - 1
                if (cnt == 0) {
                    map.remove(gems[left])
                } else {
                    map[gems[left]] = map[gems[left]]!! - 1
                }
                left++
            }
            
        }

        return intArrayOf(start, end)
    }
}