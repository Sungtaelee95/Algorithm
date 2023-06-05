class Solution {
    fun solution(price: Int, money: Int, count: Int): Long {
        var answer: Long = -1
        var usePrice: Long = 0
        for( i in 0 .. count){
            usePrice += price.toLong() * i.toLong()
        }
        if(money - usePrice < 0) answer = -1L * (money - usePrice) else return 0
        
        return answer
    }
}