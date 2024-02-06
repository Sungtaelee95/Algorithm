import java.util.*

val board = Array(19) { intArrayOf() }
val node = arrayOf(Node(0, 1), Node(1, 0), Node(1, 1), Node(-1, 1))
var winner = 0
var winPosition = Node(0, 0)
fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(19) {
        val st = StringTokenizer(br.readLine())
        board[it] = IntArray(19) { st.nextToken().toInt() }
    }

    for (row in 0..18) {
        for (col in 0..18) {
            if (board[row][col] != 0) {
                check(row, col)
            }
        }
    }

    bw.appendLine("$winner")
    if (winner != 0) {
        bw.append("${winPosition.row} ${winPosition.col}")
    }

    bw.flush()
    bw.close()
}

fun check(row: Int, col: Int) {
    for (i in 0..3) {
        var result = false
        val position = Node(row, col)
        val next = node[i]
        var check = true
        for (j in 1..4) {
            position.row += next.row
            position.col += next.col
            if (position.row > 18 || position.col > 18 || position.row < 0 || position.col < 0) {
                check = false
                break
            }
            if (board[row][col] != board[position.row][position.col]) {
                check = false
                break
            }
        }
        if (check) {
            val backPosition = Node(row - node[i].row, col - node[i].col)
            position.row += next.row
            position.col += next.col
            if (position.row > 18 || position.col > 18 || position.row < 0 || position.col < 0 || board[row][col] != board[position.row][position.col]) {
                if (backPosition.row < 0 || backPosition.col < 0 || backPosition.row > 18 || backPosition.col > 18 || board[row][col] != board[backPosition.row][backPosition.col]) {
                    result = true
                }
            }
        }
        if (result) {
            winner = board[row][col]
            winPosition = Node(row + 1, col + 1)
        }
    }
}

data class Node(var row: Int, var col: Int)
