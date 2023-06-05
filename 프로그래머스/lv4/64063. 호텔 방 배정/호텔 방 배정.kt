class Solution {

    var result = HashMap<Long,Long>()

    fun solution(k: Long, room_number: LongArray): LongArray {
        var answer = LongArray(room_number.size)

        for(i in 0 .. room_number.size-1){
            answer[i] = find(room_number[i])
        }
        return answer
    }

    fun find(want:Long):Long{
        if(result[want] == null){
            result[want] = want+1L
            return want
        }
        result[want] = find(result[want]!!)
        return result[want]!!
    }

}