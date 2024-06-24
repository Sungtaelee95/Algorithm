import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val board = Array(10) { IntArray((10)) }
val rowCheck =  Array(10) { BooleanArray((10)) }
val colCheck =  Array(10) { BooleanArray((10)) }
val rectangleCheck = Array(10) { BooleanArray((10)) }
val bw = BufferedWriter(OutputStreamWriter(System.out))
var endCheck = false
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    repeat(9) { row ->
        val st = StringTokenizer(br.readLine())
        repeat(9) { col ->
            board[row][col] = st.nextToken().toInt()
            if (board[row][col] != 0) {
                rowCheck[row][board[row][col]] = true
                colCheck[col][board[row][col]] = true
                rectangleCheck[row/3*3+col/3][board[row][col]] = true
            }
        }
    }

    dfs(0,0)

    bw.flush()
    bw.close()
}

fun dfs(row: Int, col: Int) {
    if(endCheck) return
    if (row == 9) {
        for (i in 0 .. 8) {
            for (j in 0 .. 8) {
                bw.append("${board[i][j]} ")
            }
            bw.appendLine()
        }
        endCheck = true
    }
    if (col == 9) {
        dfs(row+1, 0)
    }

    if (board[row][col] == 0) {
        for (i in 1 .. 9) {
            if (!rowCheck[row][i] &&
                !colCheck[col][i] &&
                !rectangleCheck[row/3*3+col/3][i]
                ) {
                rowCheck[row][i] = true
                colCheck[col][i] = true
                rectangleCheck[row/3*3+col/3][i] = true
                board[row][col] = i
                dfs(row, col+1)
                rowCheck[row][i] = false
                colCheck[col][i] = false
                rectangleCheck[row/3*3+col/3][i] = false
                board[row][col] = 0
            }
        }
    } else {
        dfs(row, col+1)
    }

}