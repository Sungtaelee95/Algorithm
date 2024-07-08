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
    val m = st.nextToken().toLong()
    var result = Int.MAX_VALUE

    val memorys = IntArray(n + 2) // 사용 메모리
    val coin = IntArray(n + 2) // 비용
    st = StringTokenizer(br.readLine())
    repeat(n) {
        memorys[it + 1] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    repeat(n) {
        coin[it + 1] = st.nextToken().toInt()
    }

    val dp = Array(n + 1) { LongArray(10_001) { 0L } }
    for (i in 1 .. n) {
        for (j in 0 .. 10_000) {
            if (j < coin[i]) {
                dp[i][j] = dp[i-1][j]
                continue
            }
            dp[i][j] = maxOf(dp[i-1][j-coin[i]] + memorys[i], dp[i-1][j])
            if (dp[i][j] >= m) result = minOf(result, j)
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}