class Solution {
    var answer = arrayOf<IntArray>()
    fun solution(n: Int): Array<IntArray> {
        hanoi(n,1,2,3)
        return answer
    }
    fun hanoi(n:Int, s: Int, m: Int, e: Int) {
        if (n == 0) return
        hanoi(n-1, s,e,m)
        answer += intArrayOf(s,e)
        hanoi(n-1, m,s,e)
    }
}