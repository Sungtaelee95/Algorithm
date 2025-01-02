import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

lateinit var map: Array<IntArray>
lateinit var result: Array<IntArray>
var n = -1
val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var count = 1
    while (true) {
        n = br.readLine().toInt()
        if (n == 0) break
        map = Array(n) { IntArray(n) }
        result = Array(n) { IntArray(n) {Int.MAX_VALUE} }
        repeat(n) {
            map[it] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        bfs()
        bw.appendLine("Problem ${count++}: ${result[n-1][n-1]}")
    }

    bw.flush()
    bw.close()
}
fun bfs() {
    val dq = ArrayDeque<Info>()
    dq.add(Info(0, 0, map[0][0]))
    result[0][0] = map[0][0]
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        for (i in 0..3) {
            val nr = now.row + rows[i]
            val nc = now.col + cols[i]
            if (nr < 0 || nc < 0 || nr >= n || nc >= n ) continue
            if (result[nr][nc] <= result[now.row][now.col] + map[nr][nc]) continue
            result[nr][nc] = result[now.row][now.col] + map[nr][nc]
            dq.addLast(Info(nr, nc, result[now.row][now.col] + map[nr][nc]))
        }
    }
}

data class Info(val row: Int, val col: Int, val dist: Int)
