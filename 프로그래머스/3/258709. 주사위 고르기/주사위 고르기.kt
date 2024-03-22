class Solution {
    
    lateinit var diceUseCheck: BooleanArray // 주사위 조합 시 사용
    lateinit var dice: Array<IntArray> // 다른 함수에서 참조하기 위해 멤버변수로 설정
    var answer: IntArray = intArrayOf()
    var maxWin = 0 // 승률를 저장할 변수\
    
    var aSum = IntArray(501) {0}
    var bSum = IntArray(501) {0}
    
    fun solution(dice: Array<IntArray>): IntArray {    
        this.dice = dice
        diceUseCheck = BooleanArray(dice.size)
        
        for (i in 0 until dice.size) {
            diceUseCheck[i] = true // 사용처리
            aDiceDfs(i, 1)
            diceUseCheck[i] = false // 미사용처리
        }
        
        return answer
    }
    
    fun aDiceDfs(start: Int, count: Int) { // 주사위 조합을 정하는 dfs
        if (count == dice.size / 2){
            simulateSetting()
            return
        } 
        for (i in start until dice.size) {
            if (diceUseCheck[i]) continue // 사용한 것은 패스
            diceUseCheck[i] = true
            aDiceDfs(i, count+1)
            diceUseCheck[i] = false
        }
    }
    
    fun simulateSetting() { // 현재 주사위 조합으로 승패 시뮬레이션을 하기 위한 셋팅
        val aDice = mutableListOf<IntArray>() // a주사위 저장
        val bDice = mutableListOf<IntArray>() // b주사위 저장
        
        val aDiceNumber = mutableListOf<Int>() // a주사위 번호 저장
        val bDiceNumber = mutableListOf<Int>() // b주사위 번호 저장
    
        for (i in 0 until dice.size) {
            if (diceUseCheck[i]) { // 주사위 사용여부 확인
                 aDice.add(dice[i])
                 aDiceNumber.add(i+1)
                 continue
            } 
            bDice.add(dice[i])
            bDiceNumber.add(i+1)
        }
        
        aSum = IntArray(501){0}
        bSum = IntArray(501){0}
        
        simulater(aDice, aSum) // 주사위 정보를 시뮬레이터에게 전달
        simulater(bDice, bSum)
        
        var winCount = 0
        for(i in 1 .. 500) {
            bSum[i] += bSum[i-1]
            winCount += aSum[i]*bSum[i-1]
        }
        
        if (maxWin < winCount) {
            maxWin = winCount
            answer = aDiceNumber.sorted().toIntArray()
        }
    }
    
    
    fun simulater(dices: MutableList<IntArray>, sumArr: IntArray) {
        dices[0].forEach{
            sumDfs(1, it, dices, sumArr)
        }
    }
    
    fun sumDfs(deep: Int, number: Int, dices: MutableList<IntArray>, sumArr: IntArray) {
        if (deep == dices.size) {
            sumArr[number]++
            return
        }
        for (num in dices[deep]) {
            sumDfs(deep+1, number+num, dices, sumArr)
        }
    }
}