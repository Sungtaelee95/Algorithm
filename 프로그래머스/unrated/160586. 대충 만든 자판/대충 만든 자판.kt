import java.lang.Math.min

class Solution {
    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        var answer = IntArray(targets.size){-1}

        var map = mutableMapOf<Char,Int>()

        keymap.forEach {
            val arr = it.toCharArray()
            arr.forEach {
                if(map.containsKey(it)){
                    map[it] = min(map[it]!!,arr.indexOf(it)+1)
                } else { 
                    map[it]= arr.indexOf(it)+1
                }
            }
        }

        for(i in 0 .. targets.size-1){
            val arr = targets[i].toCharArray()
            var count = 0
            var flag = true
            for(j in 0 .. arr.size-1){
                if(map.containsKey(arr[j])){
                    count += map[arr[j]]!!
                } else {
                    flag = false
                    break
                }
            }
            if(flag) answer[i] = count
        }

        return answer
    }
}