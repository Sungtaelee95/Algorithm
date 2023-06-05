class Solution {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0
        var boardList = board.toMutableList()
        var scoreList = mutableListOf<Int>()
        /*
        [[0,0,0,0,0],
         [0,0,1,0,3],
         [0,2,5,0,1],
         [4,2,4,4,2],
         [3,5,1,3,1]] */
        for(i in 0 .. moves.size-1){
            var tempList = mutableListOf<Int>()
            for(j in 0 .. boardList.size-1){
                if(boardList[j][moves[i]-1]==0) continue
                else if(boardList[j][moves[i]-1]!=0){
                    tempList.add(boardList[j][moves[i]-1])
                    boardList[j].set(moves[i]-1,0)
                    break
                }
            }
            if(tempList.isEmpty()) continue
            scoreList.add(tempList[0])
        }
        var count = scoreList.size / 2
        while(count > 0){
            for(i in 0 .. scoreList.size-1){
                if(i == scoreList.size-1) continue
                else if(scoreList[i] == scoreList[i+1]){
                    answer += 2
                    var temp1 = mutableListOf<Int>()
                    if(i != 0){
                        for(j in 0 .. i-1){
                            temp1.add(scoreList[j])
                        }
                    }
                    if( i+1 != scoreList.size-1){
                        for(j in i+2 .. scoreList.size-1){
                            temp1.add(scoreList[j])
                        }
                    }
                    scoreList = temp1
                    break
                } 
            }
            count--
        }
        return answer
    }
}