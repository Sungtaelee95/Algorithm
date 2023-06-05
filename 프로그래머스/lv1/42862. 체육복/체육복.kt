class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = 0
        var tempMap = mutableMapOf<Int,Int>()
        
        for(i in 1 .. n){
            //println("${i}번째 반복")
            if (reserve.contains(i)) {
                if(lost.contains(i)) {
                    tempMap.put(i,1)
                    //println("작동확인1")
                    continue
                }
                else {
                    tempMap.put(i,2)
                    //println("작동확인2")
                } 
            }
            else if(lost.contains(i)){
                tempMap.put(i,0)
                //println("작동확인3")
            }
            else {
                tempMap.put(i,1)
                //println("작동확인4")
            }
            
        }       
        println(tempMap)
        for(i in 1 .. n){
            if(tempMap.get(i)==2 && tempMap.get(i+1)==0 ){
                if(tempMap.get(i-1)==0 && tempMap.get(i+2)==2 ){
                    tempMap.put(i,1)
                    tempMap.put(i-1,1)
                    continue
                }
                tempMap.put(i,1)
                tempMap.put(i+1,1)
            } 
        }
        println(tempMap.size-1)
        for(i in n downTo 2){
            if(tempMap.get(i)==2 && tempMap.get(i-1)==0){
                    tempMap.put(i,1)
                    tempMap.put(i-1,1)
            } 
        }
        
        
        answer = tempMap.count{e-> e.value > 0 }
        
        return answer
    }
}