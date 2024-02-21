import java.util.*
import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val houses = Array(N) { intArrayOf() }
    val dp = Array(N) { IntArray(3) }
    repeat(N) {
        st = StringTokenizer(br.readLine())
        houses[it] = IntArray(3) { st.nextToken().toInt() }
    }
    dp[0][0] = houses[0][0]
    dp[0][1] = houses[0][1]
    dp[0][2] = houses[0][2]
    for (row in 1 until N) {
        for (col in 0 until 3) {
            when(col) {
                0 -> {
                    dp[row][0] = houses[row][0] + min(dp[row-1][1], dp[row-1][2])
                }
                1 -> {
                    dp[row][1] = houses[row][1] + min(dp[row-1][0], dp[row-1][2])
                }
                2 -> {
                    dp[row][2] = houses[row][2] + min(dp[row-1][0], dp[row-1][1])
                }
            }
        }
    }

    bw.append("${dp[N-1].min()}")

    bw.flush()
    bw.close()
}