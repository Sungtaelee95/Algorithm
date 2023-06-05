class Solution {
    fun solution(e: Int, starts: IntArray): IntArray {
        var answer = IntArray(starts.size)
        var countList = MutableList(e + 1) { 0 }
        var maxList = IntArray(e + 1) { it }

        for (i in 1 .. e) {
            for (j in 1 .. (e/i) ) {
                countList[i*j]++
            }
        }

        var maxValue = countList.last()
        var tempIndex = countList.size

        for(i in (countList.size-1) downTo 0){
           if(countList[i] < maxValue ){
               maxList[i] = tempIndex
           } else {
               maxValue = countList[i]
               tempIndex = i
               maxList[i] = tempIndex
           }
        }

        // println(countList)
        // println(maxList)

        for (i in starts.indices) {
            answer[i] = maxList[starts[i]]
        }

        return answer
    }
}