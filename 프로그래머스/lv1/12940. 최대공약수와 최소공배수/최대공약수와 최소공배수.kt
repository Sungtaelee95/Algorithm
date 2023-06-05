class Solution {
    fun solution(n: Int, m: Int): IntArray {
        var big = 0
        var min = 0
        var x : Int = 0
        var y : Int = 0
        if(n < m){
            big = m
            min = n
        } else {
            big = n
            min = m
        }
        
        //최대공약수 구하기
        for(i in min downTo 1){
            if( big % i == 0 && min % i == 0){
                x = i
                break;
            }
        }
        
        y = big * min / x
        
        var answer = intArrayOf(x,y)
        return answer
    }
}