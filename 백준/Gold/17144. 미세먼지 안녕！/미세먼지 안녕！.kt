import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

lateinit var map: Array<IntArray>
lateinit var top: Node
lateinit var bottom: Node
var r = 0
var c = 0
var t = 0
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    r = st.nextToken().toInt()
    c = st.nextToken().toInt()
    t = st.nextToken().toInt()

    map = Array(r) { IntArray(c) }
    var check = true
    repeat(r) { row ->
        st = StringTokenizer(br.readLine())
        repeat(c) { col ->
            map[row][col] = st.nextToken().toInt()
            if (map[row][col] == -1) {
                if (check) {
                    check = false
                    top = Node(row, col)
                } else {
                    bottom = Node(row, col)
                }
            }
        }
    }

    repeat(t) {
        spread()
        circle()
    }

    var result = 2
    map.forEach {
        result += it.sum()
    }
    bw.append("$result")

    bw.flush()
    bw.close()
}

val nextRow = intArrayOf(0, 0, -1, 1)
val nextCol = intArrayOf(-1, 1, 0, 0)

fun spread() {
    val dq = ArrayDeque<Node>()
    val tempMap = Array(r) { IntArray(c) }
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] != 0 && map[i][j] != -1) dq.addLast(Node(i, j))
            if (map[i][j] == -1) tempMap[i][j] = -1
        }
    }
    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        var cnt = 0
        for (i in 0..3) {
            val nextNode = Node(now.row + nextRow[i], now.col + nextCol[i])
            if (nextNode.row < 0 || nextNode.col < 0 || nextNode.row >= r || nextNode.col >= c) continue
            if (map[nextNode.row][nextNode.col] == -1) continue
            cnt++
            tempMap[nextNode.row][nextNode.col] += map[now.row][now.col] / 5
        }
        tempMap[now.row][now.col] += map[now.row][now.col] - (map[now.row][now.col] / 5 * cnt)
    }
    map = tempMap
}

fun circle() {
    val tops = ArrayDeque<Int>()
    for (i in top.col + 1 until c) {
        tops.addLast(map[top.row][i])
    }
    for (i in top.row - 1 downTo 0) {
        tops.addLast(map[i][c - 1])
    }
    for (i in c - 2 downTo 0) {
        tops.addLast(map[0][i])
    }
    for (i in 1 until top.row) {
        tops.addLast(map[i][0])
    }
    tops.addFirst(0)
    tops.removeLast()

    for (i in top.col + 1 until c) {
        map[top.row][i] = tops.removeFirst()
    }
    for (i in top.row - 1 downTo 0) {
        map[i][c - 1] = tops.removeFirst()
    }
    for (i in c - 2 downTo 0) {
        map[0][i] = tops.removeFirst()
    }
    for (i in 1 until top.row) {
        map[i][0] = tops.removeFirst()
    }

    val bottoms = ArrayDeque<Int>()
    for (i in bottom.col + 1 until c) {
        bottoms.addLast(map[bottom.row][i])
    }
    for (i in bottom.row + 1 until r) {
        bottoms.addLast(map[i][c - 1])
    }
    for (i in c - 2 downTo 0) {
        bottoms.addLast(map[r - 1][i])
    }
    for (i in r - 2 downTo bottom.row + 1) {
        bottoms.addLast(map[i][0])
    }
    bottoms.addFirst(0)
    bottoms.removeLast()

    for (i in bottom.col + 1 until c) {
        map[bottom.row][i] = bottoms.removeFirst()
    }
    for (i in bottom.row + 1 until r) {
        map[i][c - 1] = bottoms.removeFirst()
    }
    for (i in c - 2 downTo 0) {
        map[r - 1][i] = bottoms.removeFirst()
    }
    for (i in r - 2 downTo bottom.row + 1) {
        map[i][0] = bottoms.removeFirst()
    }
}

data class Node(val row: Int, val col: Int)
