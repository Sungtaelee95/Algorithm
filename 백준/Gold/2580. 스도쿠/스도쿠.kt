import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val board = Array(10) { IntArray((10)) }
val rows = intArrayOf(0, 0, 1, -1)
val cols = intArrayOf(1, -1, 0, 0)
val bw = BufferedWriter(OutputStreamWriter(System.out))
var endCheck = false
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    repeat(9) { row ->
        val st = StringTokenizer(br.readLine())
        repeat(9) { col ->
            board[row][col] = st.nextToken().toInt()
        }
    }

    dfs(Node(0, 0))

    bw.flush()
    bw.close()
}

fun dfs(node: Node) {
    if (endCheck) return
    if (node.col == 9) {
        val new = Node(node.row + 1, 0)
        dfs(new)
    }
    if (node.row == 9) {
        for (i in 0..8) {
            for (j in 0..8) {
                bw.append("${board[i][j]} ")
            }
            bw.appendLine()
        }
        endCheck = true
        return
    }
    if (board[node.row][node.col] == 0) {
        for (i in 1..9) {
            if (!whIsContains(i, node) && !rectangleIsContains(i, node)) {
                board[node.row][node.col] = i
                dfs(Node(node.row, node.col + 1))
            }
        }
        board[node.row][node.col] = 0
    } else {
        dfs(Node(node.row, node.col + 1))
    }
}

fun whIsContains(num: Int, node: Node): Boolean {
    for (i in 0..8) {
        if (board[i][node.col] == num) return true
        if (board[node.row][i] == num) return true
    }
    return false
}

fun rectangleIsContains(num: Int, node: Node): Boolean {
    val sRow = (node.row / 3) * 3
    val sCol = (node.col / 3) * 3
    for (i in sRow..sRow + 2) {
        for (j in sCol..sCol + 2) {
            if (board[i][j] == num) return true
        }
    }
    return false
}

data class Node(val row: Int, val col: Int)
