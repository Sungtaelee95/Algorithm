class Solution {
    fun solution(sequence: IntArray): Long {
        var answer: Long = 0

        var arr1 = LongArray(sequence.size){0L}
        var arr2 = LongArray(sequence.size){0L}
        var reverse = 1
        for(i in 0 .. sequence.size-1){
            arr1[i] = (sequence[i]*reverse).toLong()
            arr2[i] = (sequence[i]*reverse*-1).toLong()
            reverse *= -1
        }

        var temp = 0L
        var temp2 = 0L

        for(i in 0 .. sequence.size-1){
            temp += arr1[i]
            temp2 += arr2[i]

            if(temp < 0)temp = 0
            if(temp2 < 0)temp2 = 0

            answer = Math.max(answer,Math.max(temp,temp2))
        }


        return answer
    }
}