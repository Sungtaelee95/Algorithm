import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m, s, t) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(n + 1) { LongArray(n + 1) { 1_000_000_000L } }
    for (r in 0..n) {
        for (c in 0..n) {
            if (r == c) map[r][c] = 0
        }
    }
    repeat(m) {
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        map[a][b] = minOf(c.toLong(), map[a][b])
    }

    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                map[i][j] = minOf(map[i][j], map[i][k] + map[k][j])
            }
        }
    }

    repeat(br.readLine().toInt()) {
        val st = StringTokenizer(br.readLine())
        val a1 = st.nextToken().toInt()
        val b1 = st.nextToken().toInt()
        val c1 = st.nextToken().toLong()
        val a2 = st.nextToken().toInt()
        val b2 = st.nextToken().toInt()
        val c2 = st.nextToken().toLong()
        var answer = map[s][t]
        answer = minOf(answer, map[s][a1] + minOf(c1, map[a1][b1]) + map[b1][t])
        answer = minOf(answer, map[s][a2] + minOf(c2, map[a2][b2]) + map[b2][t])
        answer = minOf(answer, map[s][a1] + minOf(c1, map[a1][b1]) + map[b1][a2] + minOf(c2, map[a2][b2]) + map[b2][t])
        answer = minOf(answer, map[s][a2] + minOf(c2, map[a2][b2]) + map[b2][a1] + minOf(c1, map[a1][b1]) + map[b1][t])
        if (answer == 1_000_000_000L) {
            bw.appendLine("-1")
        } else {
            bw.appendLine("$answer")
        }

    }
    bw.flush()
    bw.close()

}