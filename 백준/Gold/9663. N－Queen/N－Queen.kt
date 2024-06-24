import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

lateinit var board: Array<IntArray>
lateinit var rowCheck: BooleanArray
lateinit var colCheck: BooleanArray
lateinit var rucCheck: BooleanArray
lateinit var lucCheck: BooleanArray
var n = 0
var result = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    n = br.readLine().toInt()
    board = Array(n) { IntArray(n) }
    rowCheck = BooleanArray(n)
    colCheck = BooleanArray(n)
    rucCheck = BooleanArray(n * 2)
    lucCheck = BooleanArray(n * 2)


    for (i in 0 until n) {
        rowCheck[0] = true
        colCheck[i] = true
        rucCheck[0 + i + 1] = true
        lucCheck[n - i + 0] = true
        dfs(1)
        rowCheck[0] = false
        colCheck[i] = false
        rucCheck[0 + i + 1] = false
        lucCheck[n - i + 0] = false
    }

    bw.append("$result")
    bw.flush()
    bw.close()
}

fun dfs(row: Int) {

    if (row == n) {
        result++
        return
    }

    for (nc in 0 until n) {
        if (!rowCheck[row] &&
            !colCheck[nc] &&
            !rucCheck[row + nc + 1] &&
            !lucCheck[n - nc + row]
        ) {
            rowCheck[row] = true
            colCheck[nc] = true
            rucCheck[row + nc + 1] = true
            lucCheck[n - nc + row] = true
            dfs(row+1)
            rowCheck[row] = false
            colCheck[nc] = false
            rucCheck[row + nc + 1] = false
            lucCheck[n - nc + row] = false
        }
    }
}
