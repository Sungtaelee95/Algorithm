import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val M = st.nextToken().toInt()
    val N = st.nextToken().toInt()

    val visited = Array(N) { BooleanArray(M) }
    val map = Array(N) { br.readLine().toCharArray() }

    var wPower = 0
    var bPower = 0

    val nextRow = intArrayOf(1, 0, -1, 0)
    val nextCol = intArrayOf(0, 1, 0, -1)

    for (row in 0 until N) {
        for (col in 0 until M) {
            if (visited[row][col]) continue
            visited[row][col] = true
            val dq = ArrayDeque<Position>()
            dq.add(Position(row, col))
            var count = 1
            while (dq.isNotEmpty()) {
                val now = dq.removeFirst()
                for (i in 0..3) {
                    val nr = nextRow[i] + now.r
                    val nc = nextCol[i] + now.c
                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue // 범위
                    if (visited[nr][nc]) continue // 방문여부
                    if (map[row][col] != map[nr][nc]) continue // 아군 여부
                    visited[nr][nc] = true
                    dq.addLast(Position(nr, nc))
                    count++
                }
            }
            when (map[row][col]) {
                'W' -> wPower += count * count
                'B' -> bPower += count * count
            }
        }
    }

    bw.append("$wPower $bPower")

    bw.flush()
    bw.close()
}

data class Position(val r: Int, val c: Int)

