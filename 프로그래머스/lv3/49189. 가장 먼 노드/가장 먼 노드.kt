import java.util.ArrayDeque

class Solution {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        var len = IntArray(n+1){0} // 거리를 저장할 배열
        var node = Array(n+1,{ArrayList<Int>()})
        var visit = BooleanArray(n+1){true}
        edge.forEach {
            node[it[0]].add(it[1])
            node[it[1]].add(it[0])
        }

        var dq = ArrayDeque<Int>()
        dq.add(1)
        len[1] = 0

        var max = 0
        while(!dq.isEmpty()){
            var now = dq.pollFirst()
            visit[now] = false

            for(i in 0 .. node[now].size-1){
                if(visit[node[now][i]]){
                    visit[node[now][i]] = false
                    dq.addLast(node[now][i])
                    len[node[now][i]] = len[now]+1
                    max = len[now]+1
                }
            }

        }
        return len.count{e -> e == max}
    }
}