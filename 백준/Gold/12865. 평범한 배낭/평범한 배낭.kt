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

    val weight = IntArray(n + 2)
    val cost = IntArray(n + 2)

    repeat(n) {
        val info = br.readLine().split(" ")
        weight[it + 1] = info[0].toInt()
        cost[it + 1] = info[1].toInt()
    }

    val dp = Array(n + 1) { IntArray(m + 1) }
    var result = 0
    for (i in 1..n) {
        for (j in 1..m) {
            if (j < weight[i]) {
                dp[i][j] = dp[i - 1][j]
                continue
            }
            dp[i][j] = maxOf(dp[i - 1][j], dp[i - 1][j - weight[i]] + cost[i])
            if (j == m) result = maxOf(result, dp[i][j])
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}