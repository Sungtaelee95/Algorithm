class Solution {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 1
        var clothSet = mutableMapOf<String,Int>()
        for(cloth in clothes){
            if(clothSet.containsKey(cloth[1])){ //해당 부위가 이미 등록되어 있을 경우
                clothSet.put(cloth[1],clothSet.get(cloth[1])!!.plus(1))
            } else {
                clothSet.put(cloth[1], 1)
            }
        }
        
        for(i in clothSet){
            answer *= i.value + 1
        }
        
        return answer - 1
    }
}