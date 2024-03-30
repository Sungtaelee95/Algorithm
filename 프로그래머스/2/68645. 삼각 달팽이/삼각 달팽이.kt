class Solution {
    fun solution(n: Int): IntArray {
        var answer = mutableListOf<Int>()
        val result = mutableListOf<IntArray>()
        
        repeat(n) {
            result.add(IntArray(it+1))
        }
        
        var row = 0
        var rowMin = 0

        var col = 0
        var colMin = 0
        
        var count = n // 작성할 부분
        
        var number = 1
        while (number <= n * (n+1) / 2) {
            if (row == rowMin && col == colMin) {
                repeat(count) {
                    result[row][col] = number
                    number++
                    row++
                }
                count--
                col++
                row--
                repeat(count) {
                    result[row][col] = number
                    number++
                    col++
                }
                col--
                count--
                rowMin++
                colMin++
                continue
            }
            repeat(count) {
                row--
                col--
                result[row][col] = number
                number++
            }
            count--
            row++
            rowMin++        
        }
        result.forEach{
            it.forEach{
                answer.add(it)
            }
        }
        
        return answer.toIntArray()
    }
}