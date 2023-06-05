class Solution {
    fun solution(arr: IntArray): IntArray {
        var space = IntArray(1,{-1})
        if(arr.size == 1) return space

        var min = arr.minOrNull()
        var minOutList = arr.toMutableList()
        minOutList.remove(min)


        return minOutList.toIntArray()
    }
}