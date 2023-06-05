class Solution {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        var answer = IntArray(id_list.size,{0})
        var checkMap = mutableMapOf<String,Int>() 
        id_list.forEach{
            checkMap.put(it,0)
        }
        val reportSet = report.toSet() 
        reportSet.forEach{
            var count = checkMap.getValue(it.split(" ")[1])+1
            checkMap.put(it.split(" ")[1], count)
        }
        reportSet.forEach{
            if(checkMap.getValue(it.split(" ")[1]) >= k) {
                answer[ id_list.indexOf(it.split(" ")[0]) ] += 1
            }
        }

        return answer
    }
}