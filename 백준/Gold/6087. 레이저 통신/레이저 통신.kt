import java.io.BufferedReader // ktlint-disable filename
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val C = st.nextToken().toInt()
    val R = st.nextToken().toInt()

    val nextRow = intArrayOf(0, 0, 1, -1)
    val nextCol = intArrayOf(1, -1, 0, 0)

    var start: Node? = null
    var end: Node? = null

    val visit = Array(R) { BooleanArray(C) }
    val changeMap = Array(R) { IntArray(C) { Int.MAX_VALUE } }
    for (i in 0 until R) {
        val line = br.readLine()
        for (j in 0 until C) {
            if (line[j] == '*') visit[i][j] = true
            if (line[j] == 'C') {
                if (start == null) {
                    start = Node(i, j, 0)
                    visit[i][j] = true
                } else {
                    end = Node(i, j, 0)
                    visit[i][j] = false
                }
            }
        }
    }

    val dq = ArrayDeque<Node>()
    dq.add(start!!)
    while (!dq.isEmpty()) {
        val now = dq.pollFirst()
        for (i in 0..3) {
            var nr = now.row + nextRow[i]
            var nc = now.col + nextCol[i]
            while (true) {
                if (nr < 0 || nc < 0 || nr >= R || nc >= C) break
                if (visit[nr][nc]) break
                if (changeMap[nr][nc] > now.value) {
                    changeMap[nr][nc] = now.value
                    dq.addLast(Node(nr, nc, now.value + 1))
                }
                nr += nextRow[i]
                nc += nextCol[i]
            }
        }
    }

    bw.append("${changeMap[end!!.row][end!!.col]}")

    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int, val value: Int)
