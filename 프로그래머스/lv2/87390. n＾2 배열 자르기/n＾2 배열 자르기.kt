class Solution {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        var answer: IntArray = intArrayOf()
        var tempList = mutableListOf<Int>()
        var count = 0L
        for(i in 1 .. n){
            if(count >= left / n.toLong() && count <= right / n.toLong()){
                for(j in 0 .. i-1){
                    tempList.add(i)
                }
                for(k in i+1 .. n){
                 tempList.add(k)
                }
            }
            count += 1L    
        }
        //println(tempList)

        for(i in left % n.toLong() .. left % n.toLong() + (right-left) ){
            answer += tempList[i.toInt()]
        }


        return answer
    }
}