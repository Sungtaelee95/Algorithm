import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val tc = br.readLine().toInt()

    repeat(tc) {
        var st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()
        st = StringTokenizer(br.readLine())
        val times = IntArray(n) { st.nextToken().toInt() }
        val load = Array(n) { mutableListOf<Int>() }
        val indrgree = IntArray(n)
        repeat(k) {
            st = StringTokenizer(br.readLine())
            val start = st.nextToken().toInt() - 1
            val end = st.nextToken().toInt() - 1
            load[start].add(end)
            indrgree[end]++
        }
        val target = br.readLine().toInt() - 1
        val lengths = IntArray(n)

        val dq = ArrayDeque<Int>()

        for (i in 0 until n) {
            if (indrgree[i] == 0) {
                dq.add(i)
                lengths[i] = times[i]
            }
        }

        while (dq.isNotEmpty()) {
            val now = dq.removeFirst()
            for (next in load[now]) {
                lengths[next] = maxOf(lengths[next], lengths[now] + times[next])
                indrgree[next]--
                if (indrgree[next] == 0) dq.add(next)
            }
        }
        bw.appendLine("${lengths[target]}")
    }

    bw.flush()
    bw.close()
}

