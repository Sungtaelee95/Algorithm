class Solution {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        var answer = IntArray(commands.size, {0})
        for(i in 0 .. commands.size-1) {
            var tempArray = IntArray(commands[i][1].toInt()-commands[i][0]+1.toInt(), {0})
            var k = 0
            for(j in commands[i][0]-1 .. commands[i][1]-1) {
                tempArray[k] = array[j]
                k += 1
            }
            tempArray.sort()
            answer[i] = tempArray[commands[i][2]-1]
         }

        return answer
    }
}