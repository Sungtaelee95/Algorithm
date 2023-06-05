import kotlin.math.*
class Solution {
    fun solution(numbers: IntArray, hand: String): String {
        var answer = ""
        val pinger = hand[0].toString().toUpperCase()
        val keyPad = arrayOf(arrayOf(0,0),arrayOf(0,1),arrayOf(0,2),
                             arrayOf(1,0),arrayOf(1,1),arrayOf(1,2),
                             arrayOf(2,0),arrayOf(2,1),arrayOf(2,2),
                                          arrayOf(3,1)) // 키패드 모양과 동일하게.....ㅎ
        var startLeft = mutableListOf(3,0)
        var startRight  = mutableListOf(3,2)
        
        for(i in 0 .. numbers.size-1){
            
            when(numbers[i]){
                1,4,7 -> {
                    answer += "L"
                    startLeft[0] = keyPad[numbers[i]-1][0]
                    startLeft[1] = keyPad[numbers[i]-1][1]
                }
                3,6,9 -> {
                    answer += "R"
                    startRight[0] = keyPad[numbers[i]-1][0]
                    startRight[1] = keyPad[numbers[i]-1][1]
                }
                2,5,8 -> {
                    var temp1 = keyPad[numbers[i]-1] // 누르려는 버튼위치
                    val temp2 = abs(temp1[0]-startRight[0]) + abs(temp1[1]-startRight[1])
                    val temp3 = abs(temp1[0]-startLeft[0]) + abs(temp1[1]-startLeft[1])
                    
                    val btnRightLen =  temp2
                    val btnLeftLen  =  temp3
                    
                    if(btnRightLen < btnLeftLen){
                        answer += "R"
                        startRight[0] = keyPad[numbers[i]-1][0]
                        startRight[1] = keyPad[numbers[i]-1][1]
                    } 
                    if(btnRightLen > btnLeftLen){
                        answer += "L"
                        startLeft[0] = keyPad[numbers[i]-1][0]
                        startLeft[1] = keyPad[numbers[i]-1][1]
                    } 
                    if(btnRightLen == btnLeftLen){
                        answer += pinger
                        if(pinger == "R"){
                            startRight[0] = keyPad[numbers[i]-1][0]
                            startRight[1] = keyPad[numbers[i]-1][1]
                        }else{
                            startLeft[0] = keyPad[numbers[i]-1][0]
                            startLeft[1] = keyPad[numbers[i]-1][1]
                        } 
                    }
                }
                
                0 -> {
                    var temp1 = keyPad[9] // 누르려는 버튼위치
                    val temp2 = abs(temp1[0]-startRight[0]) + abs(temp1[1]-startRight[1])
                    val temp3 = abs(temp1[0]-startLeft[0]) + abs(temp1[1]-startLeft[1])
                    val btnRightLen =  temp2
                    val btnLeftLen  =  temp3
                    if(btnRightLen < btnLeftLen){
                        answer += "R"
                        startRight[0] = keyPad[9][0]
                        startRight[1] = keyPad[9][1]
                    } 
                    if(btnRightLen > btnLeftLen){
                        answer += "L"
                        startLeft[0] = keyPad[9][0]
                        startLeft[1] = keyPad[9][1]
                    } 
                    if(btnRightLen == btnLeftLen){
                        answer += pinger
                        if(pinger == "R"){
                            startRight[0] = keyPad[9][0]
                            startRight[1] = keyPad[9][1]
                        }else{
                            startLeft[0] = keyPad[9][0]
                            startLeft[1] = keyPad[9][1]
                        }
                    }
                }
            }
        }
        return answer
    }
}