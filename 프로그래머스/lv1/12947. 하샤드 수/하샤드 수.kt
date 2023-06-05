class Solution {
    fun solution(x: Int): Boolean {
    var answer = false
    var sum = 0
    var stringX = x.toString()
    for(i in 0..stringX.length-1){
        sum += stringX[i].toInt() - 48 //아스키코드? 그걸로 변하는걸 생각을 못했네......갈길이 머네요.....
    }
    if (x % sum == 0 ) answer = true
    return answer
    }
}