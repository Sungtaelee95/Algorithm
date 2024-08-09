import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)
lateinit var dist: Array<IntArray>
lateinit var visit: Array<BooleanArray>
lateinit var map: Array<CharArray>
var flag = false
var result = 0
var n = 0
var m = 0
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    map = Array(n) { CharArray(m) }
    repeat(n) {
        map[it] = br.readLine().toCharArray()
    }
    dist = Array(n) { IntArray(m) }
    visit = Array(n) { BooleanArray(m) }
    visit[0][0] = true
    dist[0][0] = 1
    dfs(Node(0, 0))

    if (flag) {
        bw.append("-1")
    } else {
        bw.append("$result")
    }


    bw.flush()
    bw.close()
}

fun dfs(now: Node) {
    if (flag) return
    result = maxOf(result, dist[now.row][now.col])
    for (i in 0..3) {
        val nr = now.row + rows[i] * (map[now.row][now.col] - '0')
        val nc = now.col + cols[i] * (map[now.row][now.col] - '0')
        if (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 'H') continue
        if (visit[nr][nc]) {
            flag = true
            return
        }
        visit[nr][nc] = true
        if (dist[nr][nc] < dist[now.row][now.col] + 1) {
            dist[nr][nc] = dist[now.row][now.col] + 1
            dfs(Node(nr, nc))
        }
        visit[nr][nc] = false
    }
}

data class Node(val row: Int, val col: Int)

