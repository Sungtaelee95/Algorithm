class Solution {
    fun solution(n: Int): Int {
        var temp = arrayListOf(0,1)

        for(i in 2 .. n){
            temp.add( (temp[i-2] % 1234567 + temp[i-1] % 1234567) % 1234567 )
        }
        return temp.last()
    }
}
