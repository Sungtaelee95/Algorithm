import java.io.BufferedReader // ktlint-disable filename
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var visit: Array<BooleanArray>
var targetDist = 0
var result = 0
var R = 0
var C = 0
val nextRow = intArrayOf(0, 0, -1, 1)
val nextCol = intArrayOf(-1, 1, 0, 0)
lateinit var start: Node
lateinit var end: Node

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    R = st.nextToken().toInt()
    C = st.nextToken().toInt()
    start = Node(R - 1, 0)
    end = Node(0, C - 1)
    targetDist = st.nextToken().toInt()

    visit = Array(R) { BooleanArray(C) }
    visit[start.row][start.col] = true
    for (i in 0 until R) {
        val line = br.readLine()
        for (j in 0 until C) {
            if (line[j] == 'T') visit[i][j] = true
        }
    }

    for (i in 0..3) {
        val nr = start.row + nextRow[i]
        val nc = start.col + nextCol[i]
        if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue
        if (visit[nr][nc]) continue
        visit[nr][nc] = true
        dfs(Node(nr, nc), 2)
        visit[nr][nc] = false
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

fun dfs(node: Node, dist: Int) {
    if (dist == targetDist) {
        if (node == end) result++
        visit[node.row][node.col] = false
        return
    }
    for (i in 0..3) {
        val nr = node.row + nextRow[i]
        val nc = node.col + nextCol[i]
        if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue
        if (visit[nr][nc]) continue
        visit[nr][nc] = true
        dfs(Node(nr, nc), dist + 1)
        visit[nr][nc] = false
    }
}

data class Node(val row: Int, val col: Int)
