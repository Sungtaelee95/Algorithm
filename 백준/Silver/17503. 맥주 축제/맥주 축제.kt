import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toLong()
    val K = st.nextToken().toInt()

    val pq = PriorityQueue<Beer>(compareBy { it.level })

    repeat(K) {
        st = StringTokenizer(br.readLine())
        pq.add(Beer(st.nextToken().toLong(), st.nextToken().toLong()))
    }

    var result = Long.MAX_VALUE
    var sum = 0L
    val pq2 = PriorityQueue<Beer>(compareBy { it.like })

    while (pq.isNotEmpty()) {
        val beer = pq.poll()
        sum += beer.like
        pq2.add(beer)
        if (pq2.size > N) {
            sum -= pq2.poll().like
        }
        if (sum >= M && pq2.size == N) {
            result = beer.level
            break
        }
    }

    if (result == Long.MAX_VALUE) bw.append("-1") else bw.append("$result")

    bw.flush()
    bw.close()
}

data class Beer(val like: Long, val level: Long)