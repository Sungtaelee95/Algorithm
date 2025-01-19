import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var input: Array<String>
lateinit var map: Array<IntArray>
lateinit var visit: Array<BooleanArray>
lateinit var board: Array<IntArray>
lateinit var start: Node
lateinit var end: Node
val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)
var r = 0
var c = 0


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    c = st.nextToken().toInt()
    r = st.nextToken().toInt()

    input = Array(r) { br.readLine() }
    map = Array(r) { IntArray(c) }
    visit = Array(r) { BooleanArray(c) }
    board = Array(r) { IntArray(c) { Int.MAX_VALUE } }
    input.forEachIndexed { row, s ->
        s.forEachIndexed { col, c ->
            when (c) {
                in '0'..'9' -> {
                    map[row][col] = c - '0'
                }

                'R' -> {
                    map[row][col] = 0
                }

                'T' -> {
                    start = Node(row, col, 0)
                    map[row][col] = 0
                }

                'E' -> {
                    end = Node(row, col, 0)
                    map[row][col] = 0
                }
            }
        }
    }
    bfs()
    if (board[end.row][end.col] == Int.MAX_VALUE) {
        bw.append("-1")
    } else {
        bw.append("${board[end.row][end.col]}")
    }
    bw.flush()
    bw.close()
}

fun bfs() {
    val pq = PriorityQueue<Node> { o1, o2 -> o1.time.compareTo(o2.time) }
    pq.add(start)
    while (pq.isNotEmpty()) {
        val now = pq.poll()
        if (visit[now.row][now.col]) continue
        visit[now.row][now.col] = true
        for (i in 0..3) {
            val info = canGo(now, i)
            if (info.dist == -1) continue
            val nr = now.row + rows[i] * info.dist
            val nc = now.col + cols[i] * info.dist
            val np = info.point + now.time
            if (board[nr][nc] > np) {
                board[nr][nc] = np
                pq.add(Node(nr, nc, np))
            }
        }
    }
}

fun canGo(node: Node, dire: Int): Info {
    var dist = 1
    var point = 0
    while (true) {
        val nr = node.row + rows[dire] * dist
        val nc = node.col + cols[dire] * dist
        if (nr < 0 || nc < 0 || nr >= r || nc >= c || input[nr][nc] == 'H') break
        if (input[nr][nc] == 'R') return Info(dist - 1, point)
        if (input[nr][nc] == 'E') return Info(dist, point)
        dist++
        point += map[nr][nc]
    }
    return Info(-1, -1)
}

data class Node(val row: Int, val col: Int, val time: Int)
data class Info(val dist: Int, val point: Int)