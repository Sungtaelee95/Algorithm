class Solution {
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 1
        var fir = a
        var sec = b
        while(true){
            if(fir % 2 != 0) fir++
            if(sec % 2 != 0) sec++
            if(fir == sec) break
            else{
                fir /= 2
                sec /= 2
                answer++
            }
        }
        return answer
    }
}