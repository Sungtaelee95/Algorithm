import java.util.PriorityQueue

class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        var answer = intArrayOf()
        var mscnt = HashMap<String,Int>()

        for(i in 0 .. genres.size-1){
            if(mscnt.containsKey(genres[i])){
                mscnt[genres[i]] = plays[i] + mscnt[genres[i]]!!
                continue
            }
            mscnt[genres[i]] = plays[i]
        }

        var temp = mscnt.toList().sortedWith(java.util.Comparator { o1, o2 -> o2.second.compareTo(o1.second)})

        for(i in temp) {
            var pq = PriorityQueue<Info>(Comparator { o1, o2 ->  o2.count.compareTo(o1.count)})
            for(j in 0 .. genres.size-1){
                if(genres[j] == i.first){
                    pq.add(Info(genres[j],plays[j],j))
                }
            }
            answer += pq.poll().num
            if(!pq.isEmpty()) {
                answer += pq.poll().num
            }
        }

        return answer
    }
    data class Info(
        val music: String,
        val count: Int,
        val num: Int
    )
}
