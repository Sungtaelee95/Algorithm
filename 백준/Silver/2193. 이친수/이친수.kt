import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val dp = LongArray(91)
    dp[1] = 1
    dp[2] = 1
    for (i in 3..N) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    bw.append("${dp[N]}")

    bw.flush()
    bw.close()
}
