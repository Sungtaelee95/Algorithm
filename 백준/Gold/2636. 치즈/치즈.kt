import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)
var n = 0
var m = 0
lateinit var map: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    map = Array(n) { br.readLine().split(" ").map { it.toInt() }.toIntArray() }
    var time = 0
    while (true) {
        val updateMap = updateMap()
        time++
        if (endCheck(updateMap)) {
            bw.append("$time\n${countCheese()}")
            break
        }
        map = updateMap
    }
    bw.flush()
    bw.close()
}

private fun endCheck(map: Array<IntArray>) = map.all { it.sum() == 0 }

private fun countCheese(): Int {
    var count = 0
    for (r in 0 until n) {
        for (c in 0 until m) {
            if (map[r][c] == 1) count++
        }
    }
    return count
}

private fun updateMap(): Array<IntArray> {
    val updateMap = Array(n) { IntArray(m) }
    val dq = ArrayDeque<Node>()
    val visited = Array(n) { BooleanArray(m) }
    for (r in 0 until n) {
        for (c in 0 until m) {
            updateMap[r][c] = map[r][c]
            if (r == 0 || r == n - 1 || c == 0 || c == m - 1) {
                visited[r][c] = true
                dq.addLast(Node(r, c))
            }
        }
    }
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (map[now.row][now.col] == 1) {
            updateMap[now.row][now.col]--
            continue
        }
        for (i in 0..3) {
            val nr = now.row + rows[i]
            val nc = now.col + cols[i]
            if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc]) continue
            visited[nr][nc] = true
            dq.addLast(Node(nr, nc))
        }
    }
    return updateMap
}

data class Node(val row: Int, val col: Int)