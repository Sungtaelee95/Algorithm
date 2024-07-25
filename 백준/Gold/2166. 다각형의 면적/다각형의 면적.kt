import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.abs
import java.text.Format
import java.util.*
import kotlin.math.round

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()

    val rows = LongArray(n)
    val cols = LongArray(n)
    repeat(n) {
        st = StringTokenizer(br.readLine())
        rows[it] = st.nextToken().toLong()
        cols[it] = st.nextToken().toLong()
    }
    var result = 0L
    for (i in 0 until n) {
        if (i == n - 1) {
            result += rows[i] * cols[0]
            result -= cols[i] * rows[0]
            continue
        }
        result += rows[i] * cols[i + 1]
        result -= cols[i] * rows[i + 1]
    }

    bw.append(String.format("%.1f", abs(result / 2.0)))

    bw.flush()
    bw.close()
}