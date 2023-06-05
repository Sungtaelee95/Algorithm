import java.lang.Math.abs

class Solution {
    fun solution(a: Int, b: Int, c: Int, d: Int): Int {
        var answer: Int = 0
        var arr = IntArray(7){0}
        arr[a]++
        arr[b]++
        arr[c]++
        arr[d]++
        var max = arr.maxOrNull()

        when(max){

            4 -> answer = 1111*a
            3 -> {
                val p = arr.indexOf(3)
                val q = arr.indexOf(1)
                answer = ((10*p)+q) * ((10*p)+q)
            }

            2 -> {
                var p = 0
                var q = 0
                for(i in 1 .. arr.size-1){
                    if(arr[i] == 2){
                        if(p == 0) p = i
                        else q = i
                    }
                }
                if(q != 0) answer = (p+q) * abs(p-q)
                
                else {
                    var r = 0
                    for(i in 1 .. arr.size-1){
                        if(arr[i] == 1){
                            if(q == 0 )q = i
                            else r = i
                        }
                    }
                    answer = q*r
                }
            }

            1 -> {
                answer = 0
                for(i in 1 .. arr.size-1){
                    if(arr[i] != 0) {
                        answer = i
                        break
                    }
                }
            }

        }

        return answer
    }
}