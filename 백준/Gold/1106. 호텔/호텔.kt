import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val c = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    val dp = IntArray(c + 101) { 987654321 } // index: 사람 수, 값: 비용
    dp[0] = 0
    repeat(n) {
        st = StringTokenizer(br.readLine())
        val cost = st.nextToken().toInt()
        val human = st.nextToken().toInt()
        for (i in human until c + 101) {
            dp[i] = minOf(dp[i], dp[i - human] + cost)
        }
    }
    var result = Int.MAX_VALUE
    for (i in c until c + 101) {
        result = minOf(result, dp[i])
    }
    bw.append("$result")

    bw.flush()
    bw.close()
}

