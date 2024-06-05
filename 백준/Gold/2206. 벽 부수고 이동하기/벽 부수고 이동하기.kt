import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.StringTokenizer
import kotlin.collections.ArrayList


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    var N = st.nextToken().toInt()
    var M = st.nextToken().toInt()

    var result = Int.MAX_VALUE
    val nr = intArrayOf(0, 0, 1, -1)
    val nc = intArrayOf(1, -1, 0, 0)

    val road = Array(N) { br.readLine() }
    var visit = Array(N) { BooleanArray(M) }
    var dq = ArrayDeque<Node>()
    dq.addFirst(Node(0, 0, false, 1))
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (now.row == N-1 && now.col == M-1) {
            result = minOf(result, now.count)
        }
        for (i in 0..3) {
            val nextRow = now.row + nr[i]
            val nextCol = now.col + nc[i]
            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue
            if (road[nextRow][nextCol] == '1' && now.broke) continue
            if (road[nextRow][nextCol] == '1' && !now.broke) {
                visit[nextRow][nextCol] = true
                dq.addLast(Node(nextRow, nextCol, true, now.count+1))
            }
            if (road[nextRow][nextCol] == '0' && !visit[nextRow][nextCol]) {
                visit[nextRow][nextCol] = true
                dq.addLast(Node(nextRow, nextCol, now.broke, now.count+1))
            }
        }
    }
    visit = Array(N) { BooleanArray(M) }
    dq = ArrayDeque<Node>()
    dq.addFirst(Node(N-1, M-1, false, 1))
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (now.row == 0 && now.col == 0) {
            result = minOf(result, now.count)
        }
        for (i in 0..3) {
            val nextRow = now.row + nr[i]
            val nextCol = now.col + nc[i]
            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue
            if (road[nextRow][nextCol] == '1' && now.broke) continue
            if (road[nextRow][nextCol] == '1' && !now.broke) {
                visit[nextRow][nextCol] = true
                dq.addLast(Node(nextRow, nextCol, true, now.count+1))
            }
            if (road[nextRow][nextCol] == '0' && !visit[nextRow][nextCol]) {
                visit[nextRow][nextCol] = true
                dq.addLast(Node(nextRow, nextCol, now.broke, now.count+1))
            }
        }
    }


    if (result == Int.MAX_VALUE) {
        bw.append("-1")
    } else {
        bw.append("$result")
    }

    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int, val broke: Boolean, val count: Int)