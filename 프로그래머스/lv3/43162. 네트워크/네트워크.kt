import java.util.*
class Solution {
    
    private var nodeList = mutableListOf<MutableList<Int>>()
    private var visited = mutableListOf<Boolean>()
    private var answer = 0
    
    fun solution(n: Int, computers: Array<IntArray>): Int {
        nodeList.add(mutableListOf<Int>())
        visited.add(true)
        for(i in 0 .. n-1){
            var tempList = mutableListOf<Int>()
            for(j in 0 .. n-1){
                if(i == j) {
                    tempList.add(0)
                } else {
                    if(computers[i][j] != 0){
                        tempList.add(j+1)
                    } else {
                        tempList.add(0)
                    }
                }
            }
            nodeList.add(tempList)
            visited.add(true)
        }

        for(i in 1 .. n){
            if(visited[i]){
                dfs(i)
                answer++
            }
        }
        return answer
    }

    private fun dfs(i : Int){
        visited[i] = false
        for(j in 0 .. nodeList[i].size-1){
            if(nodeList[i][j] != 0 && visited[nodeList[i][j]]){
                dfs(nodeList[i][j])
            }
        }
    }
}