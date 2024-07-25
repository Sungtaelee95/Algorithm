import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.abs
import java.text.Format
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.round

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val map = Array(n) { br.readLine().toCharArray() }
    val nextRow = intArrayOf(0, 0, -1, 1)
    val nextCol = intArrayOf(-1, 1, 0, 0)
    var result = 0
    for (row in 0 until n) {
        for (col in 0 until m) {
            if (map[row][col] == 'W') continue
            val visited = Array(n) { BooleanArray(m) }
            visited[row][col] = true
            val dq = ArrayDeque<Node>()
            dq.addLast(Node(row, col, 0))
            while (dq.isNotEmpty()) {
                val now = dq.removeFirst()
                result = maxOf(result, now.dist)
                for (i in 0..3) {
                    val nr = nextRow[i] + now.row
                    val nc = nextCol[i] + now.col
                    if (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 'W' || visited[nr][nc]) continue
                    visited[nr][nc] = true
                    dq.addLast(Node(nr, nc, now.dist + 1))
                }
            }
        }
    }
    bw.append("$result")
    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int, val dist: Int)