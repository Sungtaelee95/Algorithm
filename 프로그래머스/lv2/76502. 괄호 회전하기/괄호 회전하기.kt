import java.util.*

class Solution {
    fun solution(s: String): Int {
        var answer: Int = 0
        var list = mutableListOf<Char>()
        var count = 0
        s.forEach{
            list.add(it)
        }
        while(count != s.length){
            count++
            list.add(list[0])
            list.removeAt(0)
            var tempDeque = ArrayDeque<Char>()
            var check = true
            for(i in 0 .. list.size-1){
                if(tempDeque.size >= 1){
                    when(tempDeque.peekLast()){
                        '[' -> if(list[i] == ']') {
                                    tempDeque.removeLast()
                                    check = false
                                }
                        '(' -> if(list[i] == ')') {
                                    tempDeque.removeLast()
                                    check = false
                                }
                        '{' -> if(list[i] == '}') {
                                    tempDeque.removeLast()
                                    check = false
                                }
                    }  
                }
                if(check) {
                    tempDeque.add(list[i])
                } else {
                    check = true
                }
            }
            
            if(tempDeque.size == 0) answer++
        }
        return answer
    }
}