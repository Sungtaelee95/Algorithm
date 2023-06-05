class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0
        
        var prior = mutableListOf<Pair<Int,Int>>()
        
        for(i in 0 .. priorities.size-1){
            prior.add(Pair(i,priorities[i]))
        }
        
        println(prior[0].second)
        var count = 0
        var printList = mutableListOf<Pair<Int,Int>>()
        while(printList.size-1 != priorities.size-1){
            var tempList = mutableListOf<Int>()
            for(i in 0 .. prior.size-1){
                tempList.add(prior[i].second)
            }
            var max = tempList.maxOrNull()
            
            for(i in 0 .. prior.size){
                if(prior[i].second == max){
                    printList.add(prior[i])
                    prior.remove(prior[i])
                    break
                } else {
                    var tempPair = prior[i]
                    prior.remove(prior[i])
                    prior.add(tempPair)
                    break
                }
            }
        }
        for(i in 0 .. printList.size-1){
            if(printList[i].first == location) {
                answer = i+1
                break
            } else continue
        }
        
        
        return answer
    }
}