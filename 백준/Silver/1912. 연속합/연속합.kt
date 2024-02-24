import java.util.*
import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val arr = IntArray(N) { st.nextToken().toInt() }
    val dp = IntArray(N)
    arr.forEachIndexed { index, i ->
        dp[index] = i
    }

    for (i in 1 until N) {
        dp[i] = max(dp[i], dp[i-1] + arr[i])
    }

    bw.append("${dp.max()}")

    bw.flush()
    bw.close()
}
