class Solution {
    fun solution(board: Array<String>): Int {
        var answer: Int = 1
        var oCount = 0
        var xCount = 0

        board.forEach{
            val check = it.toCharArray()
            check.forEach{
                if(it == 'O') oCount++
                if(it == 'X') xCount++
            }
        }

        if(oCount < xCount){
            answer = 0
        } else if(oCount - xCount > 1){
            answer = 0
        } else if(xWin(board) && oWin(board)){
            answer = 0
        } else if(xWin(board) && (xCount < oCount)){
            answer = 0
        } else if(oWin(board) && (xCount == oCount)){
            answer = 0
        }

        return answer
    }

    fun xWin(board: Array<String>): Boolean{
        //가로 완성이 되어있는지 확인
        var check1 = false
        for(i in 0 .. board.size-1){
            if(board[i] == "XXX"){
                check1 = true
                break
            }
        }
        if(check1) return check1
        else {
            //세로 완성이 되어있는지 확인
            var check2 = false

            var one = ""
            var two = ""
            var three = ""
            for(i in 0 .. board.size-1){
                one += board[i][0]
                two += board[i][1]
                three += board[i][2]
            }

            if(one == "XXX"){
                check2 = true
            }
            if(two == "XXX"){
                check2 = true
            }
            if(three == "XXX"){
                check2 = true
            }
            if(check2) return check2
            else {
                //대각선으로 완성이 되어있는지 확인
                var check3 = false

                var count = 0
                var temp = ""
                for(i in 0 .. board.size-1){
                    temp += board[i][count]
                    count++
                }
                if(temp == "XXX"){
                    check3 = true
                }
                if(check3) return check3
                else{
                    // 반대 대각선 체크
                    var count = 2
                    var temp = ""
                    for(i in 0 .. board.size-1){
                        temp += board[i][count]
                        count--
                    }
                    if(temp == "XXX"){
                        check3 = true
                    }
                    if(check3) return check3
                }
            }
        }
        return false
    }

    fun oWin(board: Array<String>): Boolean{
        //가로 완성이 되어있는지 확인
        var check1 = false
        for(i in 0 .. board.size-1){
            if(board[i] == "OOO"){
                check1 = true
                break
            }
        }
        if(check1) return check1
        else {
            //세로 완성이 되어있는지 확인
            var check2 = false

            var one = ""
            var two = ""
            var three = ""
            for(i in 0 .. board.size-1){
                one += board[i][0]
                two += board[i][1]
                three += board[i][2]
            }

            if(one == "OOO"){
                check2 = true
            }
            if(two == "OOO"){
                check2 = true
            }
            if(three == "OOO"){
                check2 = true
            }
            if(check2) return check2
            else {
                //대각선으로 완성이 되어있는지 확인
                var check3 = false

                var count = 0
                var temp = ""
                for(i in 0 .. board.size-1){
                    temp += board[i][count]
                    count++
                }
                if(temp == "OOO"){
                    check3 = true
                }
                if(check3) return check3
                else{
                    // 반대 대각선 체크
                    var count = 2
                    var temp = ""
                    for(i in 0 .. board.size-1){
                        temp += board[i][count]
                        count--
                    }
                    if(temp == "OOO"){
                        check3 = true
                    }
                    if(check3) return check3
                }
            }
        }
        return false
    }
}