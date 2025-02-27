import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val works = Array(n) {
        val (t, p) = br.readLine().split(" ").map { it.toInt() }
        Work(t, p)
    }
    var result = 0
    for (i in 0 until n) {
        if (works[i].time + i > n) continue
        val dq = ArrayDeque<Work>()
        dq.addLast(Work(works[i].time + i, works[i].pay))
        while (dq.isNotEmpty()) {
            val now = dq.removeFirst()
            result = maxOf(result, now.pay)
            for (j in now.time until n) {
                if (works[j].time + j > n) continue
                dq.addLast(Work(works[j].time + j , works[j].pay + now.pay))
            }
        }
    }
    print("$result")
}

data class Work(val time: Int, val pay: Int)