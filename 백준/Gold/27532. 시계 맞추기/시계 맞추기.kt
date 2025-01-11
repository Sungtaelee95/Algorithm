import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val m = br.readLine().toInt()
    val input = IntArray(m) {
        val time = br.readLine().split(":").map { it.toInt() }
        (time[0] - 1) * 60 + time[1]
    }
    var result = Int.MAX_VALUE
    for (r in 1..720) {
        val times = input.mapIndexed { index, i -> i - (r * (index + 1)) }.toIntArray()
        for (i in times.indices) {
            while (times[i] < 0) {
                times[i] += 720
            }
        }
        result = minOf(result, times.distinct().size)
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}