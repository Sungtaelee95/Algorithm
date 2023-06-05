import java.util.*

lateinit var array: Array<IntArray>
lateinit var visited: BooleanArray
lateinit var answer: ArrayList<Int>

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    array = Array(n + 1) { intArrayOf() }
    visited = BooleanArray(n + 1)
    answer = ArrayList()

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        array[b] += intArrayOf(a)
    }

    var max = 0

    for (i in 1..array.lastIndex) {
        val tmp = BFS(i)

        for (j in visited.indices) {
            visited[j] = false
        }

        if (tmp >= max) {
            if(tmp > max) answer.clear()

            max = tmp
            answer += i
        }
    }

    answer.forEach {
        print("$it ")
    }
}

fun BFS(start: Int): Int {
    val queue = LinkedList<Int>()

    queue.add(start)
    visited[start] = true

    var level = 0

    while (queue.isNotEmpty()) {
        val curr = queue.poll()

        for (i in array[curr]) {
            if (!visited[i]) {
                queue.add(i)
                level++
                visited[i] = true
            }
        }
    }

    return level
}

