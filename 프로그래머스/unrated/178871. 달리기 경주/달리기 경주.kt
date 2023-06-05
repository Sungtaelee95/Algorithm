class Solution {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        var answer= Array<String>(players.size,{""})
        var rank = HashMap<Int,String>() // 순위 별 선수 저장
        var player = HashMap<String,Int>() // 선수 별 순위 저장

        for(i in 0 .. players.size-1){
            rank[i] = players[i]
            player[players[i]] = i
        }

        callings.forEach {
            val winer = it // 불린 선수의 이름
            val winerrank = player[it]!!-1 // 불린 선수의 변경된 순위

            val loser = rank[winerrank]!! // 역전당한 선수이름
            val loserrank = winerrank+1 // 역전 당한 선수의 변경된 순위

            player[winer] = winerrank
            rank[winerrank] = winer

            player[loser] = loserrank
            rank[loserrank] = loser
        }
        rank.forEach {
            answer[it.key] = it.value
        }
        
        return answer
    }
}