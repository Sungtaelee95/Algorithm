import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val h = st.nextToken().toInt()
    val dp = LongArray(61)
    dp[0] = 1
    dp[1] = 1
    for (i in 2..h) {
        if (i % 2 == 0){
            dp[i] = 2 * dp[i - 1] + 1
            continue
        }
        dp[i] = 2 * dp[i - 1] - 1
    }

    bw.append("${dp[h]}")

    bw.flush()
    bw.close()
}




