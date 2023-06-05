class Solution {
    fun solution(maps: Array<String>): Int {
        var answer: Int
        val arrX = intArrayOf(1,-1,0,0)
        val arrY = intArrayOf(0,0,1,-1)

        var mapCount = ArrayList<IntArray>()
        var visit = ArrayList<BooleanArray>()
        var dq = java.util.ArrayDeque<Node>()
        var start = Node(0,0)
        var end = Node(0,0)
        var leval = Node(0,0)
        for(i in 0 .. maps.size-1){
            mapCount.add(IntArray(maps[i].length){0})
            visit.add(BooleanArray(maps[i].length){true})
            for(j in 0 .. maps[i].length-1){
                if(maps[i][j] == 'S') start = Node(i,j)
                if(maps[i][j] == 'E') end = Node(i,j)
                if(maps[i][j] == 'L') leval = Node(i,j)
            }
        }

        dq.add(start)
        var startleval = -1
        while (!dq.isEmpty()) {
            val now = dq.pollFirst()
            val nowX = now.x
            val nowY = now.y
            visit[nowX][nowY] = false
            if (now.equals(leval) ) {
                startleval = mapCount[nowX][nowY]
                break
            }

            for(i in 0 .. 3){
                val rx = arrX[i]
                val ry = arrY[i]
                val nowValue = mapCount[nowX][nowY]

                if(nowX+rx < 0 || nowY+ry < 0 || nowX+rx >= maps.size || nowY+ry >= maps[0].length) continue

                if(!visit[nowX+rx][nowY+ry] || maps[nowX+rx][nowY+ry].equals('X')) continue

                if(mapCount[nowX+rx][nowY+ry] == 0) {
                    mapCount[nowX + rx][nowY + ry] = nowValue + 1

                    dq.addLast(Node(nowX + rx, nowY + ry))
                }
            }
        }


        dq.clear()

        dq.addFirst(leval)
        for(i in 0 .. visit.size-1){
            for(j in 0 .. visit[i].size-1){
                visit[i][j] = true
                mapCount[i][j] = 0
            }
        }

        var levalend = -1
        while (!dq.isEmpty()){
            val now = dq.pollFirst()
            val nowX = now.x
            val nowY = now.y
            visit[nowX][nowY] = false

            if (now.equals(end)) {
                levalend = mapCount[nowX][nowY]
                break
            }

            for(i in 0 .. 3){
                val rx = arrX[i]
                val ry = arrY[i]
                val nowValue = mapCount[nowX][nowY]

                if(nowX+rx < 0 || nowY+ry < 0 || nowX+rx >= maps.size || nowY+ry >= maps[0].length) continue

                if(!visit[nowX+rx][nowY+ry] || maps[nowX+rx][nowY+ry].equals('X')) continue

                if(mapCount[nowX+rx][nowY+ry] == 0) {
                    mapCount[nowX + rx][nowY + ry] = nowValue + 1

                    dq.addLast(Node(nowX + rx, nowY + ry))
                }
            }

        }

        answer = startleval + levalend
        if(startleval.equals(-1) || levalend.equals(-1)) {
            answer = -1
        }
        return answer
    }
}
data class Node(
    val x: Int,
    val y: Int
)