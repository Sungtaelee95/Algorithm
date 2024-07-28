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

    val maxDp = Array(n + 1) { IntArray(3) }
    val minDp = Array(n + 1) { IntArray(3) }

    repeat(n) {
        st = StringTokenizer(br.readLine())
        val arr = IntArray(3) { st.nextToken().toInt() }
        maxDp[it+1][0] = maxOf(maxDp[it][0],maxDp[it][1]) + arr[0]
        maxDp[it+1][1] = maxOf(maxDp[it][0],maxOf(maxDp[it][1],maxDp[it][2])) + arr[1]
        maxDp[it+1][2] = maxOf(maxDp[it][1],maxDp[it][2]) + arr[2]

        minDp[it+1][0] = minOf(minDp[it][0],minDp[it][1]) + arr[0]
        minDp[it+1][1] = minOf(minDp[it][0],minOf(minDp[it][1],minDp[it][2])) + arr[1]
        minDp[it+1][2] = minOf(minDp[it][1],minDp[it][2]) + arr[2]
    }

    bw.append("${maxOf(maxDp[n][0],maxOf(maxDp[n][1],maxDp[n][2]))} ${minOf(minDp[n][0],minOf(minDp[n][1],minDp[n][2]))}")

    bw.flush()
    bw.close()
}
