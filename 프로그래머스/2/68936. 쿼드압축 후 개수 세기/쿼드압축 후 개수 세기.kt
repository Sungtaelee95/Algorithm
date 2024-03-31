class Solution {
    var answer: IntArray = intArrayOf(0,0)
    
    fun solution(arr: Array<IntArray>): IntArray {
        quard(arr, 0, 0, arr.size)
        return answer
    }
    
    private fun quard(arr: Array<IntArray>, x: Int, y: Int, size: Int) {
        if (isZip(arr, x,y,size, arr[x][y])) {
            when (arr[x][y]) {
                0 -> answer[0]++
                1 -> answer[1]++
                else -> return
            }
            return
        }
        quard(arr,x,y, size/2);
        quard(arr,x,y + size/2, size/2);
        quard(arr,x+size/2,y, size/2);
        quard(arr,x+size/2,y + size/2, size/2);
    }
    
    private fun isZip(arr: Array<IntArray>, x: Int, y: Int, size: Int, vue: Int): Boolean {
        for (i in x until x+size) {
            for (j in y until y+size) {
                if (arr[i][j] != arr[x][y]) return false
            }
        }
        return true
    }
}