import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    val input = br.readLine().split(" ").map { it.toLong() }.sorted()
    if (input[0] != 1L) {
        bw.append("1")
    } else {
        var sum = input[0]
        for (i in 1 until n) {
            if (sum < input[i] - 1) break
            sum += input[i]
        }
        bw.append("${sum + 1}")
    }

    bw.flush()
    bw.close()
}
