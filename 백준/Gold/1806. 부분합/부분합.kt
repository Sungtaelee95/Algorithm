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
    val s = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val arr = IntArray(n) { st.nextToken().toInt() }
    var start = 0
    var sum = 0
    var end = 0
    var result = Int.MAX_VALUE
    while (true) {
        if (sum >= s) {
            result = minOf(result, end - start)
            sum -= arr[start]
            start++
        } else if (end == n) {
            break
        } else {
            sum += arr[end]
            end++
        }

    }
    if (result == Int.MAX_VALUE) bw.append("0") else bw.append("$result")

    bw.flush()
    bw.close()
}
