import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.sqrt

lateinit var board: Array<LongArray>
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    var result = -1L

    board = Array(n) { LongArray(m) }
    repeat(n) { row ->
        val input = br.readLine()
        for (col in input.indices) {
            board[row][col] = (input[col] - '0').toLong()
        }
    }

    for (row in 0 until n) {
        for (col in 0 until m) {
            if (squareCheck(board[row][col])) result = maxOf(result, board[row][col])
            for (nr in -8..8) {
                for (nc in -8..8) {
                    if (nr == 0 && nc == 0) continue
                    var num = board[row][col]
                    var nextRow = row + nr
                    var nextCol = col + nc
                    while (true) {
                        if (nextRow >= n || nextCol >= m || nextRow < 0 || nextCol < 0) break
                        num = num * 10 + board[nextRow][nextCol]
                        if (squareCheck(num)) result = maxOf(result, num)
                        nextRow += nr
                        nextCol += nc
                    }
                }
            }
        }
    }
    
    bw.append("$result")

    bw.flush()
    bw.close()
}

fun squareCheck(num: Long): Boolean {
    return sqrt(num.toFloat()).toLong() * sqrt(num.toFloat()).toLong() == num
}