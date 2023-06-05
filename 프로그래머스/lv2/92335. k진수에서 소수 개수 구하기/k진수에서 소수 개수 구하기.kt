import kotlin.math.*
class Solution {
    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        val temp = n.toString(k).split('0')
        temp.forEach{
            if(it.isNotEmpty() && it != "1"){
                var chk1 = sqrt(it.toDouble())
                if(it.toLong() == 2L) {
                    answer++
                } else {
                    var count = 0
                    for(j in 2 .. chk1.toInt()+1){
                        if(it.toLong() % j.toLong() == 0L) count++
                    }
                    if(count == 0) answer++
                }
            }
        }
        return answer
    }
}