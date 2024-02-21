import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val dp = IntArray(1001)
    dp[1] = 1
    dp[2] = 2
    for (i in 3 until 1001) {
        dp[i] = (dp[i - 1] + dp[i - 2]) % 10_007
    }

    bw.append("${dp[N]}")

    bw.flush()
    bw.close()
}