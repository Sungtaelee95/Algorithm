import java.io.BufferedReader // ktlint-disable filename
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val arr = IntArray(N) { st.nextToken().toInt() }
    st = StringTokenizer(br.readLine())
    val B = st.nextToken().toInt()
    val C = st.nextToken().toInt()

    var result = 0L

    for (i in arr.indices) {
        arr[i] -= B
        result++
    }

    for (i in arr.indices) {
        if (arr[i] <= 0) continue
        if (arr[i] < C) {
            result++
        } else {
            if (arr[i] % C != 0) result++
            result += arr[i] / C
        }
    }

    bw.append("$result")
    bw.flush()
    bw.close()
}
