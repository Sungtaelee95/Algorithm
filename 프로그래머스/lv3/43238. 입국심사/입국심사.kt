class Solution {

    var N: Int? = null
    lateinit var Times: IntArray

    fun solution(n: Int, times: IntArray): Long {
        N = n
        Times = times
        var maxValue = 1000000000L * 1000000000L
        var minValue = 1L
        while(maxValue > minValue){
            var midValue = (maxValue + minValue)/2L
            if(check(midValue)){
                maxValue = midValue
            } else {
                minValue = midValue+1L
            }
        }
        return minValue
    }

    fun check(mid: Long):Boolean{
        var num = 0L
        Times.forEach{
            num += (mid / it.toLong())
        }
        return N!!.toLong() <= num
    }
}
