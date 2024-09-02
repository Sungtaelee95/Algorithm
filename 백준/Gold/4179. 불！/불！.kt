import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

lateinit var map: Array<IntArray>
lateinit var visit: Array<BooleanArray>
lateinit var jihun: Node
var fires = mutableListOf<Node>()
var result = Int.MAX_VALUE
var r = 0
var c = 0
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    r = st.nextToken().toInt()
    c = st.nextToken().toInt()

    map = Array(r) { IntArray(c) }
    repeat(r) { row ->
        val input = br.readLine()
        for (i in input.indices) {
            when (input[i]) {
                '#' -> map[row][i] = Int.MAX_VALUE
                'J' -> jihun = Node(row, i)
                'F' -> fires.add(Node(row, i))
                '.' -> map[row][i] = 2000
            }
        }
    }
    bfs(false)
    bfs(true)

    if (result == Int.MAX_VALUE) bw.append("IMPOSSIBLE") else bw.append("$result")

    bw.flush()
    bw.close()
}

fun bfs(isJihun: Boolean) {
    visit = Array(r) { BooleanArray(c) }
    val dq = ArrayDeque<Node>()
    val nextRow = intArrayOf(0, 0, 1, -1)
    val nextCol = intArrayOf(-1, 1, 0, 0)
    if (isJihun) {
        dq.add(jihun)
        map[jihun.row][jihun.col] = 0
    } else {
        for (fire in fires) {
            dq.add(fire)
            map[fire.row][fire.col] = 0
        }
    }
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        for (i in 0..3) {
            val nr = now.row + nextRow[i]
            val nc = now.col + nextCol[i]
            if (nr < 0 || nc < 0 || nr >= r || nc >= c) {
                if (isJihun) {
                    result = minOf(result, map[now.row][now.col] + 1)
                }
                continue
            }
            if (map[nr][nc] <= map[now.row][now.col] + 1 || map[nr][nc] == Int.MAX_VALUE) continue
            map[nr][nc] = map[now.row][now.col] + 1
            dq.addLast(Node(nr, nc))
        }
    }
}

data class Node(val row: Int, val col: Int)
