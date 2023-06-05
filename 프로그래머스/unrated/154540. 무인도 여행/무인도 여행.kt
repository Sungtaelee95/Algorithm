import java.util.*
import kotlin.collections.ArrayList

class Solution {

    fun solution(maps: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        var visit = java.util.ArrayList<BooleanArray>(maps.size)
        var dq = java.util.ArrayDeque<Info>()
        var map2 = ArrayList<IntArray>(maps.size)
        val arrx = intArrayOf(1,-1,0,0)
        val arry = intArrayOf(0,0,1,-1)

        for(i in 0 .. maps.size-1){
            var tempBool = BooleanArray(maps[i].length){true}
            var tempInt = IntArray(maps[i].length){0}
            for(j in 0 .. maps[i].length-1){
                if(maps[i][j] == 'X'){
                    tempBool[j] = false
                } else {
                    tempInt[j] = maps[i][j].code-48
                }
            }
            visit.add(tempBool)
            map2.add(tempInt)
        }
        var tempCheck = false
        for(y in 0 .. map2.size-1){
            for(x in 0 .. map2[y].size-1){
                if(map2[y][x] != 0){
                    dq.add(Info(x,y))
                    tempCheck = true
                    break
                }
            }
            if(tempCheck) break
        }
        if(dq.isEmpty()) return IntArray(1){-1}

        var sum = 0

        while(!dq.isEmpty()){
            val now = dq.pollFirst()
            val nx = now.x
            val ny = now.y
            visit[ny][nx] = false
            sum += map2[ny][nx]

            for(i in 0 .. 3){
                val mx = nx + arrx[i]
                val my = ny + arry[i]

                if(mx < 0 || my < 0 || mx >= map2[0].size || my >= map2.size) continue
                if(map2[my][mx] == 0 )continue
                if(!visit[my][mx])continue
                dq.addLast(Info(mx,my))
                visit[my][mx] = false
            }

            if(dq.isEmpty()) {
                answer += sum
                sum = 0
                var tempCheck2 = false
                for (y in 0..map2.size - 1) {
                    for (x in 0..map2[y].size - 1) {
                        if (map2[y][x] != 0 && visit[y][x]) {
                            dq.add(Info(x, y))
                            tempCheck2 = true
                            break
                        }
                    }
                    if (tempCheck2) break
                }

            }
        }

        answer.sort()

        return answer

    }
}

data class Info(
    val x: Int,
    val y: Int,
)