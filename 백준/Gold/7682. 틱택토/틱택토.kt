import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val winningTriggers = listOf(
    listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
    listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), 
    listOf(0, 4, 8), listOf(2, 4, 6) 
)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val tiktaktok = br.readLine()
        if (tiktaktok == "end") break

        if (isValidTicTacToe(tiktaktok)) {
            bw.appendLine("valid")
        } else {
            bw.appendLine("invalid")
        }
    }
    bw.flush()
    bw.close()
}

fun isValidTicTacToe(board: String): Boolean {
    val xCount = board.count { it == 'X' }
    val oCount = board.count { it == 'O' }

    // 기본 조건: X는 O보다 하나 많거나, 같은 수가 되어야 함
    if (xCount != oCount && xCount != oCount + 1) {
        return false
    }

    val xWins = isWinner(board, 'X')
    val oWins = isWinner(board, 'O')

    // 기본 조건: 둘 다 이길 수 없다
    if (xWins && oWins) return false

    // X가 이겼을 때: X가 O보다 하나 더 많이 있어야 함
    if (xWins && xCount != oCount + 1) return false

    // O가 이겼을 때: X와 O의 수가 같아야 함
    if (oWins && xCount != oCount) return false

    // 무승부 혹은 일치하며 종료된 경우
    return (xWins || oWins || xCount + oCount == 9)
}

fun isWinner(board: String, player: Char): Boolean {
    return winningTriggers.any { (a, b, c) ->
        board[a] == player && board[b] == player && board[c] == player
    }
}