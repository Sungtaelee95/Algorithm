import java.util.*
fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val dp = IntArray(N) { st.nextToken().toInt() }
    var answer = dp[0]

    for (i in 1 until N) {
        dp[i] = Math.max(dp[i], dp[i-1] + dp[i])
        answer = Math.max(answer, dp[i])
    }

    bw.append("$answer")

    bw.flush()
    bw.close()
}
