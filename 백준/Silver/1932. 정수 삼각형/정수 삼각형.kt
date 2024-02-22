import java.lang.Math.max
import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val dp = Array(501) { IntArray(501) }
    val map = Array(501) { IntArray(501) }

    for (i in 1..N) {
        st = StringTokenizer(br.readLine())
        for (j in 1..i) {
            map[i][j] = st.nextToken().toInt()
        }
    }
    dp[1][1] = map[1][1]
    dp[2][1] = map[1][1] + map[2][1]
    dp[2][2] = map[1][1] + map[2][2]
    for (i in 2 until 500) {
        for (j in 1 until 500) {
            dp[i+1][j] = max(dp[i+1][j], map[i+1][j] + dp[i][j])
            dp[i+1][j+1] = max(dp[i+1][j+1], map[i+1][j+1] + dp[i][j])
        }
    }

    bw.append("${dp[N].max()}")
    bw.flush()
    bw.close()
}
