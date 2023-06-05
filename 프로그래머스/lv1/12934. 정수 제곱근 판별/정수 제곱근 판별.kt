class Solution {
   fun solution(n: Long): Long {
        for (i in 0..n) {
            if (i * i == n) {
                return ((i + 1) * (i + 1))
            }
        }
        return -1
    }
}