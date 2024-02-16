import java.util.*
import kotlin.collections.ArrayDeque

lateinit var start: Position
fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val visited = Array(N) { BooleanArray(M) }
    val map = Array(N) { charArrayOf() }

    repeat(N) {
        map[it] = br.readLine().toCharArray()
        map[it].forEachIndexed { index, c ->
            when (c) {
                'I' -> {
                    start = Position(it, index)
                    visited[it][index] = true
                }
                'X' -> visited[it][index] = true
            }
        }
    }
    var answer = 0
    val dq = ArrayDeque<Position>()
    dq.add(start)
    val nextRow = intArrayOf(1,0,-1,0)
    val nextCol = intArrayOf(0,1,0,-1)
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        for (i in 0..3) {
            val nr = nextRow[i] + now.r
            val nc = nextCol[i] + now.c
            if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue
            if (visited[nr][nc]) continue
            visited[nr][nc] = true
            if (map[nr][nc] == 'P') answer++
            dq.addLast(Position(nr, nc))
        }
    }
    if (answer == 0) {
        bw.append("TT")
    } else {
        bw.append("$answer")
    }

    bw.flush()
    bw.close()
}

data class Position(val r: Int, val c: Int)

