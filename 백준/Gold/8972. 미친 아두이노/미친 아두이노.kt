import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()

    val board = Array(R) { CharArray(C) }

    repeat(R) {
        board[it] = br.readLine().toCharArray()
    }

    val commends = br.readLine().toCharArray()
    val directions = listOf<Position>(
        Position(1, -1),
        Position(1, 0),
        Position(1, 1),
        Position(0, -1),
        Position(0, 0),
        Position(0, 1),
        Position(-1, -1),
        Position(-1, 0),
        Position(-1, 1)
    )

    val dq = ArrayDeque<Position>()
    var turnCount = 0
    var endCheck = false

    for (commend in commends) {
        turnCount++
        var changeCheck = false
        var jongsuPosition = Position(0,0)
        for (row in 0 until R) {
            for (col in 0 until C) {
                if (board[row][col] == 'I') {
                    board[row][col] = '.'
                    if (board[row + directions[commend - '1'].row][col + directions[commend - '1'].col] == 'R') {
                        bw.append("kraj $turnCount")
                        endCheck = true
                        break
                    }
                    board[row + directions[commend - '1'].row][col + directions[commend - '1'].col] = 'I'
                    jongsuPosition = Position(row + directions[commend - '1'].row, col + directions[commend - '1'].col)
                    changeCheck = true
                    break
                }
            }
            if (changeCheck) break
        }

        for (row in 0 until R) {
            for (col in 0 until C) {
                if (board[row][col] == 'R') {
                    board[row][col] = '.'
                    dq.add(Position(row, col))
                }
            }
        }

        val distinctSet = mutableSetOf<Position>()
        while (!dq.isEmpty()) {
            val now = dq.pollFirst()
            val distArr = IntArray(9)
            repeat(9) {
                distArr[it] = abs(jongsuPosition.row - (now.row + directions[it].row)) + abs(jongsuPosition.col - (now.col + directions[it].col))
                if (now.row + directions[it].row < 0 || now.row + directions[it].row >= R ||
                    now.col + directions[it].col < 0 || now.col + directions[it].col >= C) distArr[it] = Int.MAX_VALUE
            }
            val nextCommend = directions[distArr.indexOf(distArr.min())]
            val nextPosition = Position(now.row + nextCommend.row, now.col + nextCommend.col)
            if (board[nextPosition.row][nextPosition.col] == 'I') {
                bw.append("kraj $turnCount")
                endCheck = true
                break
            }
            if(distinctSet.contains(nextPosition)){
                board[nextPosition.row][nextPosition.col] = '.'
                continue
            }
            distinctSet.add(nextPosition)
            board[nextPosition.row][nextPosition.col] = 'R'
        }
        if(endCheck) break

    }

    if (!endCheck) {
        board.forEach {
            bw.appendLine(it.joinToString(""))
        }
    }

    bw.flush()
    bw.close()
}

data class Position(val row: Int, val col: Int)