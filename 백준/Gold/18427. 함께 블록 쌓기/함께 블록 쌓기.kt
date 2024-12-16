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
    val h = st.nextToken().toInt()
    val mod = 10007

    val blocks = Array(n) { br.readLine().split(" ").map { it.toInt() } }

    val dp = Array(n + 1) { IntArray(h + 1) }
    dp[0][0] = 1

    for (i in 1..n) {
        for (j in 0..h) {
            dp[i][j] = dp[i - 1][j]
            for (block in blocks[i - 1]) {
                if (j - block >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - block]) % mod
                }
            }
        }
    }

    bw.append("${dp[n][h]}")

    bw.flush()
    bw.close()
}
