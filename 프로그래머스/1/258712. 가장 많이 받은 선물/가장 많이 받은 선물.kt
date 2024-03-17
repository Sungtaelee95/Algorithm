class Solution {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        var answer: Int = 0
        val giftMap = hashMapOf<String, MutableList<Info>>() // 키: 준사람, 값 : Info(받은 사람, 받은 갯수)
        val scoreMap = hashMapOf<String, Int>() // 키: 이름, 값: 선물 지수
        val countMap = hashMapOf<String, Int>() // 키: 이름, 값: 선물 갯수
        val distinctCheckMap = hashMapOf<String, MutableList<String>>() // 키: 이름(준 사람), 값: 받은 사람 목록 
        
        for (friend in friends) {
            scoreMap[friend] = 0
            countMap[friend] = 0
            distinctCheckMap[friend] = mutableListOf<String>()
            val temp = mutableListOf<Info>()
            friends.forEach {
                temp.add(Info(it, 0))
            }
            giftMap[friend] = temp
        }
        
        for (gift in gifts) {
            val giveFriend = gift.split(" ")[0] // 준 사람
            val takeFriend = gift.split(" ")[1] // 받은 사람
            if(giftMap[giveFriend] != null) {
                if(giftMap[giveFriend]!!.count{it.takeName == takeFriend} > 0) {
                    giftMap[giveFriend]!!.forEach {
                        if(it.takeName == takeFriend) it.count++ // 선물을 준 갯수 증가
                    }
                } else {
                    giftMap[giveFriend]!!.add(Info(takeFriend, 1))
                }
            } else {
                giftMap[giveFriend] = mutableListOf(Info(takeFriend, 1))
            }
            
            scoreMap[giveFriend] = scoreMap[giveFriend]!! + 1 // 선물 지수 증가
            scoreMap[takeFriend] = scoreMap[takeFriend]!! - 1 // 선물 지수 감소
        }
        
        giftMap.forEach {
            val giveFriend = it.key // 준 사람
            val vue = it.value // 받은 사람들 리스트
            vue.forEach { info ->
                val takeName = info.takeName // 선물 받은 사람 이름
                val count = info.count // 선물을 준 갯수
                val reverseCount = if (giftMap[takeName] != null && 
                        giftMap[takeName]!!.filter{it.takeName == giveFriend}.isNotEmpty()){
                    giftMap[takeName]!!.filter{it.takeName == giveFriend}.first().count
                } else 0
                // takeName에게 선물 받은 갯수
                if (takeName != giveFriend){
                    if (count > reverseCount) {
                        countMap[giveFriend] = countMap[giveFriend]!! + 1
                    }
                    if (count == reverseCount && !distinctCheckMap[takeName]!!.contains(giveFriend)) {
                        if (scoreMap[giveFriend]!! > scoreMap[takeName]!!) {
                            countMap[giveFriend] = countMap[giveFriend]!! + 1
                        }
                        if (scoreMap[giveFriend]!! < scoreMap[takeName]!!) {
                            countMap[takeName] = countMap[takeName]!! + 1
                        }
                    }
                    distinctCheckMap[giveFriend]!!.add(takeName)
                }
            }
        }
        
        countMap.forEach{
            answer = maxOf(answer, it.value)
        }
        
        return answer
    }
}

data class Info(val takeName: String, var count: Int) // 받은 사람, 받은 갯수