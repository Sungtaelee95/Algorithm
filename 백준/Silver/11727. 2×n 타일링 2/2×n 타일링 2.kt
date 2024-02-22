import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val dp = IntArray(1001)
    dp[0] = 0
    dp[1] = 1
    dp[2] = 3
    for (i in 3..N) {
        dp[i] = (dp[i - 2]*2 + dp[i - 1]) % 10_007
    }

    bw.append("${dp[N]}")

    bw.flush()
    bw.close()
}
