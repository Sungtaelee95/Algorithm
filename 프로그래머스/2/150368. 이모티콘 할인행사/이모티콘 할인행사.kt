// 아이디어2 dfs
// 1. 이모티콘 별로 할인율 10,20,30,40 적용된 완전 탐색을 실시
// 2. dfs 단계 진행 중 이모티콘 별로 할인율이 모두 적용되었을 경우
//    적용된 것과 유저들 정보를 토대로 반복문을 통하여 서비스 구매 수와 매출액을 구한다.

data class Info(val count: Int, val price: Int)

class Solution {
    lateinit var emoticons: IntArray
    lateinit var users: Array<IntArray>
    
    val salePersent = intArrayOf(10,20,30,40) // (원래가격 * (100 - it)) / 100
    var resultCount = 0
    var resultPrice = 0
    var results = mutableListOf<Info>()
    
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        this.emoticons = emoticons
        this.users = users
        
        for (i in 0 .. 3) {
            saleDfs(1, mutableListOf(i))
        }
        
        results.forEach {
            if (it.count == resultCount) {
                if (it.price >= resultPrice) {
                    resultPrice = it.price
                }
            }
        }
    
        return intArrayOf(resultCount, resultPrice)
    }
    
    fun saleDfs(deep: Int, saleIndex: MutableList<Int>) { // 이모티콘 할인율 경우의 수를 구하는 dfs
        if (deep == emoticons.size) {
            calculator(saleIndex)
            return
        }
        for (i in 0 .. 3) {
            saleIndex.add(i)
            saleDfs(deep+1, saleIndex)
            saleIndex.removeLast()
        }
    }
    
    fun calculator(saleIndex: MutableList<Int>) { 
        // 이모티콘 별 할인율이 적용되었을 때 서비스 가입자 수 및 매출 금액 계산
        val userOfPriceMap = hashMapOf<Int, Int>() // 사용자 별 구매 금액 저장 목적 맵
        for (i in 0 until users.size) {
            userOfPriceMap[i+1] = 0
        }
        
        for (i in 0 until emoticons.size) {
            val discountPersent = salePersent[saleIndex[i]]
            val saleEmoticonPrice = emoticons[i] / 100 * (100 - discountPersent)
            for (j in 0 until users.size) {
                if (users[j][0] > discountPersent) continue // 할인율이 낮으면 패스
                userOfPriceMap[j+1] = userOfPriceMap[j+1]!! + saleEmoticonPrice
            } 
        }
        
        var servierSaleCount = 0 // 서비스 구매 개수
        var salePrice = 0 // 이모티콘 구매 금액
        for (i in 0 until users.size) {
            val user = users[i]
            if (userOfPriceMap[i+1]!! >= user[1]) {
                servierSaleCount++
                continue
            }
            salePrice += userOfPriceMap[i+1]!!
        }
        
        if (resultCount <= servierSaleCount) {
            resultCount = servierSaleCount
            results.add(Info(servierSaleCount, salePrice))
        }
    }
}