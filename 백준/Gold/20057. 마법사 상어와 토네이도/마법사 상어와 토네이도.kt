import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

var result = 0L
lateinit var map: Array<LongArray>

// ←
val left = arrayOf(
    Node(-2, 0, 2),
    Node(-1, -1, 10), Node(-1, 0, 7), Node(-1, 1, 1),
    Node(0, -2, 5),
    Node(1, -1, 10), Node(1, 0, 7), Node(1, 1, 1),
    Node(2, 0, 2)
)

// ↓
val down = arrayOf(
    Node(-1, -1, 1), Node(-1, 1, 1),
    Node(0, -2, 2), Node(0, -1, 7), Node(0, 1, 7), Node(0, 2, 2),
    Node(1, -1, 10), Node(1, 1, 10),
    Node(2, 0, 5)
)

// →
val right = arrayOf(
    Node(-2, 0, 2),
    Node(-1, -1, 1), Node(-1, 0, 7), Node(-1, 1, 10),
    Node(0, 2, 5),
    Node(1, -1, 1), Node(1, 0, 7), Node(1, 1, 10),
    Node(2, 0, 2)
)

// ↑
val up = arrayOf(
    Node(-2, 0, 5),
    Node(-1, -1, 10), Node(-1, 1, 10),
    Node(0, -2, 2), Node(0, -1, 7), Node(0, 1, 7), Node(0, 2, 2),
    Node(1, -1, 1), Node(1, 1, 1)
)
var n = 0

data class Node(val row: Int, val col: Int, val percent: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    map = Array(n) { LongArray(n) }

    repeat(n) {
        st = StringTokenizer(br.readLine())
        map[it] = LongArray(n) { st.nextToken().toLong() }
    }
    tornado((n - 1) / 2, (n - 1) / 2 - 1, 3)

    var dist = 1
    var row = (n - 1) / 2
    var col = (n - 1) / 2 - 1
    repeat((n - 1) / 2) {
        for (i in 0..3) {
            when (i) {
                0 -> {
                    repeat(dist){
                        tornado(++row, col, i)
                    }
                }
                1 -> {
                    dist++
                    repeat(dist){
                        tornado(row, ++col, i)
                    }
                }
                2-> {
                    repeat(dist){
                        tornado(--row, col, i)
                    }
                }
                3 -> {
                    if (dist < n-1) dist++
                    repeat(dist){
                        tornado(row, --col, i)
                    }
                }
            }
        }
    }

    bw.append("$result")
    bw.flush()
    bw.close()
}

fun tornado(row: Int, col: Int, dist: Int) {
    val arr = when (dist) {
        0 -> down
        1 -> right
        2 -> up
        3 -> left
        else -> arrayOf(Node(0, 0, 0))
    }
    var alpha = 0L
    for (node in arr) {
        val nr = node.row + row
        val nc = node.col + col
        val plus = (map[row][col] * node.percent) / 100
        if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
            result += plus
            alpha += plus
            continue
        }
        map[nr][nc] += plus
        alpha += plus
    }

    val nr = when (dist) {
        0 -> row + 1
        1, 3 -> row
        2 -> row - 1
        else -> Int.MAX_VALUE
    }
    val nc = when (dist) {
        0, 2 -> col
        1 -> col + 1
        3 -> col - 1
        else -> Int.MAX_VALUE
    }
    if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
        result += map[row][col] - alpha
    } else {
        map[nr][nc] += map[row][col] - alpha
    }
    map[row][col] = 0
}
