import java.util.*

class Solution {
    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
        var answer: Array<IntArray> = rc
        var Left = ArrayDeque<Int>()
        var Mid = ArrayDeque<ArrayDeque<Int>>()
        var Right = ArrayDeque<Int>()

        //데이터 입력
        for(i in 0 .. rc.size-1){
            var temp = ArrayDeque<Int>()
            for(j in 0 .. rc[i].size-1){
               if(j == 0) Left.add(rc[i][j])
               else if(j == rc[i].size-1) Right.add(rc[i][j])
               else temp.add(rc[i][j])
            }
            Mid.add(temp)
        }

        operations.forEach {
            if(it == "Rotate"){
                Mid.peekFirst().addFirst(Left.pollFirst());
                Right.addFirst(Mid.peekFirst().pollLast());
                Mid.peekLast().addLast(Right.pollLast());
                Left.addLast(Mid.peekLast().pollFirst());

            } else {
                Left.addFirst(Left.pollLast());
                Mid.addFirst(Mid.pollLast());
                Right.addFirst(Right.pollLast());
            }
        }


        for(i in 0 .. rc.size-1){
            var j = 0
            answer[i][0] = Left.pollFirst()
            j++
            var temp = Mid.pollFirst()
            while(!temp.isEmpty()){
                answer[i][j++] = temp.pollFirst()
            }
            answer[i][j] = Right.pollFirst()
        }


        return answer
    }
}