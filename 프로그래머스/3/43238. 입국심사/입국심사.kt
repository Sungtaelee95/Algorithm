class Solution {
    val max = 1_000_000_000L * 1_000_000_000L
    var N: Long = 0L
    fun solution(n: Int, times: IntArray): Long {
        N = n.toLong()
        var left = 1L
        var right = max
        while (left < right) {
            val mid = (left + right) / 2L
            if (isPossible(mid, times)) {
                right = mid
            } else {
                left = mid+1
            }
        }
        return left
    }
    
    fun isPossible(answer: Long, times: IntArray): Boolean {
        var n = N
        times.forEach { 
            n -= answer / it
        }
        return n <= 0
    }
}