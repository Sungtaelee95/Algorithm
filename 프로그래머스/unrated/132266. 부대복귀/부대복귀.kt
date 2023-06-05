import java.util.*
import kotlin.collections.ArrayList

class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        var answer = intArrayOf()
        var roadList = ArrayList<ArrayList<Int>>()

        repeat(n+1){
            roadList.add(ArrayList())
        }

        for(i in 0 .. roads.size-1){
            roadList[roads[i][0]].add(roads[i][1])
            roadList[roads[i][1]].add(roads[i][0])
        }

        var lenCount =  IntArray(n+1){-1}
        var dq = ArrayDeque<Int>()
        dq.addFirst(destination)
        lenCount[destination] = 0

        while(!dq.isEmpty()){
            val now = dq.pollFirst()
            for(nextRoad in roadList[now]){
                if(lenCount[nextRoad] == -1){
                    dq.addLast(nextRoad)
                    lenCount[nextRoad] = lenCount[now]+1
                }
            }
        }

        sources.forEach {
            answer += lenCount[it]
        }

        return answer
    }
}