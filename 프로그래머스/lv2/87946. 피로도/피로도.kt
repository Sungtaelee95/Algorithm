import java.lang.Math.max

class Solution {

    lateinit var visit : BooleanArray
    var answer: Int = -1
    lateinit var dungeon : Array<IntArray>
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        visit = BooleanArray(dungeons.size){true}
        dungeon = dungeons
        for(i in 0 .. dungeons.size-1){
            if(visit[i] && k-dungeon[i][0] >= 0){
                dfs(k-dungeon[i][1],i,1)
            }
            visit[i] = true
        }

        return answer
    }

    fun dfs(hp:Int,now:Int,count:Int){
        visit[now] = false
        answer = max(answer,count)
        for(i in 0 .. dungeon.size-1){
            if(visit[i] && hp-dungeon[i][0] >= 0){
                dfs(hp-dungeon[i][1],i,count+1)
            }
        }
        visit[now] = true
    }
}