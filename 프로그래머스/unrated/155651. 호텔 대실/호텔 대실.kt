import java.util.PriorityQueue
class Solution {
    fun solution(book_time: Array<Array<String>>): Int {
        var answer: Int = 0
        val pq = PriorityQueue<Int>()
        book_time.sortWith {o1, o2 ->
            if (o1[0] == o2[0]) {
                o1[1].compareTo(o2[1])
            } else {
                o1[0].compareTo(o2[0])
            }
        }
        for (book in book_time) {
            val start = getTime(book[0])
            val end = getTime(book[1]) + 10

            if (pq.isEmpty() || pq.peek() > start) {
                pq.add(end)
            } else {
                pq.poll()
                pq.add(end)
            }
            answer = answer.coerceAtLeast(pq.size)
        }

        return answer
    }

    fun getTime(s: String): Int {
        val (h, m) = s.split(":").map { it.toInt() }
        return h*60 + m
    }
}
