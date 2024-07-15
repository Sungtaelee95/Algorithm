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

    val weight = mutableListOf<Int>(0)
    val cost = mutableListOf<Int>(0)

    repeat(n) {
        var (v,c,k) = br.readLine().split(" ").map { it.toInt() }
        var idx = 1
        while(k > 0) {
            val temp = minOf(idx, k)
            weight.add(v*temp)
            cost.add(c*temp)
            idx *= 2
            k -= temp
        }
    }

    val dp = Array(weight.size+1) { IntArray(m+1) }
    for (i in 1 until weight.size) {
        for (j in 1..m) {
            if (j < weight[i]) {
                dp[i][j] = dp[i - 1][j]
                continue
            }
            dp[i][j] = maxOf(dp[i - 1][j], dp[i - 1][j - weight[i]] + cost[i])
        }
    }

    bw.append("${dp[weight.size-1][m]}")

    bw.flush()
    bw.close()
}