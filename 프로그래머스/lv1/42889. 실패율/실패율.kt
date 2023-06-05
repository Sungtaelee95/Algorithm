class Solution {
    fun solution(N: Int, stages: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        var miss = mutableMapOf<Int,Double>()
        var missList = mutableListOf<Double>()
        for(i in 1 .. N){
            if((stages.count{e-> e == i }).toDouble()== 0.0 && stages.count{e-> e >= i }.toDouble() == 0.0){
                miss.put(i,0.0)
                missList.add(0.0)
                continue
                //NaN 발생하여 대체
            }
            miss.put(i,(stages.count{e-> e == i }).toDouble()/stages.count{e-> e >= i }.toDouble())
            missList.add((stages.count{e-> e == i }).toDouble()/stages.count{e-> e >= i }.toDouble())
        }
        missList.sort()
        missList.reverse()
        
        for(i in 0 .. missList.size-1){
            for(j in 1 .. missList.size){
                if(missList[i]==miss.get(j)){
                    answer.add(j)
                    miss.remove(j)
                    break
                }
            }
        }
        
        return answer.toIntArray()
    }
}