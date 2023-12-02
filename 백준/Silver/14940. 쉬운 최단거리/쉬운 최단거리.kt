import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val map = Array(N) { IntArray(M) }
    val dq = ArrayDeque<Node>()

    val nextArr = arrayOf(
        Node(0, 1),
        Node(0, -1),
        Node(1, 0),
        Node(-1, 0)
    )

    for (i in 0 until N) {
        val arr = IntArray(M) { -1 }
        st = StringTokenizer(br.readLine())
        for (j in 0 until M) {
            val value = st.nextToken().toInt()
            if (value == 2) {
                arr[j] = 0
                dq.addLast(Node(i, j))
            }
            if (value == 0) arr[j] = 0
        }
        map[i] = arr
    }

    while (!dq.isEmpty()) {
        val now = dq.removeFirst()
        for (next in nextArr) {
            val nextNode = Node(now.row + next.row, now.col + next.col)
            if (nextNode.row < 0 || nextNode.col < 0 || nextNode.row >= N || nextNode.col >= M) continue
            if (map[nextNode.row][nextNode.col] != -1) continue
            map[nextNode.row][nextNode.col] = map[now.row][now.col] + 1
            dq.addLast(nextNode)
        }
    }

    map.forEach {
        bw.appendLine(it.joinToString(" "))
    }

    bw.flush()
    bw.close()
}

data class Node(val row: Int, val col: Int)
