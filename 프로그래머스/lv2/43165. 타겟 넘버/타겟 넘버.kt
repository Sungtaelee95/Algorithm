class Solution {
    fun solution(numbers: IntArray, target: Int): Int {
        var answer = 0
        val one = make(numbers.size)
        
        for(i in 0 .. one.size-1){
            var sum = 0
            for(j in 0 .. numbers.size-1){
                sum += one[i][j] * numbers[j]
            }
            if(sum == target){
                answer++
            }
        }
        
        return answer
    }
    
    fun make(n : Int): MutableList<MutableList<Int>> {
        var returnList = mutableListOf<MutableList<Int>>()
        if(n == 1){
            var tempList1 = mutableListOf<Int>()
            var tempList2 = mutableListOf<Int>()
            tempList1.add(1)
            tempList2.add(-1)
            returnList.add(tempList1)
            returnList.add(tempList2)
            return returnList
        } else {
            val tempList = make(n-1)
            for( i in 0 .. tempList.size-1){
                var temp =  tempList[i].plus(-1).toMutableList()
                returnList.add(temp)
            }
            for( i in 0 .. tempList.size-1){
                var temp =  tempList[i].plus(1).toMutableList()
                returnList.add(temp)
            }  
        }
        return returnList
    }
    
}