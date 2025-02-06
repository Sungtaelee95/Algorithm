import java.io.*

fun main() { 
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = br.readLine().split(" ").map{ it.toInt() }
    val dp = Array(n+1) { IntArray(n+1) }
    for (i in 1 .. n) {
        val input = br.readLine().split(" ").map{ it.toInt() }
        for (j in 1 .. n) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + input[j-1]
        }
    }
    repeat(m) {
        val (x1,y1,x2,y2) = br.readLine().split(" ").map{ it.toInt() }
        bw.appendLine("${dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1]}")
    }
    bw.flush()
    bw.close()
}