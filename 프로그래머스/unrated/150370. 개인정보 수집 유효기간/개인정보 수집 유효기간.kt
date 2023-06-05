class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        var termsMap = mutableMapOf<String,Int>()
        terms.forEach{
            termsMap.put(it.split(" ")[0], it.split(" ")[1].toInt()*28)
        }
        var todayCheck = 0
        todayCheck += today.split(".")[0].toInt()*12*28
        todayCheck += today.split(".")[1].toInt()*28
        todayCheck += today.split(".")[2].toInt()
        for(i in 0 .. privacies.size-1){
            var day = 0
            day += privacies[i].split(".")[0].toInt()*12*28
            day += privacies[i].split(".")[1].toInt()*28
            day += privacies[i].split(".")[2].split(" ")[0].toInt()
            
            var check = privacies[i].split(".")[2].split(" ")[1]
            if(todayCheck - day >= termsMap.get(check)!!) answer += i+1
        }
        
        return answer
    }
}