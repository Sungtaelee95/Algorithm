import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val arr = IntArray(301) { 0 }
    repeat(N) {
        arr[it] = br.readLine().toInt()
    }
    val dp = IntArray(301) { 0 }
    dp[0] = arr[0]
    dp[1] = arr[0] + arr[1]
    dp[2] = maxOf(arr[0] + arr[2], arr[1] + arr[2])

    for (i in 3 until N) {
        dp[i] = maxOf(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i])
    }
    bw.append("${dp[N - 1]}")

    bw.flush()
    bw.close()
}