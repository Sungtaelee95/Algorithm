class Solution {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        var count = 0
        for(i in 0 .. lottos.size-1){//최저 맞추기
            if(win_nums.contains(lottos[i])) count++
        }
        when(count){
            0,1 -> answer.add(6)
            2 -> answer.add(5)
            3 -> answer.add(4)
            4 -> answer.add(3)
            5 -> answer.add(2)
            6 -> answer.add(1)
        }
        var bestCount = count + lottos.count{e -> e == 0}
         when(bestCount){
            0,1 -> answer.add(6)
            2 -> answer.add(5)
            3 -> answer.add(4)
            4 -> answer.add(3)
            5 -> answer.add(2)
            6 -> answer.add(1)
        }
        answer.reverse()
        return answer.toIntArray()
    }
}