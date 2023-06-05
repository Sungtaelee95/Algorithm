import java.util.ArrayDeque

class Solution {
    fun solution(board: Array<String>): Int {
        var answer: Int = Int.MAX_VALUE
        var ar = intArrayOf(0,0,1,-1)
        var ac = intArrayOf(1,-1,0,0)
        var visit = ArrayList<BooleanArray>()
        var count = ArrayList<IntArray>()
        var start = Info(0,0)
        var end = Info(0,0)

        for(i in 0 .. board.size-1){
            var tempvi = BooleanArray(board[i].length){true}
            var tempcn = IntArray(board[i].length){Int.MAX_VALUE}
            for(j in 0 .. board[i].length-1){
                if(board[i][j] == 'R'){
                    start = Info(i,j)
                    tempcn[j] = 0
                }
                if(board[i][j] == 'G'){
                    end = Info(i,j)
                }
                if(board[i][j] == 'D'){
                    tempvi[j] = false
                }
            }
            visit.add(tempvi)
            count.add(tempcn)
        }

        var history = ArrayList<Info>()

        var dq = ArrayDeque<Info>()
        dq.add(start)

        while(!dq.isEmpty()){
            val now = dq.pollFirst()
            val row = now.row
            val col = now.col
            if(now.equals(end)){
                answer = Math.min(answer,count[row][col])
            }
            for(i in 0 .. 3){
                var nr = row
                var nc = col
                while(true){
                    nr += ar[i]
                    nc += ac[i]
                    if(nr < 0 || nc < 0 || nr >= board.size || nc >= board[0].length) break
                    if(board[nr][nc] == 'D') break
                }
                nr -= ar[i]
                nc -= ac[i]
                if(history.contains(Info(nr,nc))) continue
                dq.addLast(Info(nr,nc))
                history.add(Info(nr,nc))
                count[nr][nc] = Math.min(count[row][col]+1, count[nr][nc])
            }
        }
        if(answer == Int.MAX_VALUE) answer = -1
        return answer
    }

    data class Info(
        val row: Int,
        val col: Int
    )
}