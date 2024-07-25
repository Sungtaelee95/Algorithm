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

    val rows = DoubleArray(n)
    val cols = DoubleArray(n)
    repeat(n) {
        st = StringTokenizer(br.readLine())
        rows[it] = st.nextToken().toDouble()
        cols[it] = st.nextToken().toDouble()
    }
    var num1 = 0.0
    for (i in 0 until n) {
        if (i == n-1) {
            num1 += rows[i] * cols[0]
            continue
        }
        num1 += rows[i] * cols[i + 1]
    }
    var num2 = 0.0
    for (i in 0 until n) {
        if (i == n-1) {
            num2 += cols[i] * rows[0]
            continue
        }
        num2 += cols[i] * rows[i + 1]
    }
    val result = kotlin.math.abs(round(((num1 - num2) / 2.0) * 10.0) / 10.0)
    bw.append("%.1f".format(result))

    bw.flush()
    bw.close()
}