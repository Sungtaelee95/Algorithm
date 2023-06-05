class Solution {
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        var answer: IntArray = intArrayOf()

        var map = HashMap<String,Int>()

        for(i in 0 .. name.size-1){
            map[name[i]] = yearning[i]
        }

        photo.forEach{
            var temp = 0
            it.forEach{
                if(map.containsKey(it)){
                    temp += map[it]!!
                }
            }
            answer += temp
        }

        return answer
    }
}