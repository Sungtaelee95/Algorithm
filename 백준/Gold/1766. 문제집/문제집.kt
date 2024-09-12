import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val count = IntArray(n + 1)
    val arr = Array(n + 1) { mutableListOf<Int>() }
    repeat(m) {
        st = StringTokenizer(br.readLine())
        val e = st.nextToken().toInt()
        val h = st.nextToken().toInt()
        count[h]++
        arr[e].add(h)
    }
    val pq = PriorityQueue<Int>()
    for (i in 1..n) {
        if (count[i] == 0) pq.add(i)
    }
    while (pq.isNotEmpty()) {
        val num = pq.poll()
        bw.append("$num ")
        arr[num].forEach {
            if (--count[it] == 0) pq.add(it)
        }
    }

    bw.flush()
    bw.close()
}
