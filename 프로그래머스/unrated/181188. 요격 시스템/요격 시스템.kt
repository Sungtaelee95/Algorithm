class Solution {
    fun solution(targets: Array<IntArray>): Int {
        var answer: Int = 0
        var target = targets.sortedWith(kotlin.Comparator { o1, o2 -> if(o1[1] == o2[1]) o1[0].compareTo(o2[0])
        else o1[1].compareTo(o2[1])})

        var check = target[0][1]
        answer++

        for(i in 1 .. target.size-1){
            if(target[i][0] >= check){
                answer++
                check = target[i][1]
            }
        }

        return answer
    }
}